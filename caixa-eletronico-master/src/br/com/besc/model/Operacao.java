package br.com.besc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Operacao {
	
	private LocalDateTime time;
	private double saldoTotal;
	private double valor;
	private NaturezaOperacao naturezaOperacao;
	
	public abstract String getDescricao();
	
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public double getSaldoTotal() {
		return saldoTotal;
	}
	public void setSaldoTotal(double saldoTotal) {
		this.saldoTotal = saldoTotal;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public NaturezaOperacao getNaturezaOperacao() {
		return naturezaOperacao;
	}
	public void setNaturezaOperacao(NaturezaOperacao naturezaOperacao) {
		this.naturezaOperacao = naturezaOperacao;
	}

	@Override
	public String toString() {
		return time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")) + " " + getDescricao() + " Valor: " + valor
		+ " Saldo atual: " + saldoTotal + " " + naturezaOperacao.getCodigo() + "\n";
	}
	
	

	
}
