package br.com.ajax.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ajax.model.Emprestimo;
import br.com.ajax.model.ItemEmprestimo;
import br.com.ajax.repository.EmprestimoRepository;
import br.com.ajax.util.jpa.Transactional;

public class EstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EmprestimoRepository emprestimoRepository;

	@Transactional
	public void baixarItensEstoque(Emprestimo emprestimo) {
		emprestimo = this.emprestimoRepository.porId(emprestimo.getId());

		for (ItemEmprestimo item : emprestimo.getItens()) {
			item.getLivro().baixarEstoque(item.getQuantidade());
		}
	}

	public void retornarItensEstoque(Emprestimo emprestimo) {
		emprestimo = this.emprestimoRepository.porId(emprestimo.getId());

		for (ItemEmprestimo item : emprestimo.getItens()) {
			item.getLivro().adicionarEstoque(item.getQuantidade());
		}
	}

}
