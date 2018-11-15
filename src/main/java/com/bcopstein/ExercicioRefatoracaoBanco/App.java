package com.bcopstein.ExercicioRefatoracaoBanco;
import java.util.List;
import java.util.Map;

import com.bcopstein.ExercicioRefatoracaoBanco.Negocio.Conta;
import com.bcopstein.ExercicioRefatoracaoBanco.Negocio.LogicaOperacoes;
import com.bcopstein.ExercicioRefatoracaoBanco.Negocio.Operacao;
import com.bcopstein.ExercicioRefatoracaoBanco.Persistencia.Persistencia;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;


public class App extends Application {
	private TelaEntrada telaEntrada;
	
    @Override
    public void start(Stage primaryStage) {
    	this.setStages(primaryStage);
    	
    	primaryStage.setTitle("$$ Banco NOSSA GRANA $$");

        primaryStage.setScene(TelaEntrada.getInstance().getTelaEntrada());
        
        primaryStage.show();
    }
    
    private void setStages(Stage stage) {
    	TelaEntrada.getInstance().setStage(stage);
    	TelaEstatisticas.getInstance().setStage(stage);
    	TelaOperacoes.getInstance().setStage(stage);
    }
    
    @Override
    public void stop() {
        LogicaOperacoes.getInstance().stop();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}