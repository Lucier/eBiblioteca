package br.com.ajax.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Categoria;
import br.com.ajax.service.CadastroCategoriaService;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroCategoriaService cadastroCategoriaService;

	private Categoria categoria;

	public CadastroCategoriaBean() {
		limpar();
	}

	public void salvar() {
		this.cadastroCategoriaService.salvar(this.categoria);
		limpar();

		FacesUtil.addInfoMessage("Categoria cadastrada com sucesso.");
	}

	public void limpar() {
		categoria = new Categoria();
	}

	public CadastroCategoriaService getCadastroCategoriaService() {
		return cadastroCategoriaService;
	}

	public void setCadastroCategoriaService(
			CadastroCategoriaService cadastroCategoriaService) {
		this.cadastroCategoriaService = cadastroCategoriaService;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public boolean isEditando() {
		return this.categoria.getId() != null;
	}

}
