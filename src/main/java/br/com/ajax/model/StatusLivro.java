package br.com.ajax.model;

public enum StatusLivro {

	EMPRESTADO("Emprestado"), RESERVA("Reserva"), DEVOLVIDO("Devolvido");

	private String descricao;

	StatusLivro(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
