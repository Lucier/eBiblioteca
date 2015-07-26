package br.com.ajax.repository.filter;

import java.io.Serializable;
import java.util.Date;

import br.com.ajax.model.StatusLivro;

public class EmprestimoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataEmprestimo;
	private Date dataDevolucao;
	private String nomeBibliotecario;
	private String nomeCliente;
	private StatusLivro[] statuses;

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getNomeBibliotecario() {
		return nomeBibliotecario;
	}

	public void setNomeBibliotecario(String nomeBibliotecario) {
		this.nomeBibliotecario = nomeBibliotecario;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public StatusLivro[] getStatuses() {
		return statuses;
	}

	public void setStatuses(StatusLivro[] statuses) {
		this.statuses = statuses;
	}

}
