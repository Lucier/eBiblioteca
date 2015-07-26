package br.com.ajax.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ajax.model.Categoria;
import br.com.ajax.repository.CategoriaRepository;
import br.com.ajax.util.jpa.Transactional;

public class CadastroCategoriaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaRepository categoriaRepository;

	@Transactional
	public Categoria salvar(Categoria categoria) {
		Categoria categoriaExistente = categoriaRepository.porNome(categoria
				.getNome());

		if (categoriaExistente != null && !categoriaExistente.equals(categoria)) {
			throw new NegocioException(
					"Já existe uma categoria cadastrada com o nome informado.");
		}

		return categoriaRepository.salvar(categoria);
	}

}
