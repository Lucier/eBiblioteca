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

import br.com.ajax.model.Curso;
import br.com.ajax.repository.filter.CursoFilter;
import br.com.ajax.service.NegocioException;
import br.com.ajax.util.jpa.Transactional;

public class CursoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Curso porId(Long id) {
		return this.manager.find(Curso.class, id);
	}

	public List<Curso> cursos() {
		return this.manager.createQuery("from Curso", Curso.class)
				.getResultList();
	}

	public Curso salvar(Curso curso) {
		return this.manager.merge(curso);
	}

	@Transactional
	public void remover(Curso curso) {
		try {
			curso = porId(curso.getId());
			manager.remove(curso);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(
					"O curso selecionado não pode ser excluído.");
		}
	}

	public Curso porNome(String nome) {
		try {
			return manager
					.createQuery("from Curso where upper(nome) = :nome",
							Curso.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Curso> filtrados(CursoFilter filter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Curso.class);

		if (StringUtils.isNotBlank(filter.getNome())) {
			criteria.add(Restrictions.ilike("nome", filter.getNome(),
					MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

}
