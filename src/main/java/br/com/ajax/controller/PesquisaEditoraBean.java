package br.com.ajax.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Editora;
import br.com.ajax.repository.EditoraRepository;
import br.com.ajax.repository.filter.EditoraFilter;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaEditoraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EditoraRepository editoraRepository;

	private EditoraFilter filter;
	private List<Editora> editorasFiltradas;
	private Editora editoraSelecionada;

	public PesquisaEditoraBean() {
		filter = new EditoraFilter();
		editorasFiltradas = new ArrayList<>();
	}

	public void pesquisar() {
		editorasFiltradas = editoraRepository.filtrados(filter);
	}

	public void excluir() {
		editoraRepository.remover(editoraSelecionada);
		editorasFiltradas.remove(editoraSelecionada);

		FacesUtil.addInfoMessage("Autor " + editoraSelecionada.getNome()
				+ " excluído com sucesso.");
	}

	public EditoraRepository getEditoraRepository() {
		return editoraRepository;
	}

	public void setEditoraRepository(EditoraRepository editoraRepository) {
		this.editoraRepository = editoraRepository;
	}

	public EditoraFilter getFilter() {
		return filter;
	}

	public void setFilter(EditoraFilter filter) {
		this.filter = filter;
	}

	public List<Editora> getEditorasFiltradas() {
		return editorasFiltradas;
	}

	public void setEditorasFiltradas(List<Editora> editorasFiltradas) {
		this.editorasFiltradas = editorasFiltradas;
	}

	public Editora getEditoraSelecionada() {
		return editoraSelecionada;
	}

	public void setEditoraSelecionada(Editora editoraSelecionada) {
		this.editoraSelecionada = editoraSelecionada;
	}

}
