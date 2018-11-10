package com.bcopstein.ExercicioRefatoracaoBanco;

import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

public class TelaEstatisticas {
	private Stage mainStage; 
	private Scene cenaEstatisticas;
	
	private List<Operacao> operacoes;

	private TextField tfMes;
	private TextField tfAno;

	private TextField tfSaldoMedio;
	private TextField tfTotalDeCreditos;
	private TextField tfQuantidadeDeCreditos;
	private TextField tfTotalDeDebitos;
	private TextField tfQuantidadeDeDebitos;
	
	public TelaEstatisticas(Stage mainStage, List<Operacao> operacoes) {
		this.mainStage = mainStage;
		this.cenaEstatisticas = null;
		this.tfSaldoMedio = new TextField();
		this.tfTotalDeCreditos = new TextField();
		this.tfQuantidadeDeCreditos = new TextField();
		this.tfTotalDeDebitos = new TextField();
		this.tfQuantidadeDeDebitos = new TextField();
		
		this.operacoes = operacoes;
	}

	public Scene getTelaEstatisticas() {
		// INICIALIZA OS RESULTADO ZERADOS
		this.resetaResultados();
		
		// ------------------- INICIO PAINEL GERAL ------------------------ 
		// OBS: PODE VIRAR UM MÉTODO DE UMA CLASSE PAI
		GridPane grid = new GridPane();
		grid.setMinWidth(500);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Text scenetitle = new Text("Estatísticas");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		
		
		// -------------- INICIO FORMULARIO --------------
		GridPane form = new GridPane();
		form.setHgap(10);
		form.setVgap(10);
		form.setPadding(new Insets(0, 0, 0, 0));
		
		Label tituloForm = new Label("Pesquise as estatísticas da sua conta:");
		
		HBox hbData = new HBox();
		this.tfMes = new TextField();
		this.tfAno = new TextField();
		Button btnPesquisar = new Button("Pesquisar");
		btnPesquisar.setOnAction(e->{
			this.carregaResultados();
		});
		hbData.getChildren().add(this.tfMes);
		hbData.getChildren().add(this.tfAno);
		hbData.getChildren().add(btnPesquisar);
		form.add(tituloForm, 0, 0);
		form.add(hbData, 0, 1);
		
		// -------------- FINAL FORMULARIO --------------

		// -------------- INICIO RESULTADO --------------
		GridPane gridResultado = new GridPane();
		gridResultado.setAlignment(Pos.CENTER);
		gridResultado.setHgap(10);
		gridResultado.setVgap(10);
		gridResultado.setPadding(new Insets(0, 0, 0, 0));

		gridResultado.add(new Label("Saldo médio:"), 0 , 0);
		this.tfSaldoMedio.setDisable(true);
        gridResultado.add(this.tfSaldoMedio, 1 , 0);
		gridResultado.add(new Label("Total de créditos:"), 0 , 1);
		this.tfTotalDeCreditos.setDisable(true);
        gridResultado.add(this.tfTotalDeCreditos, 1 , 1);
		gridResultado.add(new Label("Quantidade de créditos:"), 2 , 1);
		this.tfQuantidadeDeCreditos.setDisable(true);
		gridResultado.add(this.tfQuantidadeDeCreditos, 3 , 1);
		gridResultado.add(new Label("Total de débitos:"), 0 , 2);
		this.tfTotalDeDebitos.setDisable(true);
		gridResultado.add(this.tfTotalDeDebitos, 1 , 2);
		gridResultado.add(new Label("Quantidade de débitos:"), 2 , 2);
		this.tfQuantidadeDeDebitos.setDisable(true);
		gridResultado.add(this.tfQuantidadeDeDebitos, 3 , 2);
		// -------------- FINAL RESULTADO --------------
		// ------------------- FINAL PAINEL GERAL ------------------------
		
		 
		grid.add(scenetitle, 0, 0);
		grid.add(form, 0, 1);
		grid.add(gridResultado, 0, 2);
		
		this.cenaEstatisticas = new Scene(grid);
		
		return this.cenaEstatisticas;
	}
	
	private void resetaResultados() {
		this.tfSaldoMedio.setText("0");
		this.tfTotalDeCreditos.setText("0");
		this.tfQuantidadeDeCreditos.setText("0");
		this.tfTotalDeDebitos.setText("0");
		this.tfQuantidadeDeDebitos.setText("0");
	}

	private void carregaResultados() {
		List<Operacao> lista = this.retornaOperacoesDoMesAno(Integer.parseInt(this.tfMes.getText()), Integer.parseInt(this.tfAno.getText()));

		double saldoMedio = 0;
		double totalCreditos = 0;
		double totalDebitos = 0;
		int qtdCreditos = 0;
		int qtdDebitos = 0;
		
		for( Operacao op : lista ) {			
			saldoMedio += op.getValorOperacao();
			
			if(op.getTipoOperacao() == op.CREDITO) {
				totalCreditos += op.getValorOperacao();
				qtdCreditos++;
			}else if(op.getTipoOperacao() == op.DEBITO) {
				totalDebitos += op.getValorOperacao();
				qtdDebitos++;
			}
		}
		saldoMedio = !lista.isEmpty() ? saldoMedio / lista.size() : 0;
		
		this.tfSaldoMedio.setText(saldoMedio+"");
		this.tfTotalDeCreditos.setText(totalCreditos+"");
		this.tfQuantidadeDeCreditos.setText(qtdCreditos+"");
		this.tfTotalDeDebitos.setText(totalDebitos+"");
		this.tfQuantidadeDeDebitos.setText(qtdDebitos+"");
	}
	
	private List<Operacao> retornaOperacoesDoMesAno(int mes, int ano){
		return this.operacoes
				.stream()
				.filter( (e) -> e.getAno() == ano && e.getMes() == mes )
				.collect(Collectors.toList());
	}
}

