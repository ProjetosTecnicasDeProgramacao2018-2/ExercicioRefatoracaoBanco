package com.bcopstein.ExercicioRefatoracaoBanco.JML;

import com.bcopstein.ExercicioRefatoracaoBanco.Negocio.Conta;

interface IStateSilver{
	//@ \result == 0
	/*@ pure @*/public int getStatus();


	//@ \result == "Silver"
	/*@ pure @*/public String getStrStatus();

	//@ \requires valor > 0 && conta != null
	//@ \ensures \old(conta.getSaldo()) + valor == conta.getSaldo() 
	public void deposito(Conta conta, double valor);

	//@ \requires valor > 0 && conta != null 
	//@ \ensures \old(conta.getSaldo()) - valor >= 0
	//@ \ensures \old(conta.getSaldo()) - valor == conta.getSaldo()    
	public void retirada(Conta conta, double valor);

	//@ \result == 10000.0
	/*@ pure @*/public double getLimRetiradaDiaria();

}
