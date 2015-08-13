package br.com.ajax.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.ajax.model.Grupo;
import br.com.ajax.model.Usuario;
import br.com.ajax.repository.GrupoRepository;
import br.com.ajax.repository.UsuarioRepository;
import br.com.ajax.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarioRepository;
	
	@Inject
	private GrupoRepository grupoRepository;
	
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		Usuario usuarioExistente = usuarioRepository.porEmail(usuario
				.getEmail());

		if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
			throw new NegocioException(
					"Já existe um usuário cadastrado com o email informado.");
		}
		
		List<Grupo> grupos = new ArrayList<>();
		grupos.add(grupoRepository.porNome("Usuario"));
		usuario.setGrupos(grupos);
		return this.usuarioRepository.salvar(usuario);
	}

}
