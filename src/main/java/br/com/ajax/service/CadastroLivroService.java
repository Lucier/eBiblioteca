package br.com.ajax.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ajax.model.Livro;
import br.com.ajax.repository.LivroRepository;
import br.com.ajax.util.jpa.Transactional;

public class CadastroLivroService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LivroRepository livroRepository;

	@Transactional
	public Livro salvar(Livro livro) {
		Livro livroExistenteISBN = livroRepository.porISBN(livro.getIsbn());
		Livro livroExistenteCDD = livroRepository.porCDD(livro.getCdd());
		Livro livroExistenteTitulo = livroRepository.porTitulo(livro
				.getTitulo());

		if (livroExistenteCDD != null && !livroExistenteCDD.equals(livro)) {
			throw new NegocioException(
					"Já existe um livro registrado com o CDD informado.");
		}

		if (livroExistenteISBN != null && !livroExistenteISBN.equals(livro)) {
			throw new NegocioException(
					"Já existe um livro registrado com o ISBN informado.");
		}

		if (livroExistenteTitulo != null && !livroExistenteTitulo.equals(livro)) {
			throw new NegocioException(
					"Já existe um livro registrado com o título informado.");
		}

		return livroRepository.salvar(livro);
	}

}
