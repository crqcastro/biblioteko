package br.com.cesarcastro.biblioteko.service;

import java.util.List;

import br.com.cesarcastro.biblioteko.model.LivroModel;

public interface LivroService {

	List<LivroModel> buscarLivros(Long offset, Long size, LivroModel params);

	LivroModel findById(Long id);
	
	void salvarLivro(LivroModel livro);

}
