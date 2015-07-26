package br.com.ajax.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ajax.model.Cliente;
import br.com.ajax.repository.ClienteRepository;
import br.com.ajax.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Cliente.class)
public class ClienteConverter implements Converter{
	
	private ClienteRepository clienteRepository;
	
	

	public ClienteConverter() {
		this.clienteRepository = (ClienteRepository) CDIServiceLocator.getBean(ClienteRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente retorno = null;
		
		if(value != null){
			retorno = this.clienteRepository.potId(new Long(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			return ((Cliente) value).getId().toString();
		}
		
		return "";
	}

}
