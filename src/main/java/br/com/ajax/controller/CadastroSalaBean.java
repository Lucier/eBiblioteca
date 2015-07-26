package br.com.ajax.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Sala;
import br.com.ajax.service.CadastroSalaService;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroSalaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroSalaService cadastroSalaService;

	private Sala sala;

	public CadastroSalaBean() {
		limpar();
	}

	public void salvar() {
		this.cadastroSalaService.salvar(this.sala);
		limpar();
		FacesUtil.addInfoMessage("Sala cadastrada com sucesso.");
	}

	public void limpar() {
		sala = new Sala();
	}

	public CadastroSalaService getCadastroSalaService() {
		return cadastroSalaService;
	}

	public void setCadastroSalaService(CadastroSalaService cadastroSalaService) {
		this.cadastroSalaService = cadastroSalaService;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public boolean isEditando() {
		return this.sala.getId() != null;
	}

}
