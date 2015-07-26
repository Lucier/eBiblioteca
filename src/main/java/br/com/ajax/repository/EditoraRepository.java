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

import br.com.ajax.model.Editora;
import br.com.ajax.repository.filter.EditoraFilter;
import br.com.ajax.service.NegocioException;
import br.com.ajax.util.jpa.Transactional;

public class EditoraRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Editora porId(Long id) {
		return this.manager.find(Editora.class, id);
	}

	public List<Editora> editoras() {
		return this.manager.createQuery("from Editora", Editora.class)
				.getResultList();
	}

	public Editora salvar(Editora editora) {
		return this.manager.merge(editora);
	}

	@Transactional
	public void remover(Editora editora) {
		try {
			editora = porId(editora.getId());
			manager.remove(editora);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(
					"A editora selecionada não pode ser excluída.");
		}
	}

	public Editora porNome(String nome) {
		try {
			return manager
					.createQuery("from Editora where upper(nome) = :nome",
							Editora.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Editora> filtrados(EditoraFilter filter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Editora.class);

		if (StringUtils.isNotBlank(filter.getNome())) {
			criteria.add(Restrictions.ilike("nome", filter.getNome(),
					MatchMode.ANYWHERE));
		}
		return criteria.addOrder(Order.asc("nome")).list();
	}

}
