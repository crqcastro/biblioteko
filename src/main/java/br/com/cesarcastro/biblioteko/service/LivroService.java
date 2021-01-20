package br.com.cesarcastro.biblioteko.service;

import java.util.List;

import br.com.cesarcastro.biblioteko.model.LivroModel;

public interface LivroService {

	LivroModel findById(Long id);
	
	void salvarLivro(LivroModel livro);

	List<LivroModel> findByParams(LivroModel params);

	List<LivroModel> getListaLivros(Integer offset, Integer size);

}
