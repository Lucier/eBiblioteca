package br.com.ajax.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.ajax.model.Emprestimo;
import br.com.ajax.repository.filter.EmprestimoFilter;

public class EmprestimoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Emprestimo> filtrados(EmprestimoFilter filtro) {
		Session session = this.manager.unwrap(Session.class);

		Criteria criteria = session.createCriteria(Emprestimo.class)
				.createAlias("cliente", "c").createAlias("bibliotecario", "b");

		if (filtro.getDataDevolucao() != null) {
			criteria.add(Restrictions.le("dataDevolucao",
					filtro.getDataDevolucao()));
		}

		if (filtro.getDataEmprestimo() != null) {
			criteria.add(Restrictions.le("dataEmprestio",
					filtro.getDataEmprestimo()));
		}

		if (StringUtils.isNotBlank(filtro.getNomeCliente())) {
			criteria.add(Restrictions.ilike("c.nome", filtro.getNomeCliente(),
					MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getNomeBibliotecario())) {
			criteria.add(Restrictions.ilike("b.nome",
					filtro.getNomeBibliotecario(), MatchMode.ANYWHERE));
		}

		if (filtro.getStatuses() != null && filtro.getStatuses().length > 0) {
			criteria.add(Restrictions.in("status", filtro.getStatuses()));
		}

		return criteria.addOrder(Order.asc("id")).list();
	}

	public Emprestimo salvar(Emprestimo emprestimo) {
		return this.manager.merge(emprestimo);
	}

	public Emprestimo porId(Long id) {
		return this.manager.find(Emprestimo.class, id);
	}

}
