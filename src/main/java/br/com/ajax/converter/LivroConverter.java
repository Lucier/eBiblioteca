package br.com.ajax.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ajax.model.Livro;
import br.com.ajax.repository.LivroRepository;
import br.com.ajax.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Livro.class)
public class LivroConverter implements Converter {

	private LivroRepository livroRepository;

	public LivroConverter() {
		livroRepository = CDIServiceLocator.getBean(LivroRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Livro retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = livroRepository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Livro produto = (Livro) value;
			return produto.getId() == null ? null : produto.getId().toString();
		}

		return "";
	}

}
