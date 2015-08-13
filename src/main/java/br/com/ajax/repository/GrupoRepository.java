package br.com.ajax.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.ajax.model.Grupo;

public class GrupoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Grupo porId(Long id) {
		return this.manager.find(Grupo.class, id);
	}

	public List<Grupo> grupos() {
		return this.manager.createQuery("from Grupo", Grupo.class)
				.getResultList();
	}

	public Grupo porNome(String nome) {
		try {
			return manager
					.createQuery("from Grupo where upper(nome) = :nome",
							Grupo.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
