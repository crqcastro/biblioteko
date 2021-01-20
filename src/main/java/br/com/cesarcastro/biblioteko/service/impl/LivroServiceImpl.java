package br.com.cesarcastro.biblioteko.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cesarcastro.biblioteko.model.LivroModel;
import br.com.cesarcastro.biblioteko.repository.LivroRepository;
import br.com.cesarcastro.biblioteko.service.LivroService;

public class LivroServiceImpl implements LivroService{

	private LivroRepository LivroRepository;

	@Autowired
	public LivroServiceImpl(LivroRepository livroDao) {
		this.LivroRepository = livroDao;
	}
	
	@Override
	public List<LivroModel> buscarLivros(Long offset, Long size, LivroModel params) {
		return LivroRepository.buscarLivros(offset, size, params);
	}

}
