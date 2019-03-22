package br.com.besc.model;

import java.time.LocalDateTime;

public class ContaPoupanca extends Conta {

	@Override
	public int codigoOperacao() {
		return 13;
	}

	@Override
	public boolean sacar(double valor) {
		if (valor > getSaldo()) {
			return false;
		}
		setSaldo(getSaldo() - valor);
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
		setSaldo(getSaldo() + valor);
		OperacaoDeposito deposito = new OperacaoDeposito();
		deposito.setTime(LocalDateTime.now());
		deposito.setValor(valor);
		deposito.setSaldoTotal(getSaldo());
		deposito.setNaturezaOperacao(NaturezaOperacao.CREDITO);
		getListaDeOperacoes().add(deposito);
	}

}
