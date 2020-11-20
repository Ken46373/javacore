package javacore.data;

import java.sql.Connection;

/**
 * テーブルアクセスの抽象クラス。
 *
 * @author kenminami
 */
public abstract class AbstractAccessor {

  /** DB接続。 */
  protected Connection connection;

  /**
   * コンストラクタ。
   *
   * @param connection DBへの接続
   */
  public AbstractAccessor(Connection connection) {

    this.connection = connection;
  }
}
