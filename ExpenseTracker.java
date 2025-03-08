package project;

import java.sql.*;

public class ExpenseTracker {
    public static void addExpense(double amount) {
        String sql = "INSERT INTO expenses (amount) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(DatabaseHandler.URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, amount);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static double getTotalExpenses() {
        String sql = "SELECT SUM(amount) FROM expenses";
        try (Connection conn = DriverManager.getConnection(DatabaseHandler.URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
