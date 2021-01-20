package br.com.cesarcastro.biblioteko.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cesarcastro.biblioteko.dao.EditoraDAO;
import br.com.cesarcastro.biblioteko.model.EditoraModel;
import br.com.cesarcastro.biblioteko.service.EditoraService;

@Service
public class EditoraServiceImpl implements EditoraService{

	private EditoraDAO editoraDAO;
	
	@Autowired
	public EditoraServiceImpl(EditoraDAO editoraDAO) {
		this.editoraDAO = editoraDAO;
	}
	@Override
	public void salvar(EditoraModel editora) {
		this.editoraDAO.salvar(editora);
		
	}
	@Override
	public List<EditoraModel> obterEditorasPeloNome(EditoraModel editora) {
		return this.editoraDAO.obterEditorasPeloNome(editora);
	}

}
