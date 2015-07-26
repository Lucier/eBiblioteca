package br.com.ajax.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ajax.model.Editora;
import br.com.ajax.repository.EditoraRepository;
import br.com.ajax.util.jpa.Transactional;

public class CadastroEditoraService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EditoraRepository editoraRepository;

	@Transactional
	public Editora salvar(Editora editora) {
		Editora editoraExistente = editoraRepository.porNome(editora.getNome());

		if (editoraExistente != null && !editoraExistente.equals(editora)) {
			throw new NegocioException(
					"Já existe uma editora cadastrada com o nome informado.");
		}

		return editoraRepository.salvar(editora);
	}

}
