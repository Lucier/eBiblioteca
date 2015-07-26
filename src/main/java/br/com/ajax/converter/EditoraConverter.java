package br.com.ajax.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ajax.model.Editora;
import br.com.ajax.repository.EditoraRepository;
import br.com.ajax.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Editora.class)
public class EditoraConverter implements Converter {

	private EditoraRepository editoraRepository;

	public EditoraConverter() {
		editoraRepository = CDIServiceLocator.getBean(EditoraRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Editora retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = editoraRepository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Editora editora = (Editora) value;
			return editora.getId() == null ? null : editora.getId().toString();
		}

		return "";
	}

}
