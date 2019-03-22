package br.com.besc.model;

import java.util.ArrayList;

public abstract class Conta {
	private int agencia;
	private int numeroConta;
	private double saldo;
	private ArrayList<Operacao> listaDeOperacoes = new ArrayList<>();

	public abstract int codigoOperacao();

	public abstract boolean sacar(double valor);

	public abstract void depositar(double valor);

	public String gerarExtrato() {
		String extrato = "";
		for (int i = 0; i < listaDeOperacoes.size(); i++) {
			Operacao operacao = listaDeOperacoes.get(i);
			extrato += operacao.toString();
		}
		return extrato;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public ArrayList<Operacao> getListaDeOperacoes() {
		return listaDeOperacoes;
	}

	public void setListaDeOperacoes(ArrayList<Operacao> listaDeOperacoes) {
		this.listaDeOperacoes = listaDeOperacoes;
	}

}
