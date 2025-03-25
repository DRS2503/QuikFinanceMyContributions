package com.example.epensecalculator2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ExpenseCalculatorController {

    // GUI components
    @FXML private TextField expenseField;
    @FXML private ComboBox<String> categoryComboBox;
    @FXML private DatePicker datePicker;
    @FXML private Button addButton;
    @FXML private ListView<String> expenseListView;
    @FXML private PieChart pieChart;

    // Data
    private final String[] categories = {"Food", "Transportation", "Housing", "Utilities", "Entertainment", "Other"};
    private ObservableList<String> expenseList = FXCollections.observableArrayList();

    // Initialize method
    public void initialize() {
        // Add categories to combo box
        categoryComboBox.getItems().addAll(categories);
        categoryComboBox.setValue(categories[0]);

        // Set current date to date picker
        datePicker.setValue(LocalDate.now());

        // Set expense list view items
        expenseListView.setItems(expenseList);

        // Update the pie chart
        updatePieChart();
    }

    // Set up the button event handler
    @FXML
    private void handleaddButtonAction(ActionEvent event) {
        // Get the expense details
        String expense = expenseField.getText();
        String category = categoryComboBox.getValue();
        LocalDate date = datePicker.getValue();

        // Add the expense to the list
        expenseList.add(expense + " - " + category + " - " + date);

        // Update the pie chart
        updatePieChart();

        // Clear the input fields
        expenseField.clear();
        categoryComboBox.setValue(categories[0]);
        datePicker.setValue(LocalDate.now());
    }

    // Update the pie chart
// Update the pie chart with the current expenses
    private void updatePieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        double totalAmount = 0.0;
        for (String category : categories) {
            double categoryAmount = 0.0;
            for (String expense : expenseList) {
                String[] parts = expense.split(" - ");
                if (parts[1].equals(category)) {
                    try {
                        categoryAmount += Double.parseDouble(parts[0]);
                    } catch (NumberFormatException e) {
                        // Ignore expenses that have invalid amounts
                    }
                }
            }
            if (categoryAmount > 0) {
                totalAmount += categoryAmount;
                pieChartData.add(new PieChart.Data(category + " - " + categoryAmount, categoryAmount));
            }
        }
        for (PieChart.Data data : pieChartData) {
            data.setName(data.getName() + " (" + String.format("%.2f", data.getPieValue() / totalAmount * 100) + "%)"); // Add percentage to category name
            data.setPieValue(data.getPieValue() / totalAmount * 100); // Convert to percentage
        }
        pieChart.setData(pieChartData);
    }

}
