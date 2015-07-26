package br.com.ajax.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Emprestimo;
import br.com.ajax.service.CancelamentoEmprestimoService;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@RequestScoped
public class CancelamentoEmprestimoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CancelamentoEmprestimoService cancelamentoEmprestimoService;

	@Inject
	private Event<EmprestimoAlteradoEvent> emprestimoAlteradoEvent;

	@Inject
	@EmprestimoEdicao
	private Emprestimo emprestimo;

	public void cancelarEmprestimo() {
		this.emprestimo = this.cancelamentoEmprestimoService
				.cancelar(this.emprestimo);
		this.emprestimoAlteradoEvent.fire(new EmprestimoAlteradoEvent(
				this.emprestimo));

		FacesUtil.addInfoMessage("Livro devolvido com sucesso");
	}
}
