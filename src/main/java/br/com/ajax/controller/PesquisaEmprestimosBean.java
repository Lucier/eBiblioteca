package br.com.ajax.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Emprestimo;
import br.com.ajax.model.StatusLivro;
import br.com.ajax.repository.EmprestimoRepository;
import br.com.ajax.repository.filter.EmprestimoFilter;

@Named
@ViewScoped
public class PesquisaEmprestimosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EmprestimoRepository emprestimoRepository;

	private List<Emprestimo> emprestimosFiltrados;
	private EmprestimoFilter filtro;

	public PesquisaEmprestimosBean() {
		filtro = new EmprestimoFilter();
		emprestimosFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		emprestimosFiltrados = emprestimoRepository.filtrados(filtro);
	}

	public StatusLivro[] getStatuses() {
		return StatusLivro.values();
	}

	public List<Emprestimo> getEmprestimosFiltrados() {
		return emprestimosFiltrados;
	}

	public EmprestimoFilter getFiltro() {
		return filtro;
	}

}
