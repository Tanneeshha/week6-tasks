package project;

import java.sql.*;

public class DatabaseHandler {
    static final String URL = "jdbc:sqlite:finance.db";

    public static void createTables() {
        String createIncomeTable = "CREATE TABLE IF NOT EXISTS income (amount REAL)";
        String createExpenseTable = "CREATE TABLE IF NOT EXISTS expenses (amount REAL)";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createIncomeTable);
            stmt.execute(createExpenseTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
