package br.com.ajax.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ajax.model.Categoria;
import br.com.ajax.repository.CategoriaRepository;
import br.com.ajax.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

	private CategoriaRepository categoriaRepository;

	public CategoriaConverter() {
		categoriaRepository = CDIServiceLocator
				.getBean(CategoriaRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Categoria retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = categoriaRepository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Categoria categoria = (Categoria) value;
			return categoria.getId() == null ? null : categoria.getId()
					.toString();
		}

		return "";
	}
}
