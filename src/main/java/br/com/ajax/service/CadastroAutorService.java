package br.com.ajax.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ajax.model.Autor;
import br.com.ajax.repository.AutorRepository;
import br.com.ajax.util.jpa.Transactional;

public class CadastroAutorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AutorRepository autorRepository;

	@Transactional
	public Autor salvar(Autor autor) {
		Autor autorExistente = autorRepository.porNome(autor.getNome());

		if (autorExistente != null && !autorExistente.equals(autor)) {
			throw new NegocioException(
					"Já existe um autor cadastrado com o nome informado.");
		}

		return autorRepository.salvar(autor);
	}

}
