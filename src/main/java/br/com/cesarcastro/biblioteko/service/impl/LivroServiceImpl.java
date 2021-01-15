package br.com.cesarcastro.biblioteko.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cesarcastro.biblioteko.dao.LivroDao;
import br.com.cesarcastro.biblioteko.model.LivroModel;
import br.com.cesarcastro.biblioteko.service.LivroService;

public class LivroServiceImpl implements LivroService{

	private LivroDao livroDao;

	@Autowired
	public LivroServiceImpl(LivroDao livroDao) {
		this.livroDao = livroDao;
	}
	
	@Override
	public List<LivroModel> buscarLivros(Long offset, Long size, LivroModel params) {
		return livroDao.buscarLivros(offset, size, params);
	}

}
