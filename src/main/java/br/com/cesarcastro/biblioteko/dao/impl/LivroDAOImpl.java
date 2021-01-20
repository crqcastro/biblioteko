package br.com.cesarcastro.biblioteko.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.cesarcastro.biblioteko.dao.LivroDAO;
import br.com.cesarcastro.biblioteko.model.LivroModel;

@Repository
public class LivroDAOImpl extends DaoGenericoImpl<LivroModel, Long> implements LivroDAO {

	@PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        super.instanciate(this.entityManager, LivroModel.class);
    }
    
	@Override
	public List<LivroModel> buscarLivros(Long offset, Long size, LivroModel params) {
		// TODO Auto-generated method stub
		return null;
	}


}
