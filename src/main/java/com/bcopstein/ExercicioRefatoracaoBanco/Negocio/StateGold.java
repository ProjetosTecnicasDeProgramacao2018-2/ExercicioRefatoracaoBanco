package com.bcopstein.ExercicioRefatoracaoBanco.Negocio;

public class StateGold implements InterfaceStateConta {
	public static final int STATUS = 1;

	@Override
	public int getStatus() {
		return STATUS;
	}

	@Override
	public String getStrStatus() {
		return "Gold";
	}

	@Override
	public void deposito(Conta conta, double valor) {
		double saldo = conta.getSaldo();
		saldo += valor * 1.01;
		
		if (saldo >= conta.LIM_GOLD_PLATINUM) {
			conta.setState(StatePlatinum.STATUS);
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
			if (saldo < conta.LIM_GOLD_SILVER) {
				conta.setState(StateSilver.STATUS);
			}
		}
		
		conta.setSaldo(saldo);
	}

	@Override
	public double getLimRetiradaDiaria() {
		return 100000.0;
	}

}
