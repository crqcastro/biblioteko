package br.com.cesarcastro.biblioteko.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cesarcastro.biblioteko.dao.AutorDAO;
import br.com.cesarcastro.biblioteko.model.AutorModel;
import br.com.cesarcastro.biblioteko.service.AutorService;

@Service
public class AutorServiceImpl implements AutorService{

	private AutorDAO autorDAO;
	
	@Autowired
	public AutorServiceImpl(AutorDAO autorDAO) {
		this.autorDAO = autorDAO;
	}
	
	@Override
	public void salvar(AutorModel autor) {
		this.autorDAO.salvar(autor);
		
	}

	@Override
	public List<AutorModel> findAutorByProps(AutorModel autor) {
		return this.autorDAO.findAutoresByProps(autor);
	}

	@Override
	public AutorModel findAutorById(Long id) {
		return this.autorDAO.recuperar(id);
	}

}
