package br.com.ajax.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ajax.model.Emprestimo;
import br.com.ajax.model.StatusLivro;
import br.com.ajax.repository.EmprestimoRepository;
import br.com.ajax.util.jpa.Transactional;

public class EmissaoEmprestimoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroEmprestimoService cadastroEmprestimoService;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Inject
	private EmprestimoRepository emprestimoRepository;
	
	@Transactional
	public Emprestimo emitir(Emprestimo emprestimo){	
		emprestimo = this.cadastroEmprestimoService.salvar(emprestimo);
		
		if(emprestimo.isNaoEmprestavel()){
			throw new NegocioException("O empréstimo não pode ser realizado com status "
					+ emprestimo.getStatus().getDescricao() + ".");
		}
		
		this.estoqueService.baixarItensEstoque(emprestimo);
		emprestimo.setStatus(StatusLivro.EMPRESTADO);
		
		emprestimo = this.emprestimoRepository.salvar(emprestimo);
		
		return emprestimo;
	}

}
