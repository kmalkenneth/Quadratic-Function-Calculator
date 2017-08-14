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
        
        criterion.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            criterion.setText(newValue.replaceAll("[qwertyuiopasdfghjklñzcvbnm]", ""));
            }   
        });
    }

public boolean isCorret(String t){

    boolean state = false;
   
    if(!t.equals("") &&
            !t.equals("+") &&
            !t.equals("/") &&
            !t.equals("*") &&
            !t.equals("(") &&
            !t.equals(")")){
        
        }
    
    return state;
}

    @FXML
    private void calculate(ActionEvent event) {
        
        calc.setA(0);
        calc.setB(0);
        calc.setC(0);
        
        //verify a
        if (criterion.getText().contains("x²")){
            
            String[] parts = criterion.getText().split("x²");
                        
            if (parts.length == 0){
                calc.setA(1);
            }else {
            
                if (parts[0].equals("-")){

                    calc.setA(-1);
                } else if (parts[0].equals("")){
                
                    calc.setA(1);
                } else {
                    calc.setA(Double.parseDouble(parts[0]));
                }

                //verefy b
                if (parts.length > 1){

                    if (parts[1].contains("x¹")){

                        String[] parts2 = parts[1].split("x¹");

                        if (parts2[0].equals("-")){

                            calc.setB(-1);
                        } else if (parts2[0].equals("+")){

                            calc.setB(1);
                        } else {

                            calc.setB(Double.parseDouble(parts[0]));
                        }

                        if (parts2.length > 1){

                            calc.setC(Double.parseDouble(parts2[1]));
                        }
                    } else if (parts[1].contains("x")){

                        String[] parts2 = parts[1].split("x¹");

                        if (parts2[0].equals("-")){

                            calc.setB(-1);
                        } else if (parts2[0].equals("+")){

                            calc.setB(1);
                        } else {

                            calc.setB(Double.parseDouble(parts[0]));
                        }

                        if (parts2.length > 1){

                            calc.setC(Double.parseDouble(parts2[1]));
                        }

                        //verefy c
                    } else {

                        if (parts[1].equals("-")){

                            calc.setC(-1);
                        } else {

                            calc.setC(Double.parseDouble(parts[1]));
                        }
                    }
                }
            }
            
            
            
        }
       
        a.setText(String.valueOf(calc.getA()));
        b.setText(String.valueOf(calc.getB()));
        c.setText(String.valueOf(calc.getC()));
        
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
        
        criterion.requestFocus();
    }

    @FXML
    private void clear(ActionEvent event) {
        
        criterion.setText("");
        criterion.requestFocus();
    }

    @FXML
    private void divide(ActionEvent event) {
        
       criterion.setText(criterion.getText() + "/");
        criterion.requestFocus();
    }

    @FXML
    private void multiply(ActionEvent event) {
        criterion.setText(criterion.getText() + "*");
        criterion.requestFocus();
    }

    @FXML
    private void less(ActionEvent event) {
        criterion.setText(criterion.getText() + "-");
        criterion.requestFocus();
    }

    @FXML
    private void plus(ActionEvent event) {
        criterion.setText(criterion.getText() + "+");
        criterion.requestFocus();
    }

    @FXML
    private void x2(ActionEvent event) {
        criterion.setText(criterion.getText() + "x²");
        criterion.requestFocus();
    }

    @FXML
    private void x1(ActionEvent event) {
        criterion.setText(criterion.getText() + "x¹");        
        criterion.requestFocus();
    }

    @FXML
    private void fraction(ActionEvent event) {
        criterion.setText(criterion.getText() + "(?/?)");        
        criterion.requestFocus();
    }

    @FXML
    private void root(ActionEvent event) {
        criterion.setText(criterion.getText() + "√(?)");        
        criterion.requestFocus();
    }

    @FXML
    private void initGrup(ActionEvent event) {
        criterion.setText(criterion.getText() + "(");        
        criterion.requestFocus();
    }

    @FXML
    private void finallyGrup(ActionEvent event) {
        criterion.setText(criterion.getText() + ")");        
        criterion.requestFocus();
    }
}
