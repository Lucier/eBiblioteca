package br.com.ajax.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ajax.model.Sala;
import br.com.ajax.repository.SalaRepository;
import br.com.ajax.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Sala.class)
public class SalaConverter implements Converter {

	private SalaRepository salaRepository;

	public SalaConverter() {
		salaRepository = CDIServiceLocator.getBean(SalaRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Sala retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = salaRepository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Sala sala = (Sala) value;
			return sala.getId() == null ? null : sala.getId().toString();
		}

		return "";
	}

}
