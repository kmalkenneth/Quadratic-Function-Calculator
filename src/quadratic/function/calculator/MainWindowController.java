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
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void calculate(ActionEvent event) {
        
        int grado = 2;
        pintarGrafica(Double.parseDouble(a.getText()), Double.parseDouble(b.getText()), Double.parseDouble(c.getText()), -1, 6, grado);
    }
    
    /**
     * Método para pintar la gráfica
     * @param min
     * @param max
     * @param grado 
     */
    private void pintarGrafica (double a, double b, double c, double min, double max, int grado){

        // Creamos un ObservableList para guardar los puntos que pintaremos en la gráfica
        ObservableList<XYChart.Series<Double, Double>> lineChartData = FXCollections.observableArrayList();

        // Instanciamos un punto a pintar
        LineChart.Series<Double, Double> series = new LineChart.Series<Double, Double>();

        // Imprimimos la función que vamos a pintar
        series.setName("f(x^"+grado+")");

        // obtenemos los puntos a pintar. Daros cuenta que los puntos a pintar estan definidos
        // por el valor de 'x' y el resultado de 'f(x)', siendo f(x)=Math.pow(x, grado) = x^grado
        for (double i = min; i<max; i=i+0.1){ //ax2+bx+c
            series.getData().add(new XYChart.Data<Double, Double>(i, a * Math.pow(i, grado) + b * i + c));
            System.out.println("(" + i + "," + Math.pow(i, grado) + ")");
        }

        // Guardamos todos los puntos de la función que hemos obtenido
        lineChartData.add(series);

        // Si No quereis que se pinten los puntos, poner a false
        graph.setCreateSymbols(true);

        // Ponemos los puntos en la gráfica
        graph.setData(lineChartData);
        graph.createSymbolsProperty();
    }
}
