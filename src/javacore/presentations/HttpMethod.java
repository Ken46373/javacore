package javacore.presentations;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTPメソッドのenum
 *
 * @author kenminami
 */
public enum HttpMethod {

  GET, POST, PUT, DELETE, PATCH, HEAD, OPTIONS,TRACE, ANY;

  /** HTTPメソッドマップ */
  private static final Map<String, HttpMethod> HTTP_MAP = new HashMap<String, HttpMethod>();

  static {
    for (HttpMethod method : values()) {
      HTTP_MAP.put(method.name(), method);
    }
  }

  /**
   * HTTPメソッドの名前を取得する。
   *
   * @param method HTTPメソッド文字列
   * @return HTTPメソッド名
   */
  public static HttpMethod resolve(String method) {

    return (method != null ? HTTP_MAP.get(method) : null);
  }

  /**
   * HTTPメソッドに該当するかどうかを判定する。
   *
   * @param method HTTPメソッド文字列
   * @return 該当するかどうか
   */
  public boolean matches(String method) {

    return (this == resolve(method));
  }
}
