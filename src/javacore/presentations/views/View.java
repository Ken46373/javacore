package javacore.presentations.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 抽象化Viewクラス。
 *
 * @author kenminami
 */
public abstract class View {

  /** ステータスコード */
  protected final int statusCode;

  /**
   * コンストラクタ。
   *
   * @param statusCode ステータスコード
   */
  public View(int statusCode) {

    this.statusCode = statusCode;
  }

  /**
   * ステータスコードを取得する。
   *
   * @return ステータスコード
   */
  public int getStatusCode() {

    return this.statusCode;
  }

  /**
   * Viewをレンダリングする。
   *
   * @param request  HTTPリクエスト
   * @param response HTTPレスポンス
   * @throws Exception レンダリング失敗
   */
  public abstract void render(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
