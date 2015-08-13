package br.com.ajax.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Usuario;
import br.com.ajax.repository.UsuarioRepository;
import br.com.ajax.repository.filter.UsuarioFilter;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarioRepository;

	private UsuarioFilter filter;
	private List<Usuario> usuariosFiltrados;
	private Usuario usuarioSelecionado;

	public PesquisaUsuarioBean() {
		filter = new UsuarioFilter();
		usuariosFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		usuariosFiltrados = usuarioRepository.filtrados(filter);
	}

	public void excluir() {
		usuarioRepository.remover(usuarioSelecionado);
		usuariosFiltrados.remove(usuarioSelecionado);

		FacesUtil.addInfoMessage("Usuário " + usuarioSelecionado.getNome()
				+ " excluído com sucesso.");
	}

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public UsuarioFilter getFilter() {
		return filter;
	}

	public void setFilter(UsuarioFilter filter) {
		this.filter = filter;
	}

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
		this.usuariosFiltrados = usuariosFiltrados;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

}
