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

import br.com.ajax.model.Categoria;
import br.com.ajax.repository.filter.CategoriaFilter;
import br.com.ajax.service.NegocioException;
import br.com.ajax.util.jpa.Transactional;

public class CategoriaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Categoria porId(Long id) {
		return this.manager.find(Categoria.class, id);
	}

	public List<Categoria> categorias() {
		return this.manager.createQuery("from Categoria", Categoria.class)
				.getResultList();
	}

	public Categoria salvar(Categoria categoria) {
		return this.manager.merge(categoria);
	}

	@Transactional
	public void remover(Categoria categoria) {
		try {
			categoria = porId(categoria.getId());
			manager.remove(categoria);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(
					"A categoria selecionada não pode ser excluída.");
		}
	}

	public Categoria porNome(String nome) {
		try {
			return manager
					.createQuery("from Categoria where upper(nome) = :nome",
							Categoria.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> filtradas(CategoriaFilter filter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Categoria.class);

		if (StringUtils.isNotBlank(filter.getNome())) {
			criteria.add(Restrictions.ilike("nome", filter.getNome(),
					MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

}
