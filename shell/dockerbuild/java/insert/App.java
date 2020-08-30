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
      if (insert(conn)) {
        read(conn);
      }
    } catch (SQLException e) {
      handleError(e);
    }
  }

  private static boolean insert(final Connection conn) {
    final String sql = "INSERT INTO persons(name, age) VALUES(?, ?)";
    try (final PreparedStatement pstmt = conn.prepareStatement(sql)) {
      // nameにperson4をセットしてください
      pstmt.setString(1, "person4");
      // ageに28をセットしてください
      pstmt.setInt(2, 28);
      pstmt.executeUpdate();
      return true;
    } catch(Exception e) {
      handleError(e);
      return false;
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
      handleError(e);
    }
  }

  private static void handleError(final Exception e) {
    System.out.println(e.getMessage());
    e.printStackTrace();
  }
}