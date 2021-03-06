package javacore.presentations;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servletクラス
 *
 * @author kenminami
 */
@WebListener
public class DispatcherServlet extends HttpServlet implements ServletContextListener {

  /** Logger。 */
  private static Logger logger = LogManager.getLogger();

  /** コントローラ一覧のマップ。 */
  private static final Map<String, RequestHandler> CONTROLLER_MAP = new HashMap<>();

  /** デフォルトサーブレット名 (Tomcat, Jetty, JBoss, and GlassFish)。 */
  private static final String DEFAULT_SERVLET_NAME = "default";

  /**
   * コンストラクタ。
   */
  public DispatcherServlet() {

    super();
  }

  // --- ServletContextListenerの実装メソッド ---

  /**
   * WEBアプリケーションの起動時処理。
   */
  @Override
  public void contextInitialized(ServletContextEvent sce) {

    logger.trace("start.");

    logger.trace("done.");
  }

  /**
   * WEBアプリケーションの終了時処理。
   */
  @Override
  public void contextDestroyed(ServletContextEvent sce) {

    logger.trace("start.");

    CONTROLLER_MAP.clear();

    logger.trace("done.");
  }

  // --- HttpServletのオーバーライド ---
  /**
   * アプリケーション起動時処理。
   */
  @Override
  public void init() throws ServletException {

    logger.trace("start.");

    // no op

    logger.trace("done.");
  }

  /**
   * アプリケーション停止時処理。
   */
  @Override

  public void destroy() {

    logger.trace("start.");

    // no op

    logger.trace("done.");
  }

  /**
   * HTTPメソッドごとに処理を振り分ける。<br>
   * (サーブレットが要求されるたびに呼び出される)
   *
   * @param request  HTTPリクエスト
   * @param response HTTPレスポンス
   */
  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) {

    logger.trace("start.");

    logger.trace("done.");
  }

  /**
   * processRequestに処理を移譲(GET)。
   *
   * @param request  HTTPリクエスト
   * @param response HTTPレスポンス
   * @throws IOException エラー発生
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    logger.trace("start.");

    this.processRequest(request, response);

    logger.trace("done.");
  }

  /**
   * processRequestに処理を移譲(POST)。
   *
   * @param request  HTTPリクエスト
   * @param response HTTPレスポンス
   * @throws IOException エラー発生
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    logger.trace("start.");

    this.processRequest(request, response);

    logger.trace("done.");
  }

  /**
   * processRequestに処理を移譲(PUT)。
   *
   * @param request  HTTPリクエスト
   * @param response HTTPレスポンス
   * @throws IOException エラー発生
   */
  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

    logger.trace("start.");

    this.processRequest(request, response);

    logger.trace("done.");
  }

  /**
   * processRequestに処理を移譲(DELETE)。
   *
   * @param request  HTTPリクエスト
   * @param response HTTPレスポンス
   * @throws IOException エラー発生
   */
  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

    logger.trace("start.");

    this.processRequest(request, response);

    logger.trace("done.");
  }

  // --- 以降、内部メソッド ---

  /**
   * リクエストを処理してイベントを発行。
   *
   * @param request  HTTPリクエスト
   * @param response HTTPレスポンス
   * @throws IOException
   */
  private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

    logger.trace("start.");

    try {
      RequestHandler handler = this.resolveRequestHandler(request);

      // Controllerが登録されていない(css/js/img)場合はデフォルトサーブレットにディスパッチする
      if (handler == null) {
        this.dispatchToDefault(request, response);
      }
      else {
        this.dispatchToController(request, response, handler);
      }
    }
    catch (Exception e) {
      logger.error("ERROR: failed to handle request.");
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    logger.trace("done.");
  }

  /**
   * ハンドラを取得する。
   *
   * @param request HTTPリクエスト
   * @return リクエストハンドラ
   */
  private RequestHandler resolveRequestHandler(HttpServletRequest request) {

    String requestUri = request.getRequestURI();

    if (requestUri == null || requestUri.length() == 0) {
      return null;
    }
    int index = requestUri.indexOf('/', 1);
    if (index < 0) {
      requestUri = "/";
    }
    else {
      requestUri = requestUri.substring(index);
    }

    requestUri = requestUri.toLowerCase();
    RequestHandler handler = CONTROLLER_MAP.get(requestUri);
    if (handler == null) {
      if (requestUri.endsWith("/")) {
        requestUri = requestUri.substring(0, requestUri.length() - 1);
      }
      else {
        requestUri += "/";
      }
      handler = CONTROLLER_MAP.get(requestUri);
    }
    return handler;
  }

  /**
   * デフォルトサーブレットへのディスパッチ処理。
   *
   * @param request  HTTPリクエスト
   * @param response HTTPレスポンス
   * @throws ServletException エラー発生
   * @throws IOException エラー発生
   */
  private void dispatchToDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    logger.trace("start.");

    ServletContext servletContext = this.getServletContext();
    RequestDispatcher requestDispatcher = servletContext.getNamedDispatcher(DEFAULT_SERVLET_NAME);

    requestDispatcher.forward(request, response);

    logger.trace("done.");
  }

  /**
   * Controllerへのディスパッチ処理。
   *
   * @param request  HTTPリクエスト
   * @param response HTTPレスポンス
   * @param handler  リクエストハンドラ
   * @throws Exception Controllerの呼び出しに失敗,またはControllerが例外をthrowした場合
   */
  private void dispatchToController(HttpServletRequest request, HttpServletResponse response, RequestHandler handler) throws Exception {

    if (handler.getHttpMethod() != HttpMethod.ANY) {
      // リクエストのHTTPメソッドが登録しているものと違う場合
      HttpMethod httpMethod = HttpMethod.resolve(request.getMethod());
      if (httpMethod != handler.getHttpMethod()) {
          logger.warn(String.format(
                  "HTTPメソッドが異なります。期待: %s, 受信: %s",
                  handler.getHttpMethod().toString(),
                  httpMethod.toString()));
          this.onInvalidRequest(request, response);
          return;
      }
    }

    // ajaxOnly の判定処理
    if (handler.getAjaxOnly()) {
        if (this.isAjaxRequest(request) == false) {
            logger.warn("ajaxリクエストではありません。");
            this.onInvalidRequest(request, response);
            return;
        }
    }
  }

  /**
   * 不正リクエストの受信時処理。
   *
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @throws Exception 例外発生
   */
  private void onInvalidRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

    boolean isAjaxRequest = this.isAjaxRequest(request);

    if (isAjaxRequest) {

    }
  }

  /**
   * ファイルにアクセスするファイルシステムを構築する。
   *
   * @param uri ファイルシステムを識別するURI
   * @param env プロパティのマップ
   * @param loader プロバイダを構築するためのクラスローダ
   * @return 新しいファイルシステム
   * @throws IOException ファイル・システムの作成中に入出力エラーが発生した場合
   */
  protected static FileSystem newFileSystem(URI uri, Map<String,?> env, ClassLoader loader)
          throws IOException {

      // FileSystems.newFileSystem(uri, env, loader) を呼び出すと例外が発生するので、
      // 例外を発生させないよう変更

      String scheme = uri.getScheme();

      // check installed providers
      for (FileSystemProvider provider: FileSystemProvider.installedProviders()) {
          if (scheme.equalsIgnoreCase(provider.getScheme())) {
              try {
                  return provider.newFileSystem(uri, env);
              }
              catch (Exception ex) {
                  // no op
              }
          }
      }

      // if not found, use service-provider loading facility
      if (loader != null) {
          ServiceLoader<FileSystemProvider> sl = ServiceLoader
              .load(FileSystemProvider.class, loader);
          for (FileSystemProvider provider: sl) {
              if (scheme.equalsIgnoreCase(provider.getScheme())) {
                  return provider.newFileSystem(uri, env);
              }
          }
      }

      // throw new ProviderNotFoundException("Provider \"" + scheme + "\" not found");
      return null;
  }


  /**
   * ajaxのリクエストかどうか判定する。
   *
   * @param request  HTTPリクエスト
   * @return ajaxのリクエストかどうか
   */
  private boolean isAjaxRequest(HttpServletRequest request) {

    String requestedWith = request.getHeader("X-Requested-With");
    if (requestedWith != null) {
        requestedWith = requestedWith.toLowerCase();
        if (requestedWith != null && requestedWith.equals("xmlhttprequest")) {
            return true;
        }
    }

    return false;
  }
}
