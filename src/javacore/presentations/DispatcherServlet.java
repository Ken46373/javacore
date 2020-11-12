package javacore.presentations;

import java.util.HashMap;
import java.util.Map;

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
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    logger.trace("start.");

    this.processRequest(request, response);

    logger.trace("done.");
  }

  /**
   * processRequestに処理を移譲(POST)。
   *
   * @param request  HTTPリクエスト
   * @param response HTTPレスポンス
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    logger.trace("start.");

    this.processRequest(request, response);

    logger.trace("done.");
  }

  /**
   * processRequestに処理を移譲(PUT)。
   *
   * @param request  HTTPリクエスト
   * @param response HTTPレスポンス
   */
  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) {

    logger.trace("start.");

    this.processRequest(request, response);

    logger.trace("done.");
  }

  /**
   * processRequestに処理を移譲(DELETE)。
   *
   * @param request  HTTPリクエスト
   * @param response HTTPレスポンス
   */
  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) {

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
   */
  private void processRequest(HttpServletRequest request, HttpServletResponse response) {

    logger.trace("start.");

    logger.trace("done.");
  }
}
