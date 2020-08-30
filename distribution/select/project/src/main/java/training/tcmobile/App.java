package training.tcmobile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

  public static void main(String[] args) {

    final String dsn = "jdbc:mysql://database/training?useSSL=false";
    /*********************************************/
    // 本来はDBのユーザ名はパスワードはソースコード内に書くべきではありません。
    // 第3者にソースコードを閲覧されてしまった場合に漏洩するからです。
    // 基本的には環境変数や.envから取得します。
    final String user = "root";
    final String password = "root";
    /*********************************************/

    try (final Connection conn = DriverManager.getConnection(dsn, user, password)) {
      read(conn);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  private static void read(final Connection conn) {
    try (final Statement stmt = conn.createStatement()) {
      final String sql = "SELECT * FROM persons";
      final ResultSet rs = stmt.executeQuery(sql);
      System.out.println("=====取得したユーザ=====");
      while (rs.next()) {
        System.out.println("名前: " + rs.getString("name"));
        System.out.println("年齢: " + rs.getInt("age"));
      }
      System.out.println("======================");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }
}