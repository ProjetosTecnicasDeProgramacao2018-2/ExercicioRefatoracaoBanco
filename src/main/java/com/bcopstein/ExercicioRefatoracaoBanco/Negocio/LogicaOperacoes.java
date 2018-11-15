package com.bcopstein.ExercicioRefatoracaoBanco.Negocio;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LogicaOperacoes {
	private static LogicaOperacoes instance; //SINGLETON
	
	/*
	 	-------------- CONTEUDO DESTA CLASSE -------------- 
	 	Definir conta em uso
		Operaçao de crédito
		Operacao de débito
		Solicita extrato
		Solicita saldo
		Solicita saldo medio
		Total créditos
		Total débitos
		VALIDACOES: criar uma classe (singleton) para validacoes dos limites diários de saques.
	*/
	
	private LogicaOperacoes() {
		// TODO Auto-generated constructor stub
	}
	
	// FUNÇÃO CHAMADA QUANDO É ENCERRADO O SISTEMA
	public void stop() {
		Contas.getInstance().save();
		Operacoes.getInstance().save();
		this.logout();
	}
	
	// -------------- INICIO OPERAÇÕES DE LOGIN --------------
	
	public boolean login(Integer numConta) {
		return Contas.getInstance().autenticaLogin(numConta);
	}
	
	public void logout() {
		Contas.getInstance().logout();
	}
	
	// -------------- FINAL OPERAÇÕES DE LOGIN --------------
	
	// -------------- INICIO OPERAÇÕES DA CONTA ATUAL --------------
	
	public boolean debito(double valor) {
		return Operacoes.getInstance().debito(valor);
	}
	
	public boolean credito(double valor) {
		return Operacoes.getInstance().credito(valor);
	}
	
	public List<Operacao> extrato() {
		return Operacoes.getInstance().extrato();
	}
	
	public ObservableList<Operacao> extratoObservable(){
		return FXCollections.observableArrayList(this.extrato());
	}

	// -------------- FINAL OPERAÇÕES DA CONTA ATUAL --------------
	
	// -------------- INICIO DADOS CONTA ATUAL --------------
	
	public String tituloConta() {
		return Contas.getInstance().getNumeroContaAtual() + " : " + Contas.getInstance().getCorrentistaContaAtual();  
	}
	public String limiteRetiradaDiaria() {
		return Contas.getInstance().getLimiteDiarioContaAtual()+"";  
	}
	public String strStatus() {
		return Contas.getInstance().getStrStatusContaAtual();
	}
	public String saldo() {
		return Contas.getInstance().getSaldoContaAtual()+"";
	}

	public double totalCreditos(int mes, int ano) {
		return Operacoes.getInstance().totalCreditos(mes, ano);
	}
	public double totalDebitos(int mes, int ano) {
		return Operacoes.getInstance().totalDebitos(mes, ano);
	}
	public int qtdCreditos(int mes, int ano) {
		return Operacoes.getInstance().qtdCreditosMesAno(mes, ano);
	}
	public int qtdDebitos(int mes, int ano) {
		return Operacoes.getInstance().qtdDebitosMesAno(mes, ano);
	}
	public double saldoMedio(int mes, int ano) {
		return Operacoes.getInstance().saldoMedio(mes, ano);
	}
	
	// -------------- FINAL DADOS CONTA ATUAL --------------
	
	public static LogicaOperacoes getInstance() {
		if(instance == null) {
			instance = new LogicaOperacoes();
		}
		return instance;
	}
}
