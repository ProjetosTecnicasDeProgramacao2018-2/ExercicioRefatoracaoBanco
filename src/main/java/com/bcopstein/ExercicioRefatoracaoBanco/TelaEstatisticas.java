package com.bcopstein.ExercicioRefatoracaoBanco;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaEstatisticas {
	private Stage mainStage; 
	private Scene cenaEstatisticas;

	public TelaEstatisticas(Stage mainStage) {
		this.mainStage = mainStage;
		this.cenaEstatisticas = null;
	}

	public Scene getTelaEstatisticas() {
		// ------------------- INICIO PAINEL GERAL ------------------------
		// OBS: PODE VIRAR UM MÉTODO DE UMA CLASSE PAI
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setMinWidth(500);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Text scenetitle = new Text("Estatísticas");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		
		
		// -------------- INICIO FORMULARIO --------------
		GridPane form = new GridPane();
		form.setAlignment(Pos.CENTER);
		form.setHgap(10);
		form.setVgap(10);
		form.setPadding(new Insets(0, 0, 0, 0));
		
		Label tituloForm = new Label("Pesquise as estatísticas da sua conta:");
		form.add(tituloForm, 0, 0);
		
		
		// -------------- FINAL FORMULARIO --------------
		
		// ------------------- FINAL PAINEL GERAL ------------------------
		
		 
		grid.add(scenetitle, 0, 0);
		grid.add(form, 0, 1);
		
		this.cenaEstatisticas = new Scene(grid);
		
		return this.cenaEstatisticas;
	}
}

