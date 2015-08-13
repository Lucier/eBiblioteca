package br.com.ajax.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Grupo;
import br.com.ajax.model.Usuario;
import br.com.ajax.repository.GrupoRepository;
import br.com.ajax.service.CadastroUsuarioService;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoRepository grupoRepository;

	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	private Usuario usuario;

	private List<Grupo> grupos;

	private List<Grupo> gruposSelecionados;

	public CadastroUsuarioBean() {
		limpar();
	}

	public void limpar() {
		usuario = new Usuario();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.grupos = this.grupoRepository.grupos();
		}
	}

	public void salvar() {

		this.usuario = cadastroUsuarioService.salvar(usuario);
		limpar();

		FacesUtil.addInfoMessage("Usuário cadastrado com sucesso.");
		
	}

	public GrupoRepository getGrupoRepository() {
		return grupoRepository;
	}

	public void setGrupoRepository(GrupoRepository grupoRepository) {
		this.grupoRepository = grupoRepository;
	}

	public CadastroUsuarioService getCadastroUsuarioService() {
		return cadastroUsuarioService;
	}

	public void setCadastroUsuarioService(
			CadastroUsuarioService cadastroUsuarioService) {
		this.cadastroUsuarioService = cadastroUsuarioService;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<Grupo> getGruposSelecionados() {
		return gruposSelecionados;
	}

	public void setGruposSelecionados(List<Grupo> gruposSelecionados) {
		this.gruposSelecionados = gruposSelecionados;
	}

}
