package br.com.ajax.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ajax.model.Emprestimo;
import br.com.ajax.repository.EmprestimoRepository;
import br.com.ajax.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Emprestimo.class)
public class EmprestimoConverter implements Converter {

	private EmprestimoRepository emprestimoRepository;
	
	public EmprestimoConverter() {
		emprestimoRepository = CDIServiceLocator.getBean(EmprestimoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Emprestimo retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = emprestimoRepository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Emprestimo emprestimo = (Emprestimo) value;
			return emprestimo.getId() == null ? null : emprestimo.getId()
					.toString();
		}

		return "";
	}
}
