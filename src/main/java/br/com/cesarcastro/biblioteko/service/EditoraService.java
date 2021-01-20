package br.com.cesarcastro.biblioteko.service;

import java.util.List;

import br.com.cesarcastro.biblioteko.model.EditoraModel;

public interface EditoraService {

	void salvar(EditoraModel editora);

	List<EditoraModel> obterEditorasPeloNome(EditoraModel editora);

	EditoraModel obterEditoraById(Long id);

}
