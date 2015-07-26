package br.com.ajax.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ajax.model.Emprestimo;
import br.com.ajax.model.StatusLivro;
import br.com.ajax.repository.EmprestimoRepository;
import br.com.ajax.util.jpa.Transactional;

public class CancelamentoEmprestimoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EmprestimoRepository emprestimoRepository;

	@Inject
	private EstoqueService estoqueService;

	@Transactional
	public Emprestimo cancelar(Emprestimo emprestimo) {
		emprestimo = this.emprestimoRepository.porId(emprestimo.getId());

		if (emprestimo.isNaoDevolvivel()) {
			throw new NegocioException(
					"O livro não pode ser devolvido no status "
							+ emprestimo.getStatus().getDescricao() + ".");
		}

		if (emprestimo.isEmprestado()) {
			this.estoqueService.retornarItensEstoque(emprestimo);
		}
		emprestimo.setStatus(StatusLivro.DEVOLVIDO);

		emprestimo = this.emprestimoRepository.salvar(emprestimo);

		return emprestimo;
	}
}
