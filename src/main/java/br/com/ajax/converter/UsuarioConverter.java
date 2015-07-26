package br.com.ajax.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ajax.model.Usuario;
import br.com.ajax.repository.UsuarioRepository;
import br.com.ajax.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	private UsuarioRepository usuarioRespository;

	public UsuarioConverter() {
		this.usuarioRespository = (UsuarioRepository) CDIServiceLocator
				.getBean(UsuarioRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Usuario retorno = null;

		if (value != null) {
			retorno = this.usuarioRespository.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			return ((Usuario) value).getId().toString();
		}

		return "";
	}

}
