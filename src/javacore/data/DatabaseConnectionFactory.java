package javacore.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DB接続の生成クラス。
 *
 * @author kenminami
 */
public class DatabaseConnectionFactory {

  /**
   * DB接続を生成する。
   *
   * @param url  URL
   * @param user ユーザー名
   * @param pass パスワード
   * @return DB接続
   * @throws ClassNotFoundException JDBCドライバのクラスが見つからない場合
   * @throws SQLException DB接続に失敗した場合
   */
  public static Connection create(String url, String user, String pass)
      throws ClassNotFoundException, SQLException {

    Class.forName("org.postgresql.Driver");

    return DriverManager.getConnection(url, user, pass);
  }
}
