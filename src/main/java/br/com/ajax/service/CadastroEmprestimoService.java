package br.com.ajax.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import br.com.ajax.model.Emprestimo;
import br.com.ajax.model.StatusLivro;
import br.com.ajax.repository.EmprestimoRepository;
import br.com.ajax.util.jpa.Transactional;

public class CadastroEmprestimoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EmprestimoRepository emprestimoRepository;

	@Transactional
	public Emprestimo salvar(Emprestimo emprestimo) {
		if (emprestimo.isNovo()) {
			emprestimo.setDataEmprestimo(new Date());
			emprestimo.setStatus(StatusLivro.RESERVA);
		}
		
		if(emprestimo.isNaoAlteravel()){
			throw new NegocioException("O emprstimo não pode ser alterado no status " 
					+ emprestimo.getStatus().getDescricao() + ".");
		}

		if (emprestimo.getItens().isEmpty()) {
			throw new NegocioException(
					"O empréstimo deve possuir pelo menos um livro");
		}

		emprestimo = this.emprestimoRepository.salvar(emprestimo);
		return emprestimo;
	}

}
