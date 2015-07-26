package br.com.ajax.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Curso;
import br.com.ajax.repository.CursoRepository;
import br.com.ajax.repository.filter.CursoFilter;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCursoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CursoRepository cursoRepository;

	private CursoFilter filter;
	private List<Curso> cursosFiltrados;
	private Curso cursoSelecionado;

	public PesquisaCursoBean() {
		filter = new CursoFilter();
		cursosFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		cursosFiltrados = cursoRepository.filtrados(filter);
	}

	public void excluir() {
		cursoRepository.remover(cursoSelecionado);
		cursosFiltrados.remove(cursoSelecionado);

		FacesUtil.addInfoMessage("Curso " + cursoSelecionado.getNome()
				+ " excluído com sucesso.");
	}

	public CursoRepository getCursoRepository() {
		return cursoRepository;
	}

	public void setCursoRepository(CursoRepository cursoRepository) {
		this.cursoRepository = cursoRepository;
	}

	public CursoFilter getFilter() {
		return filter;
	}

	public void setFilter(CursoFilter filter) {
		this.filter = filter;
	}

	public List<Curso> getCursosFiltrados() {
		return cursosFiltrados;
	}

	public void setCursosFiltrados(List<Curso> cursosFiltrados) {
		this.cursosFiltrados = cursosFiltrados;
	}

	public Curso getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Curso cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

}
