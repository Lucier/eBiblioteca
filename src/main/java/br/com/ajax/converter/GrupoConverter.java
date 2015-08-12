package br.com.ajax.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ajax.model.Grupo;
import br.com.ajax.repository.GrupoRepository;
import br.com.ajax.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Grupo.class, value="grupoConverter")
public class GrupoConverter implements Converter {

	private GrupoRepository grupoRepository;

	public GrupoConverter() {
		grupoRepository = CDIServiceLocator.getBean(GrupoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Grupo retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = grupoRepository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Grupo grupo = (Grupo) value;
			return grupo.getNome() == null ? null : grupo.getNome().toString();
		}

		return "";
	}

}
