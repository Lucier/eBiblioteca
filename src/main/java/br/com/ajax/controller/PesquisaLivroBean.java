package br.com.ajax.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Livro;
import br.com.ajax.repository.LivroRepository;
import br.com.ajax.repository.filter.LivroFilter;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaLivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LivroRepository livroRepository;

	private LivroFilter filter;
	private List<Livro> livrosFiltrados;
	private Livro livroSelecionado;

	public PesquisaLivroBean() {
		filter = new LivroFilter();
		livrosFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		livrosFiltrados = livroRepository.filtrados(filter);
	}

	public void excluir() {
		livroRepository.remover(livroSelecionado);
		livrosFiltrados.remove(livroSelecionado);

		FacesUtil.addInfoMessage("Livro " + livroSelecionado.getTitulo()
				+ " excluído com sucesso.");
	}

	public LivroFilter getFilter() {
		return filter;
	}

	public List<Livro> getLivrosFiltrados() {
		return livrosFiltrados;
	}

	public Livro getLivroSelecionado() {
		return livroSelecionado;
	}

	public void setLivroSelecionado(Livro livroSelecionado) {
		this.livroSelecionado = livroSelecionado;
	}

}
