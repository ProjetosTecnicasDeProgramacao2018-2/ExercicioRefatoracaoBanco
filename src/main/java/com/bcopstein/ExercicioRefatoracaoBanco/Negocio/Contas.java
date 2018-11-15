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
		
		this.contaAtual = this.contas.get(numConta);
		
		//SE NÃO ENCONTRAR A CONTA
		if(!this.existeContaAtual()) {
			return false;
		}
		return true; //SE DER TUDO CERTO, RETORNA true;
	}
	
	public void logout() {
		this.contaAtual = null;
	}
	
	public boolean existeContaAtual() {
		return this.contaAtual != null;
	}

	public Conta getContaAtual() {
		if(!this.existeContaAtual()) {
			throw new RuntimeException("Não existe conta atual");
		}
		return contaAtual;
	}

	public boolean credito(double valor) {
		if (valor <= 0.0) {
			return false;
  	  	}
		this.getContaAtual().deposito(valor);
		return true;
	}
	public boolean debito(double valor) {
		if (valor <= 0.0 || valor > this.contaAtual.getSaldo()) {
			return false;
  	  	}
		this.getContaAtual().retirada(valor);
		return true;
	}
	
	public void save() {
		Persistencia.getInstance().saveContas(contas.values());
	}
	
	
	// PRECISO FAZER ISSO OU POSSO PEGAR DIRETO DO getContaAtual() ???????????????????????
	public Integer getNumeroContaAtual() {
		return this.contaAtual.getNumero();
	}
	public Integer getStatusContaAtual() {
		return this.contaAtual.getStatus();
	}
	public String getCorrentistaContaAtual() {
		return this.contaAtual.getCorrentista();
	}
	public String getStrStatusContaAtual() {
		return this.contaAtual.getStrStatus();
	}
	public double getLimiteDiarioContaAtual() {
		return this.contaAtual.getLimRetiradaDiaria();
	}
	public double getSaldoContaAtual() {
		return this.contaAtual.getSaldo();
	}
}
