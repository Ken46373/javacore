package javacore.presentations.filters;

import javacore.presentations.views.View;

/**
 * 抽象化Filterクラス。
 *
 * @author kenminami
 */
public abstract class Filter {

  /**
   * フィルターを実行する。
   *
   * @return View
   * @throws Exception 例外発生
   */
  public abstract View doFilter() throws Exception;
}
