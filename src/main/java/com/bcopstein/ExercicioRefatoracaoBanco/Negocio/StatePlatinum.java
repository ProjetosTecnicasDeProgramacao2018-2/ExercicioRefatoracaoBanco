package com.bcopstein.ExercicioRefatoracaoBanco.Negocio;

public class StatePlatinum implements InterfaceStateConta {
	public static final int STATUS = 2;
	
	@Override
	public int getStatus() {
		return STATUS;
	}

	@Override
	public String getStrStatus() {
		return "Platinum";
	}

	@Override
	public void deposito(final Conta conta, double valor) {
		double saldo = conta.getSaldo();
		
		saldo += valor * 1.025;
		
		conta.setSaldo(saldo);
	}

	@Override
	public void retirada(Conta conta, double valor) {
		double saldo = conta.getSaldo();

		if (saldo - valor < 0.0) {
			return;
		} else {
			saldo = saldo - valor;
			if (saldo < conta.LIM_PLATINUM_GOLD) {
				conta.setState(StateGold.STATUS);
			}
		}
		
		conta.setSaldo(saldo);
	}

	@Override
	public double getLimRetiradaDiaria() {
		return 500000.0;
	}

}
