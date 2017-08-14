/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadratic.function.calculator;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author kenma
 */
public class MainWindowController implements Initializable {
    
    private Calculator calc;
    
    private DecimalFormat df;
    
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
        
        df = new DecimalFormat("#.00");
        
        TextFields.bindAutoCompletion(criterion,"x²", "x¹");        
        
        criterion.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            criterion.setText(newValue.replaceAll("[qwertyuiopasdfghjklñzcvbnm]", ""));
            }   
        });
    }

    @FXML
    private void calculate(ActionEvent event) {
        
        graph.setTitle("Graph of the function");
        
        calc.setA(0);
        calc.setB(0);
        calc.setC(0);
        
        evaluate();
        
        if (!calc.calcDiscriminating()){
        
            clear(event);
            graph.setTitle("The discriminant is less than zero. Has no real solution.");
        }
        
        update();
        focus();
        
    }

    @FXML
    private void clear(ActionEvent event) {
        
        a.setText("");
        b.setText("");
        c.setText("");
        
        concavity.setText("Concavity: ");
        
        sign.setText("Sign: ");
        
        axisSymetry.setText("Axis of symmetry: ");
        
        maximumMinimum.setText("Maximum or minimum: ");
         
        vertex.setText("Vertex: ");
        
        intersectionXAxis.setText("Intersection with the x-axis: ");
        intersectionYAxis.setText("Intersection with the y-axis: ");
        
        monotony.setText("Monotony: ");
        
        increasing.setText("Increasing: ");
        decreasing.setText("Decreasing: " );
        
        range.setText("Range: " );
        
        domain.setText("Domain: ");
        
        graph.getData().clear();
        
        criterion.setText("");
        criterion.requestFocus();
    }

    @FXML
    private void divide(ActionEvent event) {
        
        criterion.setText(criterion.getText() + "/");
        focus();
    }

    @FXML
    private void multiply(ActionEvent event) {
        criterion.setText(criterion.getText() + "*");
        focus();
    }

    @FXML
    private void less(ActionEvent event) {
        criterion.setText(criterion.getText() + "-");
        focus();
    }

    @FXML
    private void plus(ActionEvent event) {
        criterion.setText(criterion.getText() + "+");
        focus();
    }

    @FXML
    private void x2(ActionEvent event) {
        criterion.setText(criterion.getText() + "x²");
        focus();
    }

    @FXML
    private void x1(ActionEvent event) {
        criterion.setText(criterion.getText() + "x¹");        
        focus();
    }

    @FXML
    private void fraction(ActionEvent event) {
        criterion.setText(criterion.getText() + "(?/?)");        
        focus();
    }

    @FXML
    private void root(ActionEvent event) {
        criterion.setText(criterion.getText() + "√(?)");        
        focus();
    }

    @FXML
    private void initGrup(ActionEvent event) {
        criterion.setText(criterion.getText() + "(");        
        focus();
        
    }

    @FXML
    private void finallyGrup(ActionEvent event) {
        criterion.setText(criterion.getText() + ")"); 
        focus();
    }
    
    private void focus(){
        criterion.requestFocus();        
        criterion.positionCaret(criterion.getText().length());
    }
    
    private void update(){
        
        a.setText(String.valueOf(calc.getA()));
        b.setText(String.valueOf(calc.getB()));
        c.setText(String.valueOf(calc.getC()));
        
        concavity.setText("Concavity: " + calc.getConcavity());
        
        sign.setText("Sign: " + calc.getSign());
        
        axisSymetry.setText("Axis of symmetry: (" + df.format(calc.getAxisSymmetry()) + " , 0)");
        
        maximumMinimum.setText("Maximum or minimum: (0 , " + df.format(calc.getMaximumminimum()) + ")");
         
        vertex.setText("Vertex: (" + df.format(calc.getAxisSymmetry()) + " , " + df.format(calc.getMaximumminimum()) + ")");
        
        intersectionXAxis.setText("Intersection with the x-axis: (" + df.format(calc.getIntersectionXAxis1()) + " , 0) (" + df.format(calc.getIntersectionXAxis2()) + " , 0)" );
        intersectionYAxis.setText("Intersection with the y-axis: (0 , " + df.format(calc.getIntersectionYAxis()) + ")" );
        
        monotony.setText("Monotony: " + calc.getMonotony());
        
        increasing.setText("Increasing: " + calc.getIncreasing());
        decreasing.setText("Decreasing: " + calc.getDecreasing());
        
        range.setText("Range:  " + calc.getRange());
        
        domain.setText("Domain: R");
        codomain.setText("Codomain: R");
        
        //To graph
        calc.setCriterium(criterion.getText());
        graph.setCreateSymbols(false);        
        graph.setData(calc.calcSeriesGraph());
        graph.createSymbolsProperty();
        
        criterion.requestFocus();
    }
    
    private void evaluate(){
    
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        engine.put("X", 3);
        try {
            
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

                        double t = Double.parseDouble(String.valueOf(engine.eval(parts[0])));
                        calc.setA(t);
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

                                double t = Double.parseDouble(String.valueOf(engine.eval(parts2[0])));
                                calc.setB(t);
                            }

                            if (parts2.length > 1){

                                double t = Double.parseDouble(String.valueOf(engine.eval(parts2[1])));
                                calc.setC(t);
                            }
                        } else if (parts[1].contains("x")){

                            String[] parts2 = parts[1].split("x¹");

                            if (parts2[0].equals("-")){

                                calc.setB(-1);
                            } else if (parts2[0].equals("+")){

                                calc.setB(1);
                            } else {

                                double t = Double.parseDouble(String.valueOf(engine.eval(parts2[0])));
                                calc.setB(t);
                            }

                            if (parts2.length > 1){

                                double t = Double.parseDouble(String.valueOf(engine.eval(parts2[1])));
                                calc.setC(t);
                            }

                            //verefy c
                        } else {

                                double t = Double.parseDouble(String.valueOf(engine.eval(parts[1])));
                                calc.setC(t);
                        }
                    }
                }            
            }
        } catch (Exception e) {
        }
    }
}
