package br.com.cesarcastro.biblioteko.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.cesarcastro.biblioteko.dao.LivroDAO;
import br.com.cesarcastro.biblioteko.model.LivroModel;
import br.com.cesarcastro.biblioteko.service.LivroService;

@Service
@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
public class LivroServiceImpl implements LivroService  {

	private LivroDAO livroDAO;

	@Autowired
	public LivroServiceImpl(LivroDAO livroDAO) {
		this.livroDAO = livroDAO;
	}
	
	@Override
	public List<LivroModel> buscarLivros(Long offset, Long size, LivroModel params) {
		return livroDAO.buscarLivros(offset, size, params);
	}

	@Override
	public LivroModel findById(Long id) {
		return livroDAO.recuperar(id);
	}

	@Override
	public void salvarLivro(LivroModel livro) {
		livroDAO.salvar(livro);
		
	}


}
