package br.com.cesarcastro.biblioteko.dao;

import java.util.List;

import br.com.cesarcastro.biblioteko.model.LivroModel;

public interface LivroDAO extends DaoGenerico<LivroModel, Long> {

	List<LivroModel> findByParams(LivroModel params);

}
