package javacore.presentations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * パラメータマッピングアノテーション。
 *
 * @author kenminami
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamMapping {

  /**
   * パラメータ名。
   *
   * @return パラメータ名
   */
  String name() default "";

  /**
   * 必須かどうか。
   *
   * @return 必須かどうか
   */
  boolean required() default false;
}
