package br.com.ajax.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ajax.model.Autor;
import br.com.ajax.repository.AutorRepository;
import br.com.ajax.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Autor.class)
public class AutorConverter implements Converter {

	private AutorRepository autorRepository;

	public AutorConverter() {
		autorRepository = CDIServiceLocator.getBean(AutorRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Autor retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = autorRepository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Autor autor = (Autor) value;
			return autor.getId() == null ? null : autor.getId().toString();
		}

		return "";
	}

}
