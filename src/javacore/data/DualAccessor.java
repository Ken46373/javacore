package javacore.data;

import java.sql.Connection;

/**
 * from句を必要としないクエリのアクセスクラス。
 *
 * @author kenminami
 */
public class DualAccessor extends AbstractAccessor {

  /**
   * コンストラクタ。
   *
   * @param connection DB接続
   */
  public DualAccessor(Connection connection) {

    super(connection);
  }

  /**
   * システムの稼働状態を確認する。
   *
   * @throws Exception エラー発生
   */
  public void healthCheck() throws Exception {

    // TODO
  }
}
