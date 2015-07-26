package br.com.ajax.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Categoria;
import br.com.ajax.repository.CategoriaRepository;
import br.com.ajax.repository.filter.CategoriaFilter;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaRepository categoriaRepository;

	private CategoriaFilter filter;
	private List<Categoria> categoriasFiltradas;
	private Categoria categoriaSelecionada;

	public PesquisaCategoriaBean() {
		filter = new CategoriaFilter();
		categoriasFiltradas = new ArrayList<>();

	}

	public void pesquisar() {
		categoriasFiltradas = categoriaRepository.filtradas(filter);
	}

	public void excluir() {
		categoriaRepository.remover(categoriaSelecionada);
		categoriasFiltradas.remove(categoriaSelecionada);

		FacesUtil.addInfoMessage("Categoria " + categoriaSelecionada.getNome()
				+ " excluída com sucesso.");
	}

	public CategoriaRepository getCategoriaRepository() {
		return categoriaRepository;
	}

	public void setCategoriaRepository(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	public CategoriaFilter getFilter() {
		return filter;
	}

	public void setFilter(CategoriaFilter filter) {
		this.filter = filter;
	}

	public List<Categoria> getCategoriasFiltradas() {
		return categoriasFiltradas;
	}

	public void setCategoriasFiltradas(List<Categoria> categoriasFiltradas) {
		this.categoriasFiltradas = categoriasFiltradas;
	}

	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

}
