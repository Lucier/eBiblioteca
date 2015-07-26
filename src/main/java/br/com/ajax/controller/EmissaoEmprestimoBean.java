package br.com.ajax.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Emprestimo;
import br.com.ajax.service.EmissaoEmprestimoService;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@RequestScoped
public class EmissaoEmprestimoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EmissaoEmprestimoService emissaoEmprestimoService;

	@Inject
	@EmprestimoEdicao
	private Emprestimo emprestimo;

	@Inject
	private Event<EmprestimoAlteradoEvent> pedidoAlteradoEvent;

	public void emitirEmprestimo() {
		this.emprestimo.removerItemVazio();

		try {
			this.emprestimo = this.emissaoEmprestimoService
					.emitir(this.emprestimo);
			this.pedidoAlteradoEvent.fire(new EmprestimoAlteradoEvent(
					this.emprestimo));

			FacesUtil.addInfoMessage("Emprestimo realizado com sucesso.");
		} finally {
			this.emprestimo.adicionarItemVazio();
		}
	}

}
