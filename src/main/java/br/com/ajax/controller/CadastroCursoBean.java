package br.com.ajax.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Curso;
import br.com.ajax.service.CadastroCursoService;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCursoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroCursoService cadastroCursoService;

	private Curso curso;

	public CadastroCursoBean() {
		limpar();
	}

	public void salvar() {
		this.cadastroCursoService.salvar(this.curso);
		limpar();
		FacesUtil.addInfoMessage("Curso cadastrado com sucesso.");
	}

	public void limpar() {
		curso = new Curso();
	}

	public CadastroCursoService getCadastroCursoService() {
		return cadastroCursoService;
	}

	public void setCadastroCursoService(
			CadastroCursoService cadastroCursoService) {
		this.cadastroCursoService = cadastroCursoService;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public boolean isEditando() {
		return this.curso.getId() != null;
	}

}
