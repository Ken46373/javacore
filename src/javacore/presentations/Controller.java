package javacore.presentations;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javacore.presentations.filters.Filter;

/**
 * 抽象化Controllerクラス。
 *
 * @author kenminami
 */
public abstract class Controller {

  /** HttpServletRequest。 */
  private HttpServletRequest request;

  /** HttpServletResponse。 */
  private HttpServletResponse response;

  /** セッショントークン(処理前)。 */
  private String preSessionToken;

  /** セッショントークン(処理後)。 */
  private String postSessionToken;

  /**
   * Controllerの前提条件となるFilterを取得する。
   *
   * @return Filter
   */
  protected abstract Filter[] getFilters();

  /**
   * Controllerの前提条件となる実行権限を確認する。
   *
   * @param url URL
   * @return 実行可能かどうかの真偽値
   * @throws Exception DBエラー
   */
  protected abstract boolean canExecute(String[] url) throws Exception;

  /**
   * 事前処理。
   *
   * @param request  HttpServletRequest
   * @param response HttpServletResponse
   * @throws Exception DBエラー,ほか
   */
  void preProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {

    this.request  = request;
    this.response = response;
  }

  /**
   * 事前処理。
   *
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @throws Exception DBエラー、他
   */
  void onPreProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {

  }

  /**
   * 事後処理。
   *
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @throws Exception DBエラー、他
   */
  /* package-private */void postProcess(
          HttpServletRequest request, HttpServletResponse response) throws Exception {

      this.onPostProcess(request, response);

      if (this.postSessionToken != null && this.postSessionToken.length() > 0) {
          if (this.postSessionToken.equals(this.preSessionToken) == false) {
              this.setCookie("token", this.postSessionToken);
          }
      }
  }

  /**
   * 事後処理。
   *
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @throws Exception DBエラー、他
   */
  protected void onPostProcess(
          HttpServletRequest request, HttpServletResponse response) throws Exception {

      // サブクラスでオーバーライド
  }

  /**
   * 例外処理。
   *
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @param ex 発生した例外
   * @return HTTPステータスコード
   */
  protected int onException(HttpServletRequest request, HttpServletResponse response, Throwable ex) {

      return 500; // Internal Server Error
  }

  /**
   * セッションのトークンを取得する。
   *
   * @return セッションのトークン
   */
  public String getSessionToken() {

      if (this.postSessionToken != null && this.postSessionToken.length() > 0) {
          return this.postSessionToken;
      }
      else {
          return this.preSessionToken;
      }
  }

  /**
   * セッションのトークンを設定する。
   *
   * @param token セッションのトークン
   */
  public void setSessionToken(String token) {

      this.postSessionToken = token;
  }

  /**
   * ユーザーエージェントを取得する。
   *
   * @return ユーザーエージェント
   */
  public String getUserAgent() {

      String userAgent = this.request.getHeader("User-Agent");
      if (userAgent != null && userAgent.length() > 256) {
          userAgent = userAgent.substring(0, 256);
      }
      return userAgent;
  }

  /**
   * リクエストヘッダーの値を取得する。
   *
   * @param itemName リクエストヘッダーの項目名
   * @return リクエストヘッダーの値
   */
  public String getRequestHeader(String itemName) {

      return this.request.getHeader(itemName);
  }

  /**
   * 送信元IPアドレスを取得する。
   *
   * @return 送信元IPアドレス
   */
  public String getSourceIpAddress() {

      String ipAddress = this.request.getHeader("X-FORWARDED-FOR");
      if (ipAddress == null || ipAddress.length() == 0) {
          ipAddress = this.request.getRemoteAddr();
      }

      if (ipAddress != null && ipAddress.length() > 40) {
          ipAddress = ipAddress.substring(0, 40);
      }
      return ipAddress;
  }

  /**
   * Cookieを設定する。
   * 保存期間は365日。
   *
   * @param name Cookieの名前
   * @param value 保存する値
   */
  private void setCookie(String name, String value) {

      this.setCookie(name, value, 365 * 24 * 60 * 60);
  }

  /**
   * Cookieを設定する。
   * 保存先のパスは"/"。
   *
   * @param name Cookieの名前
   * @param value 保存する値
   * @param maxAge 保存期間(秒)
   */
  private void setCookie(String name, String value, int maxAge) {

      this.setCookie(name, value, maxAge, this.request.getContextPath());
  }

  /**
   * Cookieを設定する。
   *
   * @param name Cookieの名前
   * @param value 保存する値
   * @param maxAge 保存期間(秒)
   * @param path 保存先のパス
   */
  private void setCookie(String name, String value, int maxAge, String path) {

      Cookie cookie = new Cookie(name, value);
      cookie.setMaxAge(maxAge);
      cookie.setPath(path);
      String scheme = this.request.getHeader("X-FORWARDED-PROTO");
      if (scheme == null || scheme.length() == 0) {
          scheme = this.request.getScheme();
      }

      if (scheme != null && scheme.toLowerCase().startsWith("https")) {
          cookie.setSecure(true);
      }

      this.response.addCookie(cookie);
  }

  /**
   * 一時ディレクトリのパスを取得する。
   *
   * @return 一時ディレクトリのパス
   */
  protected String getTemporaryDirPath() {

      ServletContext servletContext = this.request.getServletContext();
      String dirPath = servletContext.getRealPath("/WEB-INF/tmp");

      File dir = new File(dirPath);
      if (dir.exists() == false) {
          dir.mkdir();
      }

      return dirPath;
  }

  /**
   * リソースのパスを取得する。
   *
   * @param resource リソース
   * @return リソースのパス
   */
  protected String getResourcePath(String resource) {

      ServletContext servletContext = this.request.getServletContext();
      return servletContext.getRealPath(resource);
  }
}
