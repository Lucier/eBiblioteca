package br.com.ajax.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Autor;
import br.com.ajax.model.Categoria;
import br.com.ajax.model.Editora;
import br.com.ajax.model.Livro;
import br.com.ajax.repository.AutorRepository;
import br.com.ajax.repository.CategoriaRepository;
import br.com.ajax.repository.EditoraRepository;
import br.com.ajax.service.CadastroLivroService;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroLivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AutorRepository autorRepository;

	@Inject
	private CategoriaRepository categoriaRepository;

	@Inject
	private EditoraRepository editoraRepository;

	@Inject
	private CadastroLivroService cadastroLivroService;

	private Livro livro;

	private List<Autor> autores;
	private List<Categoria> categorias;
	private List<Editora> editoras;

	private String cdd;
	private String isbn;
	private String titulo;

	public CadastroLivroBean() {
		limpar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.autores = this.autorRepository.autores();
			this.categorias = this.categoriaRepository.categorias();
			this.editoras = this.editoraRepository.editoras();
		}
	}

	public void salvar() {
		this.livro = cadastroLivroService.salvar(this.livro);
		limpar();

		FacesUtil.addInfoMessage("Livro salvo com sucesso.");
	}

	public void limpar() {
		livro = new Livro();
	}

	public String getCdd() {
		return cdd;
	}

	public void setCdd(String cdd) {
		this.cdd = cdd;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public List<Editora> getEditoras() {
		return editoras;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public boolean isEditando(){
		return this.livro.getId() != null;
	}

}
