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

import br.com.ajax.model.Cliente;
import br.com.ajax.repository.filter.ClienteFilter;
import br.com.ajax.service.NegocioException;
import br.com.ajax.util.jpa.Transactional;

public class ClienteRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Cliente porId(Long id) {
		return this.manager.find(Cliente.class, id);
	}

	public List<Cliente> porNome(String nome) {
		return this.manager
				.createQuery("from Cliente " + "where upper(nome) like :nome",
						Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}

	public List<Cliente> listarPorEmail(String email) {
		return this.manager
				.createQuery(
						"from Cliente " + "where upper(email) like :email",
						Cliente.class)
				.setParameter("email", email.toUpperCase() + "%")
				.getResultList();
	}

	public Cliente porEmail(String email) {
		try {
			return manager
					.createQuery("from Cliente where upper(email) = :email",
							Cliente.class)
					.setParameter("email", email.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Cliente> clientes() {
		return this.manager.createQuery("from Cliente", Cliente.class)
				.getResultList();
	}

	public Cliente salvar(Cliente cliente) {
		return this.manager.merge(cliente);
	}

	@Transactional
	public void remover(Cliente cliente) {
		try {
			cliente = porId(cliente.getId());
			manager.remove(cliente);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(
					"O cliente selecionado não pode ser excluído");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> filtrados(ClienteFilter filter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);

		if (StringUtils.isNotBlank(filter.getEmail())) {
			criteria.add(Restrictions.ilike("email", filter.getEmail(),
					MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(filter.getNome())) {
			criteria.add(Restrictions.ilike("nome", filter.getNome(),
					MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}
}
