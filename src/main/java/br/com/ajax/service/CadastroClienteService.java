package br.com.ajax.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ajax.model.Cliente;
import br.com.ajax.repository.ClienteRepository;
import br.com.ajax.util.jpa.Transactional;

public class CadastroClienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteRepository clienteRepository;

	@Transactional
	public Cliente salvar(Cliente cliente) {
		Cliente clienteExistente = clienteRepository.porEmail(cliente
				.getEmail());

		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException(
					"Já existe um cliente cadastrado com o email informado");
		}

		return clienteRepository.salvar(cliente);
	}

}
