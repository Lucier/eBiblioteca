package br.com.ajax.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Autor;
import br.com.ajax.repository.AutorRepository;
import br.com.ajax.repository.filter.AutorFilter;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AutorRepository autorRepository;

	private AutorFilter filter;
	private List<Autor> autoresFiltrados;
	private Autor autorSelecionado;

	public PesquisaAutorBean() {
		filter = new AutorFilter();
		autoresFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		autoresFiltrados = autorRepository.filtrados(filter);
	}

	public void excluir() {
		autorRepository.remover(autorSelecionado);
		autoresFiltrados.remove(autorSelecionado);

		FacesUtil.addInfoMessage("Autor " + autorSelecionado.getNome()
				+ " excluído com sucesso.");
	}

	public AutorRepository getAutorRepository() {
		return autorRepository;
	}

	public void setAutorRepository(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}

	public AutorFilter getFilter() {
		return filter;
	}

	public void setFilter(AutorFilter filter) {
		this.filter = filter;
	}

	public List<Autor> getAutoresFiltrados() {
		return autoresFiltrados;
	}

	public void setAutoresFiltrados(List<Autor> autoresFiltrados) {
		this.autoresFiltrados = autoresFiltrados;
	}

	public Autor getAutorSelecionado() {
		return autorSelecionado;
	}

	public void setAutorSelecionado(Autor autorSelecionado) {
		this.autorSelecionado = autorSelecionado;
	}

}
