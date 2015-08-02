package br.com.ajax.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Cliente;
import br.com.ajax.repository.ClienteRepository;
import br.com.ajax.repository.filter.ClienteFilter;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteRepository clienteRepository;

	private ClienteFilter filter;
	private List<Cliente> clientesFiltrados;
	private Cliente clienteSelecionado;

	public PesquisaClienteBean() {
		filter = new ClienteFilter();
		clientesFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		clientesFiltrados = clienteRepository.filtrados(filter);
	}

	public void excluir() {
		clienteRepository.remover(clienteSelecionado);
		clientesFiltrados.remove(clienteSelecionado);

		FacesUtil.addInfoMessage("Cliente " + clienteSelecionado.getNome()
				+ " excluído com sucesso.");
	}

	public ClienteRepository getClienteRepository() {
		return clienteRepository;
	}

	public void setClienteRepository(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public ClienteFilter getFilter() {
		return filter;
	}

	public void setFilter(ClienteFilter filter) {
		this.filter = filter;
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

}
