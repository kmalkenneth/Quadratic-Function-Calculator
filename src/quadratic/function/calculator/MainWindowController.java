/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadratic.function.calculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author kenma
 */
public class MainWindowController implements Initializable {
    
    private Calculator calc;
    
    private Label label;
    @FXML
    private TextField criterion;
    @FXML
    private TextField a;
    @FXML
    private TextField b;
    @FXML
    private TextField c;
    @FXML
    private LineChart<Double, Double> graph;
    @FXML
    private NumberAxis axisX;
    @FXML
    private NumberAxis axisY;
    @FXML
    private Label concavity;    
    @FXML
    private Label axisSymetry;
    @FXML
    private Label maximumMinimum;
    @FXML
    private Label vertex;
    @FXML
    private Label sign;
    @FXML
    private Label intersectionXAxis;
    @FXML
    private Label intersectionYAxis;
    @FXML
    private Label monotony;
    @FXML
    private Label increasing;
    @FXML
    private Label decreasing;
    @FXML
    private Label range;
    @FXML
    private Label domain;
    @FXML
    private Label codomain;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        calc = new Calculator();
        
        a.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!a.getText().equals("") && !a.getText().equals("-")) {
                calc.setA(Double.parseDouble(a.getText()));
            }
        });
        
        b.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!b.getText().equals("") && !b.getText().equals("-")) {
                calc.setB(Double.parseDouble(b.getText()));
            }
        });
        
        c.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!c.getText().equals("") && !c.getText().equals("-")) {
                calc.setC(Double.parseDouble(c.getText()));
            }
        });
    }    

    @FXML
    private void calculate(ActionEvent event) {
        
        concavity.setText("Concavity: " + calc.getConcavity());
        
        sign.setText("Sign: " + calc.getSign());
        
        axisSymetry.setText("Axis of symmetry: (" + calc.getAxisSymmetry() + " , 0)");
        
        maximumMinimum.setText("Maximum or minimum: (0 , " + calc.getMaximumminimum() + ")");
         
        vertex.setText("Vertex: (" + calc.getAxisSymmetry() + " , " + calc.getMaximumminimum() + ")");
        
        intersectionXAxis.setText("Intersection with the x-axis: (" + calc.getIntersectionXAxis1() + " , 0) (" + calc.getIntersectionXAxis2() + " , 0)" );
        intersectionYAxis.setText("Intersection with the y-axis: (0 , " + calc.getIntersectionYAxis() + ")" );
        
        monotony.setText("Monotony: " + calc.getMonotony());
        
        increasing.setText("Increasing: " + calc.getIncreasing());
        decreasing.setText("Decreasing: " + calc.getDecreasing());
        
        range.setText("Range: " + calc.getRange());
        
        domain.setText("Domain: R");
        codomain.setText("Codomain: R");
        
        //To graph
        graph.setCreateSymbols(false);        
        graph.setData(calc.calcSeriesGraph());
        graph.createSymbolsProperty();
    }
}
