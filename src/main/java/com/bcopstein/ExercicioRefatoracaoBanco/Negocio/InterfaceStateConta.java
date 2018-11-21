package com.bcopstein.ExercicioRefatoracaoBanco.Negocio;

public interface InterfaceStateConta {
	public int getStatus();
	public double getLimRetiradaDiaria();
	public String getStrStatus();
	public void deposito(Conta conta, double valor);
	public void retirada(Conta conta, double valor);
}
