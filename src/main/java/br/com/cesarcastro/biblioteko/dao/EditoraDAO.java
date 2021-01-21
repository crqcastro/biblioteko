package br.com.cesarcastro.biblioteko.dao;

import java.util.List;

import br.com.cesarcastro.biblioteko.model.EditoraModel;

public interface EditoraDAO extends DaoGenerico<EditoraModel, Long>{

	List<EditoraModel> obterEditorasPeloNome(EditoraModel editora);

}
