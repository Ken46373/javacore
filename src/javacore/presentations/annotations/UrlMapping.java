package javacore.presentations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javacore.presentations.HttpMethod;

/**
 * URLマッピングアノテーション。
 *
 * @author kenminami
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UrlMapping {

  /**
   * URL。
   *
   * @return URL
   */
  String value() default "";

  /**
   * HTTPメソッド。
   *
   * @return HTTPメソッド
   */
  HttpMethod method() default HttpMethod.ANY;

  /**
   * ajaxリクエストのみ受け付けるかどうか。
   *
   * @return ajaxリクエストのみ受け付けるかどうか
   */
  boolean ajaxOnly() default false;

  /**
   * ヘッダー。
   *
   * @return ヘッダー
   */
  String[] headers() default {};
}
