package com.bcopstein.ExercicioRefatoracaoBanco.Negocio;

import java.util.List;

import com.bcopstein.ExercicioRefatoracaoBanco.Persistencia.Persistencia;

public class Operacoes {
	private List<Operacao> operacoes;
	private static Operacoes instance; //SINGLETON

	private Operacoes() {
		this.operacoes = Persistencia.getInstance().loadOperacoes();
	}
	
	public static Operacoes getInstance() {
		if(instance == null) {
			instance = new Operacoes();
		}
		return instance;
	}
	
}
