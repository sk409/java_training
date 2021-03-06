package training.tcmobile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

  public static void main(String[] args) {

    final String database = System.getenv("DB_DATABASE");
    final String dsn = String.format("jdbc:mysql://database/%s?useSSL=false", database);
    final String user = System.getenv("DB_USER");
    final String password = System.getenv("DB_PASSWORD");

    try(final Connection conn = DriverManager.getConnection(dsn, user, password)) {
      if (delete(conn)) {
        count(conn);
      }
    } catch (SQLException e) {
      handleError(e);
    }
  }

  private static boolean delete(final Connection conn) {
    final String sql = "DELETE FROM persons WHERE name = ?";
    try (final PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, "person1");
      pstmt.executeUpdate();
      return true;
    } catch(Exception e) {
      handleError(e);
      return false;
    }
  }

  private static void count(final Connection conn) {
    try (final Statement stmt = conn.createStatement()) {
      final String sql = "SELECT COUNT(*) FROM persons";
      final ResultSet rs = stmt.executeQuery(sql);
      rs.next();
      final int count = rs.getInt(1);
      System.out.println("=====削除後のデータ数=====");
      System.out.println(count);  // 2ならOK
      System.out.println("=======================");
    } catch(Exception e) {
      handleError(e);
    }
  }

  private static void handleError(final Exception e) {
    System.out.println(e.getMessage());
    e.printStackTrace();
  }
}