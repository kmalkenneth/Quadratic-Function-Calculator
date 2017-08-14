/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadratic.function.calculator;

import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author kenma
 */
public class Calculator {
    
    private double a;
    private double b;
    private double c;
    
    private double axisSymmetry;
    private double maximumminimum;
    private double intersectionXAxis1;
    private double intersectionXAxis2;
    private double intersectionYAxis;
    private double discriminating;
    
    private String concavity;
    private String monotony;
    private String increasing;
    private String decreasing;
    private String range;
    private String criterium;
    
    private char sign;
    
    private DecimalFormat df;

    public Calculator() {
        df = new DecimalFormat("#.00");
    }
    
    public boolean calcDiscriminating() {
    
        discriminating = ((Math.pow(b, 2)-4*a*c));
        
        return discriminating >=0;
    }
        
    public void calcConcavity(){
        
        if (a > 0){
            
            concavity = "up";
        } else if (a < 0) {
            
            concavity = "down";
        }
    }
    
    public void calcSign(){
    
        if (a > 0){
            
            sign = '+';
        } else if (a < 0) {
            
            sign = '-';
        }
    }
    
    public void calcAxisSymmetry(){
        
        axisSymmetry = (-(b)/(2*a));      
        
    }
    
    public void calcMaximumMinimum(){
    
        maximumminimum = (-(Math.pow(b, 2)-4*a*c)/(4*a));
    }
    
    public void calcX1() {
        
        intersectionXAxis1 = ((-(b) + Math.sqrt(discriminating))/2*a);
    }
    
    public void calcX2() {
        
        intersectionXAxis2 = ((-(b) - Math.sqrt(discriminating))/2*a);
    }
    
    public void calcY() {
    
        intersectionYAxis = c;
    }
    
    public void calcMonotony(){
    
        if (a > 0){
            
            monotony = "increasing";
        } else if (a < 0) {
            
            monotony = "decreasing";
        }
    }
    
    public void calcdecreasing(){
    
        if (a > 0) {
        
            decreasing = "]-∞ ," + axisSymmetry + "[";
        } else if (a < 0) {
        
            decreasing = "]" + axisSymmetry + " , +∞[";
        }
    }
    
    public void calcIncreasing(){
    
        if (a > 0) {
        
            increasing = "]" + axisSymmetry + " , +∞[";
        } else if (a < 0) {
        
            increasing = "]-∞ , " + axisSymmetry + "[";
        }
    }
    
    public void calcRange(){
    
        if (a > 0) {
        
            range = "]" + df.format(maximumminimum) + " , +∞[";
        } else if (a < 0) {
        
            range = "]-∞ , " + df.format(maximumminimum) + "[";
        }
    }

    /**
     * Calculate the series for the graph 
     * @return  
     */
    public ObservableList<XYChart.Series<Double, Double>> calcSeriesGraph (){
       
        double min;
        double max;
        
        if (intersectionXAxis1 > intersectionXAxis2){
            
            max = intersectionXAxis1 + 3;
            min = intersectionXAxis2 - 3;
        } else {
        
            max = intersectionXAxis2 + 3;
            min = intersectionXAxis1 - 3;
        }

        // Creamos un ObservableList para guardar los puntos que pintaremos en la gráfica
        ObservableList<XYChart.Series<Double, Double>> lineChartData = FXCollections.observableArrayList();

        // Instanciamos un punto a pintar
        LineChart.Series<Double, Double> series = new LineChart.Series<Double, Double>();

        // Imprimimos la función que vamos a pintar
        series.setName("f(x)= " + criterium);

        // obtenemos los puntos a pintar. Daros cuenta que los puntos a pintar estan definidos
        // por el valor de 'x' y el resultado de 'f(x)', siendo f(x)=Math.pow(x, grado) = x^grado
        for (double i = min; i<= max; i=i+0.1){ //ax2+bx+c
            series.getData().add(new XYChart.Data<Double, Double>(i, a * Math.pow(i, 2) + b * i + c));
        }

        // Guardamos todos los puntos de la función que hemos obtenido
        lineChartData.add(series);
        
        return lineChartData;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getAxisSymmetry() {
        calcAxisSymmetry();
        return axisSymmetry;
    }

    public void setAxisSymmetry(double axisSymmetry) {
        this.axisSymmetry = axisSymmetry;
    }

    public String getConcavity() {
        calcConcavity();
        return concavity;
    }

    public void setConcavity(String concavity) {
        this.concavity = concavity;
    }

    public double getMaximumminimum() {
        calcMaximumMinimum();
        return maximumminimum;
    }

    public void setMaximumminimum(double maximumminimum) {
        this.maximumminimum = maximumminimum;
    }

    public char getSign() {
        calcSign();
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public double getIntersectionXAxis1() {
        calcX1();
        return intersectionXAxis1;
    }

    public void setIntersectionXAxis1(double intersectionXAxis1) {
        this.intersectionXAxis1 = intersectionXAxis1;
    }

    public double getIntersectionXAxis2() {
        calcX2();
        return intersectionXAxis2;
    }

    public void setIntersectionXAxis2(double intersectionXAxis2) {
        this.intersectionXAxis2 = intersectionXAxis2;
    }

    public double getIntersectionYAxis() {
        calcY();
        return intersectionYAxis;
    }

    public void setIntersectionYAxis(double intersectionYAxis) {
        this.intersectionYAxis = intersectionYAxis;
    }

    public String getMonotony() {
        calcMonotony();
        return monotony;
    }

    public void setMonotony(String monotony) {
        this.monotony = monotony;
    }

    public String getIncreasing() {
        calcIncreasing();
        return increasing;
    }

    public void setIncreasing(String increasing) {
        this.increasing = increasing;
    }

    public String getDecreasing() {
        calcdecreasing();
        return decreasing;
    }

    public void setDecreasing(String decreasing) {
        this.decreasing = decreasing;
    }

    public String getRange() {
        calcRange();
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public double getDiscriminating() {
        return discriminating;
    }

    public void setDiscriminating(double discriminating) {
        this.discriminating = discriminating;
    }

    public String getCriterium() {
        return criterium;
    }

    public void setCriterium(String criterium) {
        this.criterium = criterium;
    }
    
}