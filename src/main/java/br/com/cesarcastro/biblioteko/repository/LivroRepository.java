package br.com.cesarcastro.biblioteko.repository;

import java.util.List;

import br.com.cesarcastro.biblioteko.dao.GenericDAOImpl;
import br.com.cesarcastro.biblioteko.model.LivroModel;

public class LivroRepository extends GenericDAOImpl<LivroModel, Long>{

	protected LivroRepository(Class<LivroModel> entityClass) {
		super(entityClass);
	}

	public List<LivroModel> buscarLivros(Long offset, Long size, LivroModel params) {
//		StringBuilder sb = new StringBuilder("selece l from livros left joinwhere 1=1 ");
//		
//		params.getAutores().forEach(a->{
//			sb.append(" ")a.getNome()
//		});
//		
		return null;
	}

	
}
