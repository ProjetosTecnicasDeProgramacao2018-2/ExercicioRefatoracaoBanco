package com.bcopstein.ExercicioRefatoracaoBanco.Negocio;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import com.bcopstein.ExercicioRefatoracaoBanco.Persistencia.Persistencia;

public class Operacoes {
	private List<Operacao> operacoes;
	private static Operacoes instance; //SINGLETON

	private Operacoes() {
		this.operacoes = Persistencia.getInstance().loadOperacoes();
	}
	
	public void save() {
        Persistencia.getInstance().saveOperacoes(operacoes);
	}
	
	public static Operacoes getInstance() {
		if(instance == null) {
			instance = new Operacoes();
		}
		return instance;
	}
	
	public List<Operacao> extrato() {
		return this.operacoes
				.stream()
				.filter(op -> op.getNumeroConta() == Contas.getInstance().getNumeroContaAtual())
				.collect(Collectors.toList());
	}
	
	private List<Operacao> extratoDoMesAno(int mes, int ano){
		return this.operacoes
				.stream()
				.filter( (e) -> e.getNumeroConta() == Contas.getInstance().getNumeroContaAtual() && e.getAno() == ano && e.getMes() == mes )
				.collect(Collectors.toList());
	}

	public Integer qtdCreditosMesAno(int mes, int ano){
		List<Operacao> l = this.extratoDoMesAno(mes, ano)
					.stream()
					.filter(op -> op.CREDITO == op.getTipoOperacao())
					.collect(Collectors.toList());
		return l.size();
	}

	public Integer qtdDebitosMesAno(int mes, int ano){
		List<Operacao> l = this.extratoDoMesAno(mes, ano)
					.stream()
					.filter(op -> op.DEBITO == op.getTipoOperacao())
					.collect(Collectors.toList());
		return l.size();
	}
	
	public double totalCreditos(int mes, int ano) {
		return this.extratoDoMesAno(mes, ano)
					.stream()
					.filter(op -> op.CREDITO == op.getTipoOperacao())
					.mapToDouble(i->i.getValorOperacao())
					.sum();
	} 
	
	public double saldoMedio(int mes, int ano) {
		if(this.operacoes.isEmpty()) {
			return 0.0;
		}

		int count = 0;
		double saldo = 0;
		double sum = 0;
		
		Integer numConta = Contas.getInstance().getNumeroContaAtual();
		
		for(Operacao op : this.operacoes) {
			if(op.getNumeroConta() == numConta) {
				if(op.getAno() <= ano) {
					if(op.getTipoOperacao() == op.CREDITO) {
						saldo += op.getValorOperacao();
					}else if(op.getTipoOperacao() == op.DEBITO){
						saldo -= op.getValorOperacao();	
					}
					
					if(op.getMes() == mes && op.getAno() == ano){
						System.out.println(op);
						
						sum += saldo;
						count++;
					}
				}
			}
		}
		
		if(count == 0) {
			return 0.0;
		}
		
		return ( sum / count );
	}
	
	public double totalDebitos(int mes, int ano) {
		return this.extratoDoMesAno(mes, ano)
					.stream()
					.filter(op -> op.DEBITO == op.getTipoOperacao())
					.mapToDouble(i->i.getValorOperacao())
					.sum();
	} 
	
	public boolean credito(double valor) {
    	  if(!Contas.getInstance().credito(valor)) {
    		  return false;
    	  }
    	  
    	  GregorianCalendar date = new GregorianCalendar();        	  
    	  
    	  Operacao op = new Operacao(
    			  date.get(GregorianCalendar.DAY_OF_MONTH),
    			  ((int) date.get(GregorianCalendar.MONTH)+1),
    			  date.get(GregorianCalendar.YEAR),
    			  date.get(GregorianCalendar.HOUR),
    			  date.get(GregorianCalendar.MINUTE),
    			  date.get(GregorianCalendar.SECOND),
    			  Contas.getInstance().getNumeroContaAtual(),
    			  Contas.getInstance().getStatusContaAtual(),
    			  valor,
    			  0);
          operacoes.add(op);
          
          return this.operacoes.contains(op);
	}
	
	public boolean debito(double valor) {
  	  if(!Contas.getInstance().debito(valor)) {
  		  return false;
  	  }
  	  
  	  GregorianCalendar date = new GregorianCalendar();        	  
  	  
  	  Operacao op = new Operacao(
  			  date.get(GregorianCalendar.DAY_OF_MONTH),
  			  ((int) date.get(GregorianCalendar.MONTH)+1),
  			  date.get(GregorianCalendar.YEAR),
  			  date.get(GregorianCalendar.HOUR),
  			  date.get(GregorianCalendar.MINUTE),
  			  date.get(GregorianCalendar.SECOND),
  			  Contas.getInstance().getNumeroContaAtual(),
  			  Contas.getInstance().getStatusContaAtual(),
  			  valor,
  			  1);
        operacoes.add(op);
        
        return this.operacoes.contains(op);
	}
}
