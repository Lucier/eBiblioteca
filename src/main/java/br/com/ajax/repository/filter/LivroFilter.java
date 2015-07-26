package br.com.ajax.repository.filter;

import java.io.Serializable;

public class LivroFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String titulo;
	private String isbn;
	private String cdd;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCdd() {
		return cdd;
	}

	public void setCdd(String cdd) {
		this.cdd = cdd;
	}

}
