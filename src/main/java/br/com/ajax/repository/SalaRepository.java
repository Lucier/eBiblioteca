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

import br.com.ajax.model.Sala;
import br.com.ajax.repository.filter.SalaFilter;
import br.com.ajax.service.NegocioException;
import br.com.ajax.util.jpa.Transactional;

public class SalaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Sala porId(Long id) {
		return this.manager.find(Sala.class, id);
	}

	public List<Sala> salas() {
		return this.manager.createQuery("from Sala", Sala.class)
				.getResultList();
	}

	public Sala salvar(Sala sala) {
		return this.manager.merge(sala);
	}

	@Transactional
	public void remover(Sala sala) {
		try {
			sala = porId(sala.getId());
			manager.remove(sala);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(
					"A sala selecionada não pode ser excluída.");
		}
	}

	public Sala porNome(String nome) {
		try {
			return manager
					.createQuery("from Sala where upper(nome) = :nome",
							Sala.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Sala> filtrados(SalaFilter filter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Sala.class);

		if (StringUtils.isNotBlank(filter.getNome())) {
			criteria.add(Restrictions.ilike("nome", filter.getNome(),
					MatchMode.ANYWHERE));
		}
		return criteria.addOrder(Order.asc("nome")).list();
	}

}
