package br.com.ajax.controller;

import br.com.ajax.model.Emprestimo;

public class EmprestimoAlteradoEvent {

	private Emprestimo emprestimo;

	public EmprestimoAlteradoEvent(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

}
