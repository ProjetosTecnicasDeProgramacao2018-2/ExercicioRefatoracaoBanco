package com.bcopstein.ExercicioRefatoracaoBanco.Negocio;

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

	public boolean login(Integer numConta) {
		return Contas.getInstance().autenticaLogin(numConta); // Autoriza login deixa a conta como conta em uso
	}
	
	public void deposito() {
		
	}
	public void credito() {
		
	}
	public void extrato() {
		
	}
	
	
	public static LogicaOperacoes getInstance() {
		if(instance == null) {
			instance = new LogicaOperacoes();
		}
		return instance;
	}
}
