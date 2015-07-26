package br.com.ajax.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ajax.model.Curso;
import br.com.ajax.repository.CursoRepository;
import br.com.ajax.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Curso.class)
public class CursoConverter implements Converter {

	private CursoRepository cursoRepository;

	public CursoConverter() {
		cursoRepository = CDIServiceLocator.getBean(CursoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Curso retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = cursoRepository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Curso curso = (Curso) value;
			return curso.getId() == null ? null : curso.getId().toString();
		}

		return "";
	}

}
