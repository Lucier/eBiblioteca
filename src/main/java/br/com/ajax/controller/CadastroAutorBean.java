package br.com.ajax.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Autor;
import br.com.ajax.service.CadastroAutorService;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroAutorService cadastroAutorService;

	private Autor autor;

	public CadastroAutorBean() {
		limpar();
	}

	public void salvar() {
		this.cadastroAutorService.salvar(this.autor);
		limpar();
		FacesUtil.addInfoMessage("Autor cadastrado com sucesso.");
	}

	public void limpar() {
		autor = new Autor();
	}

	public CadastroAutorService getCadastroAutorService() {
		return cadastroAutorService;
	}

	public void setCadastroAutorService(
			CadastroAutorService cadastroAutorService) {
		this.cadastroAutorService = cadastroAutorService;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public boolean isEditando() {
		return this.autor.getId() != null;
	}

}
