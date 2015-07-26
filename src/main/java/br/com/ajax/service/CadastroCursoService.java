package br.com.ajax.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ajax.model.Curso;
import br.com.ajax.repository.CursoRepository;
import br.com.ajax.util.jpa.Transactional;

public class CadastroCursoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CursoRepository cursoRepository;

	@Transactional
	public Curso salvar(Curso curso) {
		Curso cursoExistente = cursoRepository.porNome(curso.getNome());

		if (cursoExistente != null && !cursoExistente.equals(curso)) {
			throw new NegocioException(
					"Já existe um curso cadastrado com o nome informado.");
		}

		return cursoRepository.salvar(curso);
	}

}
