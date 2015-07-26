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

import br.com.ajax.model.Autor;
import br.com.ajax.repository.filter.AutorFilter;
import br.com.ajax.service.NegocioException;
import br.com.ajax.util.jpa.Transactional;

public class AutorRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Autor porId(Long id) {
		return this.manager.find(Autor.class, id);
	}

	public List<Autor> autores() {
		return this.manager.createQuery("from Autor", Autor.class)
				.getResultList();
	}

	public Autor salvar(Autor autor) {
		return this.manager.merge(autor);
	}

	@Transactional
	public void remover(Autor autor) {
		try {
			autor = porId(autor.getId());
			manager.remove(autor);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(
					"O autor selecionado não pode ser excluído.");
		}
	}

	public Autor porNome(String nome) {
		try {
			return manager
					.createQuery("from Autor where upper(nome) = :nome",
							Autor.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Autor> filtrados(AutorFilter filter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Autor.class);

		if (StringUtils.isNotBlank(filter.getNome())) {
			criteria.add(Restrictions.ilike("nome", filter.getNome(),
					MatchMode.ANYWHERE));
		}
		return criteria.addOrder(Order.asc("nome")).list();
	}

}
