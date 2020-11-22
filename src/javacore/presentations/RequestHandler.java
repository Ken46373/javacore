package javacore.presentations;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javacore.presentations.annotations.UrlMapping;

/**
 * リクエストハンドラクラス。
 *
 * @author kenminami
 */
public class RequestHandler {

  /** コントローラのクラス。 */
  private Class<?> controllerClass;

  /** 実行メソッド。 */
  private Method handlerMethod;

  /** 実行メソッドのパラメータ配列。 */
  private Parameter[] methodParameters;

  /** アノテーション。 */
  private UrlMapping urlMapping;

  /**
   * コントローラのクラスを取得する。
   *
   * @return コントローラのクラス
   */
  public Class<?> getControllerClass() {

    return this.controllerClass;
  }

  /**
   * 実行メソッドを取得する。
   *
   * @return 実行メソッド
   */
  public Method getHandlerMethod() {

      return this.handlerMethod;
  }

  /**
   * 実行メソッドのパラメータを取得する。
   *
   * @return パラメータの配列
   */
  public Parameter[] getMethodParameters() {

      return this.methodParameters;
  }

  /**
   * URLを取得する。
   *
   * @return HTTPメソッド
   */
  public String getUrl() {

      return this.urlMapping.value();
  }

  /**
   * HTTPメソッドを取得する。
   *
   * @return HTTPメソッド
   */
  public HttpMethod getHttpMethod() {

      return this.urlMapping.method();
  }

  /**
   * ajaxリクエストのみを受け付けるかどうかを取得する。
   *
   * @return ajaxリクエストのみを受け付けるかどうか
   */
  public boolean getAjaxOnly() {

      return this.urlMapping.ajaxOnly();
  }

  /**
   * ヘッダーを取得する。
   *
   * @return ヘッダー
   */
  public String[] getHeaders() {

      return this.urlMapping.headers();
  }

  /**
   * リクエストハンドラのインスタンスを生成する。
   *
   * @param controllerClass コントローラのクラス
   * @param handlerMethod   実行メソッド
   * @param urlMapping URLマッピングアノテーション
   */
  public RequestHandler(
          Class<?> controllerClass,
          Method handlerMethod,
          UrlMapping urlMapping) {

      this.controllerClass  = controllerClass;
      this.handlerMethod    = handlerMethod;
      this.methodParameters = this.handlerMethod.getParameters();
      this.urlMapping       = urlMapping;
  }
}
