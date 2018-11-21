package com.bcopstein.ExercicioRefatoracaoBanco.Negocio;

public class StateSilver implements InterfaceStateConta {
	public static final int STATUS = 0;
	public final int LIM_SILVER_GOLD = 50000;
	
	public StateSilver() {
		
	}

	@Override
	public int getStatus() {
		return STATUS;
	}

	@Override
	public String getStrStatus() {
		return "Silver";
	}

	@Override
	public void deposito(final Conta conta, double valor) {
		double saldo = conta.getSaldo();
		saldo += valor;
		
		if (saldo >= LIM_SILVER_GOLD) {
			conta.setState(StateGold.STATUS);
		}
		
		conta.setSaldo(saldo);
	}

	@Override
	public void retirada(Conta conta, double valor) {
		double saldo = conta.getSaldo();

		if (saldo - valor < 0.0) {
			return;
		} else {
			saldo = saldo - valor;
		}
		
		conta.setSaldo(saldo);
	}

	@Override
	public double getLimRetiradaDiaria() {
		return 10000.0;
	}

}
