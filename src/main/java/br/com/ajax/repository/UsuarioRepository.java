package br.com.ajax.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.ajax.model.Usuario;

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Usuario porId(Long id) {
		return this.manager.find(Usuario.class, id);
	}

	public List<Usuario> bibliotecarios() {
		return this.manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}

	public Usuario porEmail(String email) {
		Usuario usuario = null;

		try {
			usuario = this.manager
					.createQuery("from Usuario where lower(email) = :email",
							Usuario.class)
					.setParameter("email", email.toLowerCase())
					.getSingleResult();
		} catch (NoResultException e) {

		}

		return usuario;
	}

}
