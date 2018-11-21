package com.bcopstein.ExercicioRefatoracaoBanco.Negocio;

import java.util.GregorianCalendar;

public class Operacao {
	public final static int CREDITO = 0;
	public final static int DEBITO = 1;

	private int dia;
	private int mes;
	private int ano;
	private int hora;
	private int minuto;
	private int segundo;
	private int numeroConta;
	private int statusConta;
	private double valorOperacao;
	private int tipoOperacao;

	// TIVE QUE DEIXAR PUBLIC POR CAUSA QUE A CLASSE Persistencia USA O CONSTRUTOR
	public Operacao(int dia, int mes, int ano, int hora, int minuto, int segundo, int numeroConta, int statusConta,
			double valorOperacao, int tipoOperacao) {
		super();
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.hora = hora;
		this.minuto = minuto;
		this.segundo = segundo;
		this.numeroConta = numeroConta;
		this.statusConta = statusConta;
		this.valorOperacao = valorOperacao;
		this.tipoOperacao = tipoOperacao;
	}

	public static Operacao criaOperacaoDebito(double valor) {
		GregorianCalendar date = new GregorianCalendar();  
		
		return new Operacao(
	  			  date.get(GregorianCalendar.DAY_OF_MONTH),
	  			  ((int) date.get(GregorianCalendar.MONTH)+1),
	  			  date.get(GregorianCalendar.YEAR),
	  			  date.get(GregorianCalendar.HOUR),
	  			  date.get(GregorianCalendar.MINUTE),
	  			  date.get(GregorianCalendar.SECOND),
	  			  Contas.getInstance().getNumeroContaAtual(),
	  			  Contas.getInstance().getStatusContaAtual(),
	  			  valor,
	  			  Operacao.CREDITO);
	}
	
	public static Operacao criaOperacaoCredito(double valor) {
		GregorianCalendar date = new GregorianCalendar();  
		
		return new Operacao(
	  			  date.get(GregorianCalendar.DAY_OF_MONTH),
	  			  ((int) date.get(GregorianCalendar.MONTH)+1),
	  			  date.get(GregorianCalendar.YEAR),
	  			  date.get(GregorianCalendar.HOUR),
	  			  date.get(GregorianCalendar.MINUTE),
	  			  date.get(GregorianCalendar.SECOND),
	  			  Contas.getInstance().getNumeroContaAtual(),
	  			  Contas.getInstance().getStatusContaAtual(),
	  			  valor,
	  			  Operacao.DEBITO);
	}

	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}

	public int getHora() {
		return hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public int getSegundo() {
		return segundo;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public int getStatusConta() {
		return statusConta;
	}

	public double getValorOperacao() {
		return valorOperacao;
	}

	public int getTipoOperacao() {
		return tipoOperacao;
	}

	@Override
	public String toString() {
		String tipo = "<C>";
		if (tipoOperacao == DEBITO) {
			tipo = "<D>";
		}
		String line = dia + "/" + mes + "/" + ano + " " + hora + ":" + minuto + ":" + segundo + " " + numeroConta + " "
				+ statusConta + " " + tipo + " " + valorOperacao;
		return (line);
	}
}
