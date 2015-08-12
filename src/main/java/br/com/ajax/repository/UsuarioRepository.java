package br.com.ajax.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.ajax.model.Usuario;
import br.com.ajax.repository.filter.UsuarioFilter;
import br.com.ajax.service.NegocioException;
import br.com.ajax.util.jpa.Transactional;

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

	public List<Usuario> listarPorEmail(String email) {
		return this.manager
				.createQuery(
						"from Usuario " + "where upper(email) like :email",
						Usuario.class)
				.setParameter("email", email.toLowerCase() + "%")
				.getResultList();
	}

	public Usuario salvar(Usuario usuario) {
		return this.manager.merge(usuario);
	}

	@Transactional
	public void remover(Usuario usuario) {
		try {
			usuario = porId(usuario.getId());
			manager.remove(usuario);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(
					"O usuário selecionado não pode ser excluído.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(UsuarioFilter filter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);

		if (StringUtils.isNotBlank(filter.getNome())) {
			criteria.add(Restrictions.ilike("nome", filter.getNome(),
					MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filter.getEmail())) {
			criteria.add(Restrictions.ilike("email", filter.getEmail(),
					MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

}
