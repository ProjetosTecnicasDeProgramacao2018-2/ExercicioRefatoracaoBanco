package com.bcopstein.ExercicioRefatoracaoBanco.Negocio;
public class Conta {
	public final int LIM_SILVER_GOLD = 50000;
	public final int LIM_GOLD_PLATINUM = 200000;
	public final int LIM_PLATINUM_GOLD = 100000;
	public final int LIM_GOLD_SILVER = 25000;
	
	private int numero;
	private String correntista;
	private double saldo;
	
	private InterfaceStateConta estado;

	public Conta(int umNumero, String umNome) {
		numero = umNumero;
		correntista = umNome;
		saldo = 0.0;
		estado = new StateSilver();
	}
	
	public Conta(int umNumero, String umNome,double umSaldo, int umStatus) {
		numero = umNumero;
		correntista = umNome;
		saldo = umSaldo;
		this.setState(umStatus);
	}
	
	public double getSaldo() {
		return saldo;
	}

	public Integer getNumero() {
		return numero;
	}
	
	public String getCorrentista() {
		return correntista;
	}
	
	public int getStatus() {
		return this.estado.getStatus();
	}
	
	
	// ------------- INICIO FUNÇÕES ADICIONADAS PARA OS STATE ------------- 
	public void setState(int status) {
		switch(status) {
			case StateSilver.STATUS:  
				this.estado = new StateSilver();
				break;
			case StateGold.STATUS:  
				this.estado = new StateGold();
				break;
			case StatePlatinum.STATUS:    
				this.estado = new StatePlatinum();
				break;
			default: 
				this.estado = null;
				break;
		}
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	// ------------- FINAL FUNÇÕES ADICIONADAS PARA OS STATE ------------- 

	public String getStrStatus() {
		return this.estado.getStrStatus();
	}
	
	public double getLimRetiradaDiaria() {
		return this.estado.getLimRetiradaDiaria();
	}
	
	public void deposito(double valor) {
		estado.deposito(this, valor);
	}

	public void retirada(double valor) {
		estado.retirada(this, valor);
	}

	@Override
	public String toString() {
		return "Conta [numero=" + numero + ", correntista=" + correntista + ", saldo=" + saldo + ", status=" + this.estado.getStatus()
				+ "]";
	}
}
