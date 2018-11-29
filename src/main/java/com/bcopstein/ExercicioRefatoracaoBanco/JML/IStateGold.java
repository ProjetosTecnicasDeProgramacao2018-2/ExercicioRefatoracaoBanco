package com.bcopstein.ExercicioRefatoracaoBanco.JML;

import com.bcopstein.ExercicioRefatoracaoBanco.Negocio.Conta;

interface IStateGold{
	//@ \result == 1
	/*@ pure @*/public int getStatus();


	//@ \result == "Gold"
	/*@ pure @*/public String getStrStatus();

	//@ \requires valor > 0 && conta != null
	//@ \ensures \old(conta.getSaldo()) + (valor * 1.01) == conta.getSaldo() 
	public void deposito(Conta conta, double valor);

	//@ \requires valor > 0 && conta != null 
	//@ \ensures \old(conta.getSaldo()) - valor >= 0
	//@ \ensures \old(conta.getSaldo()) - valor == conta.getSaldo()    
	public void retirada(Conta conta, double valor);

	//@ \result == 100000.0
	/*@ pure @*/public double getLimRetiradaDiaria();

}
