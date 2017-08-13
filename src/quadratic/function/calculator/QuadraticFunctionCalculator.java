/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadratic.function.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author kenma
 */
public class QuadraticFunctionCalculator extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(QuadraticFunctionCalculator.class.getResource("myTheme.css").toExternalForm());
        scene.getStylesheets().add(QuadraticFunctionCalculator.class.getResource("bootstrap3.css").toExternalForm());
        
        stage.setTitle("Quadratic Function Calculator");
        
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
