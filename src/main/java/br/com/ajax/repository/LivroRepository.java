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

import br.com.ajax.model.Livro;
import br.com.ajax.repository.filter.LivroFilter;
import br.com.ajax.service.NegocioException;
import br.com.ajax.util.jpa.Transactional;

public class LivroRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Livro salvar(Livro livro) {
		return this.manager.merge(livro);
	}

	@Transactional
	public void remover(Livro livro) {
		try {
			livro = porId(livro.getId());
			manager.remove(livro);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(
					"O livro selecionado não pode ser excluído.");
		}
	}

	public Livro porTitulo(String titulo) {
		try {
			return manager
					.createQuery("from Livro where upper(titulo) = :titulo",
							Livro.class)
					.setParameter("titulo", titulo.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Livro porISBN(String isbn) {
		try {
			return manager
					.createQuery("from Livro where upper(isbn) = :isbn",
							Livro.class)
					.setParameter("isbn", isbn.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Livro porCDD(String cdd) {
		try {
			return manager
					.createQuery("from Livro where upper(cdd) = :cdd",
							Livro.class).setParameter("cdd", cdd.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Livro> filtrados(LivroFilter filter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Livro.class);

		if (StringUtils.isNotBlank(filter.getCdd())) {
			criteria.add(Restrictions.eq("cdd", filter.getCdd()));
		}

		if (StringUtils.isNotBlank(filter.getIsbn())) {
			criteria.add(Restrictions.eq("isbn", filter.getIsbn()));
		}

		if (StringUtils.isNotBlank(filter.getTitulo())) {
			criteria.add(Restrictions.ilike("titulo", filter.getTitulo(),
					MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("titulo")).list();
	}

	public List<Livro> listaPorTitulo(String titulo) {
		return this.manager
				.createQuery("from Livro where upper(titulo) like :titulo",
						Livro.class)
				.setParameter("titulo", titulo.toUpperCase() + "%")
				.getResultList();
	}

	public Livro porId(Long id) {
		return manager.find(Livro.class, id);
	}

}
