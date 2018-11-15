package com.bcopstein.ExercicioRefatoracaoBanco;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import com.bcopstein.ExercicioRefatoracaoBanco.Negocio.Conta;
import com.bcopstein.ExercicioRefatoracaoBanco.Negocio.LogicaOperacoes;
import com.bcopstein.ExercicioRefatoracaoBanco.Negocio.Operacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaOperacoes {
	private Stage mainStage; 
	private Scene cenaOperacoes;
	private TextField tfValorOperacao;
	private TextField tfSaldo;
	private ListView<Operacao> extrato;
	private Label lbCategoria;
	private static TelaOperacoes instance; //SINGLETON
	
	private TelaOperacoes() {
	}
	
	public static TelaOperacoes getInstance() {
		if(instance == null) {
			instance = new TelaOperacoes();
		}
		return instance;
	}
	public void setStage(Stage stage) {
		this.mainStage = stage;
	}
	
	private void setResultadoOperacoes() {
		LogicaOperacoes logicaOp = LogicaOperacoes.getInstance();
		tfSaldo.setText(logicaOp.saldo());
  	  	lbCategoria.setText(logicaOp.strStatus());  //ATUALIZA A CATEGORIA
  	  	extrato.setItems(logicaOp.extratoObservable());
	}

	public Scene getTelaOperacoes() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // TITULO DA CENA
        Text scenetitle = new Text(LogicaOperacoes.getInstance().tituloConta());
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        
        // STATUS E CATEGORIA
        HBox hbCategoria = new HBox(20);    
        this.lbCategoria = new Label(LogicaOperacoes.getInstance().strStatus());
        hbCategoria.getChildren().add(new Label("Categoria: "));
        hbCategoria.getChildren().add(this.lbCategoria);
        grid.add(hbCategoria, 0, 1);

        // LIMITE DA RETIRADA DIARIA
        String limRetDiaria = "Limite retirada diaria: "+LogicaOperacoes.getInstance().limiteRetiradaDiaria();
        Label lim = new Label(limRetDiaria);
        grid.add(lim, 0, 2);
        
        Label tit = new Label("Ultimos movimentos");
        grid.add(tit,0,3);

        // Seleciona apenas o extrato da conta atual
        extrato = new ListView<>(LogicaOperacoes.getInstance().extratoObservable());
        extrato.setPrefHeight(140);
        
        grid.add(extrato, 0, 4);

        tfSaldo = new TextField();
        tfSaldo.setDisable(true);
        tfSaldo.setText(LogicaOperacoes.getInstance().saldo());
        HBox valSaldo = new HBox(20);        
        valSaldo.setAlignment(Pos.BOTTOM_LEFT);
        valSaldo.getChildren().add(new Label("Saldo"));
        valSaldo.getChildren().add(tfSaldo);
        grid.add(valSaldo, 0, 5);        

        tfValorOperacao = new TextField();
        HBox valOper = new HBox(30);        
        valOper.setAlignment(Pos.BOTTOM_CENTER);
        valOper.getChildren().add(new Label("Valor operacao"));
        valOper.getChildren().add(tfValorOperacao);
        grid.add(valOper, 1, 1);        

        Button btnCredito = new Button("Credito");
        Button btnDebito = new Button("Debito");
        Button btnVoltar = new Button("Voltar");
        Button btnEstatisticas = new Button("Estatisticas");
        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.TOP_CENTER);
        hbBtn.getChildren().add(btnCredito);
        hbBtn.getChildren().add(btnDebito);
        hbBtn.getChildren().add(btnVoltar);
        hbBtn.getChildren().add(btnEstatisticas);
        grid.add(hbBtn, 1, 2);
        
        btnCredito.setOnAction(e->{
        	try {
	      	  	double valor = Integer.parseInt(tfValorOperacao.getText());
	      	  
	          	if(LogicaOperacoes.getInstance().credito(valor)) {
	          	  	this.setResultadoOperacoes();
	          	}else{
	          		this.abreAlerta("Valor inválido !!", "Valor inválido para operacao de crédito!");
	          	}    
        	}catch(Exception ex) {
        		this.abreAlerta("Valor inválido !!", "Valor inválido para operacao de crédito!");
        	}
        });
        
        btnDebito.setOnAction(e->{
        	try {
	      	  	double valor = Integer.parseInt(tfValorOperacao.getText());
	      	  
	          	if(LogicaOperacoes.getInstance().debito(valor)) {
	          	  	this.setResultadoOperacoes();
	          	}else{
	          		this.abreAlerta("Valor inválido !!", "Valor inválido para operacao de débito!");
	          	}    
        	}catch(Exception ex) {
        		this.abreAlerta("Valor inválido !!", "Valor inválido para operacao de débito!");
        	}
        });

        btnVoltar.setOnAction(e->{
        	LogicaOperacoes.getInstance().logout();
        	mainStage.setScene(TelaEntrada.getInstance().getTelaEntrada());
        });
        btnEstatisticas.setOnAction(e->{
        	mainStage.setScene(TelaEstatisticas.getInstance().getTelaEstatisticas());
        });
		
        cenaOperacoes = new Scene(grid);
        return cenaOperacoes;
	}	
	
	private void abreAlerta(String title, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);

		alert.showAndWait();
	}

}
