package br.com.ajax.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Editora;
import br.com.ajax.service.CadastroEditoraService;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEditoraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroEditoraService cadastroEditoraService;

	private Editora editora;

	public CadastroEditoraBean() {
		limpar();
	}

	public void salvar() {
		this.cadastroEditoraService.salvar(this.editora);
		limpar();
		FacesUtil.addInfoMessage("Editora cadastrada com sucesso.");
	}

	public void limpar() {
		editora = new Editora();
	}

	public CadastroEditoraService getCadastroEditoraService() {
		return cadastroEditoraService;
	}

	public void setCadastroEditoraService(
			CadastroEditoraService cadastroEditoraService) {
		this.cadastroEditoraService = cadastroEditoraService;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public boolean isEditando() {
		return this.editora.getId() != null;
	}

}
