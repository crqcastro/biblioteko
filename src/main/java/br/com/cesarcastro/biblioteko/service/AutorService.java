package br.com.cesarcastro.biblioteko.service;

import java.util.List;

import br.com.cesarcastro.biblioteko.model.AutorModel;

public interface AutorService {

	void salvar(AutorModel autor);

	List<AutorModel> findAutorByProps(AutorModel autor);

	AutorModel findAutorById(Long id);

}
