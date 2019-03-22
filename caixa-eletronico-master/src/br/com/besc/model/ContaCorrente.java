package br.com.besc.model;

import java.time.LocalDateTime;

public class ContaCorrente extends Conta {

	private double limite = 1000;
	private double taxaOperacao = 3;
	
	@Override
	public int codigoOperacao() {
		return 1;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public double getTaxaOperacao() {
		return taxaOperacao;
	}

	public void setTaxaOperacao(double taxaOperacao) {
		this.taxaOperacao = taxaOperacao;
	}

	@Override
	public boolean sacar(double valor) {
		if (valor + this.taxaOperacao > getSaldo() + this.limite) {
			return false;
		}
		if (valor + this.taxaOperacao > getSaldo()) {
			this.limite -= (valor - getSaldo()) + this.taxaOperacao;
			setSaldo(0);
		} else {
			setSaldo(getSaldo() - valor - this.taxaOperacao);
		}
		
		OperacaoSaque saque = new OperacaoSaque();
		saque.setTime(LocalDateTime.now());
		saque.setValor(valor);
		saque.setSaldoTotal(getSaldo());
		saque.setNaturezaOperacao(NaturezaOperacao.DEBITO);
		getListaDeOperacoes().add(saque);
		
		return true;
	}
	
	@Override
	public void depositar(double valor) {
		if (this.limite < 1000) {
			double valorDevido = 1000 - this.limite;
			if (valor < valorDevido) {
				this.limite += valor;
			} else  {
				double restante = valor - valorDevido;
				this.limite = 1000;
				setSaldo(restante);
			}
		} else {
			setSaldo(getSaldo() + valor);
		}
		OperacaoDeposito deposito = new OperacaoDeposito();
		deposito.setTime(LocalDateTime.now());
		deposito.setValor(valor);
		deposito.setSaldoTotal(getSaldo());
		deposito.setNaturezaOperacao(NaturezaOperacao.CREDITO);
		getListaDeOperacoes().add(deposito);
	}
	
}
