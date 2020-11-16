package javacore.presentations;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTPメソッドのenum
 *
 * @author kenminami
 */
public enum HttpMethod {

  GET, POST, PUT, DELETE, PATCH, ANY;

  private static final Map<String, HttpMethod> HTTP_MAP = new HashMap<String, HttpMethod>();

  static {
    for (HttpMethod method : values()) {
      HTTP_MAP.put(method.name(), method);
    }
  }
}
