package br.com.cesarcastro.biblioteko.dao;

import java.util.List;

import br.com.cesarcastro.biblioteko.model.AutorModel;

public interface AutorDAO extends DaoGenerico<AutorModel, Long>{

	List<AutorModel> findAutoresByProps(AutorModel autor);

}
