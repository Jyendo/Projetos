package br.com.besc.model;

public enum NaturezaOperacao {
	CREDITO("C"), DEBITO("D");
	
	private String codigo;

	private NaturezaOperacao(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
	
}
