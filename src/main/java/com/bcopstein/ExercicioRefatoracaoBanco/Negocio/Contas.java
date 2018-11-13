package com.bcopstein.ExercicioRefatoracaoBanco.Negocio;

import java.util.Map;

import com.bcopstein.ExercicioRefatoracaoBanco.Persistencia.Persistencia;

public class Contas {
	private Map<Integer,Conta> contas;
	private static Contas instance; //SINGLETON
	private Conta contaAtual;

	private Contas() {
		this.contas = Persistencia.getInstance().loadContas();
	}
	
	public static Contas getInstance() {
		if(instance == null) {
			instance = new Contas();
		}
		return instance;
	}
	
	public boolean autenticaLogin(Integer numConta) {
		//SE JA EXISTIR UMA CONTA LOGADA, BLOQUEIA O LOGIN
		if(this.existeContaAtual()) {
			return false;
		}
		
		this.contaAtual = Contas.getInstance().get(numConta);
		
		//SE NÃO ENCONTRAR A CONTA
		if(!this.existeContaAtual()) {
			return false;
		}
		return true; //SE DER TUDO CERTO, RETORNA true;
	}
	
	public boolean existeContaAtual() {
		return this.contaAtual != null;
	}

	public Conta get(Integer nroConta) {
		return this.contas.get(nroConta);
	}
	
}
