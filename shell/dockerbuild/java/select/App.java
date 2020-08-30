package training.tcmobile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

  public static void main(String[] args) {

    final String database = System.getenv("DB_DATABASE");
    final String dsn = String.format("jdbc:mysql://database/%s?useSSL=false", database);
    final String user = System.getenv("DB_USER");
    final String password = System.getenv("DB_PASSWORD");

    try (final Connection conn = DriverManager.getConnection(dsn, user, password)) {
      read(conn);
    } catch (SQLException e) {
      handleError(e);
    }
  }

  private static void read(final Connection conn) {
    try (final Statement stmt = conn.createStatement()) {
      final String sql = "SELECT * FROM persons";
      final ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        System.out.print(rs.getString("name"));
        System.out.print(",");
        System.out.print(rs.getInt("age"));
        System.out.print(",");
      }
    } catch (Exception e) {
      handleError(e);
    }
  }

  private static void handleError(final Exception e) {
    System.out.println(e.getMessage());
    e.printStackTrace();
  }
}