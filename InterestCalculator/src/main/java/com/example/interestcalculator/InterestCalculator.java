package com.example.interestcalculator;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InterestCalculator extends Application {
//Derek Schramm
    //dsfds

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        // Set up the UI elements
        Label initialInvestmentLabel = new Label("Initial Investment:");
        TextField initialInvestmentTextField = new TextField();
        Label interestRateLabel = new Label("Interest Rate (%):");
        TextField interestRateTextField = new TextField();
        Label annualContributionLabel = new Label("Annual Contribution:");
        TextField annualContributionTextField = new TextField();
        Label timeLabel = new Label("Time (Years):");
        TextField timeTextField = new TextField();
        Button calculateButton = new Button("Calculate");
        LineChart<Number, Number> lineChart = new LineChart<>(new NumberAxis(), new NumberAxis());
        lineChart.setTitle("Investment Growth");
        lineChart.getXAxis().setLabel("Year");
        lineChart.getYAxis().setLabel("Value");
//sff
        // Set up the grid pane to layout the UI elements
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(initialInvestmentLabel, 0, 0);
        gridPane.add(initialInvestmentTextField, 1, 0);
        gridPane.add(interestRateLabel, 0, 1);
        gridPane.add(interestRateTextField, 1, 1);
        gridPane.add(annualContributionLabel, 0, 2);
        gridPane.add(annualContributionTextField, 1, 2);
        gridPane.add(timeLabel, 0, 3);
        gridPane.add(timeTextField, 1, 3);
        gridPane.add(calculateButton, 0, 4);
        gridPane.add(lineChart, 0, 5, 2, 1);

        // Set up the scene and show the stage
        Scene scene = new Scene(new Group(gridPane), 800, 600);
        stage.setScene(scene);
        stage.show();

        // Set up the calculate button action
        calculateButton.setOnAction(event -> {
            // Parse the user input
            double initialInvestment = Double.parseDouble(initialInvestmentTextField.getText());
            double interestRate = Double.parseDouble(interestRateTextField.getText()) / 100.0;
            double annualContribution = Double.parseDouble(annualContributionTextField.getText());
            double time = Double.parseDouble(timeTextField.getText());

            // Calculate the investment growth over time
            XYChart.Series<Number, Number> investedSeries = new XYChart.Series<>();
            investedSeries.setName("Invested");
            double investedValue = initialInvestment;
            for (int year = 1; year <= time; year++) {
                investedValue += annualContribution;
                investedValue *= (1.0 + interestRate);
                investedSeries.getData().add(new XYChart.Data<>(year, investedValue));
            }

            XYChart.Series<Number, Number> notInvestedSeries = new XYChart.Series<>();
            notInvestedSeries.setName("Not Invested");
            double notInvestedValue = initialInvestment;
            for (int year = 1; year <= time; year++) {
                notInvestedValue += annualContribution;
                notInvestedSeries.getData().add(new XYChart.Data<>(year, notInvestedValue));
            }

            // Add the series to the line chart
            ObservableList<XYChart.Series<Number, Number>> lineChartData = lineChart.getData();
            lineChartData.clear();
            lineChartData.add(investedSeries);
            lineChartData.add(notInvestedSeries);
        });
    }
}
