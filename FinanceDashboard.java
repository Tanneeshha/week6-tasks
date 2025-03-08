package project;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FinanceDashboard extends Application {
    @Override
    public void start(Stage primaryStage) {
        DatabaseHandler.createTables();
        
        Label title = new Label("Finance Management Dashboard");
        title.setFont(new Font("Arial", 24));
        title.setTextFill(Color.DARKBLUE);
        
        Label incomeLabel = new Label("Income:");
        TextField incomeField = new TextField();
        Button addIncomeBtn = new Button("Add Income");
        incomeField.setPromptText("Enter income amount");
        addIncomeBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        
        Label expenseLabel = new Label("Expense:");
        TextField expenseField = new TextField();
        Button addExpenseBtn = new Button("Add Expense");
        expenseField.setPromptText("Enter expense amount");
        addExpenseBtn.setStyle("-fx-background-color: #FF5733; -fx-text-fill: white; -fx-font-size: 14px;");
        
        Tooltip.install(incomeField, new Tooltip("Enter your total income"));
        Tooltip.install(expenseField, new Tooltip("Enter your total expenses"));
        
        addIncomeBtn.setOnAction(_ -> {
            IncomeTracker.addIncome(Double.parseDouble(incomeField.getText()));
            incomeField.clear();
        });
        
        addExpenseBtn.setOnAction(_ -> {
            ExpenseTracker.addExpense(Double.parseDouble(expenseField.getText()));
            expenseField.clear();
        });
        
        PieChart chart = new PieChart();
        Button showChartBtn = new Button("Show Report");
        showChartBtn.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white; -fx-font-size: 14px;");
        
        showChartBtn.setOnAction(_ -> {
            chart.getData().clear();
            double income = IncomeTracker.getTotalIncome();
            double expenses = ExpenseTracker.getTotalExpenses();
            PieChart.Data incomeData = new PieChart.Data("Income", income);
            PieChart.Data expenseData = new PieChart.Data("Expenses", expenses);
            chart.getData().addAll(incomeData, expenseData);
            
            // Fade in animation for chart
            FadeTransition fade = new FadeTransition(Duration.seconds(1), chart);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play();
        });
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        
        grid.add(incomeLabel, 0, 0);
        grid.add(incomeField, 1, 0);
        grid.add(addIncomeBtn, 2, 0);
        grid.add(expenseLabel, 0, 1);
        grid.add(expenseField, 1, 1);
        grid.add(addExpenseBtn, 2, 1);
        grid.add(showChartBtn, 1, 2);
        
        VBox layout = new VBox(20, title, grid, chart);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f4f4f4;");
        
        Scene scene = new Scene(layout, 550, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Finance Management");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}