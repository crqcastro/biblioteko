package br.com.cesarcastro.biblioteko.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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
	public List<LivroModel> findByParams(LivroModel params) {
		StringBuilder sb = new StringBuilder("select e from LivroModel e where 1=1 ");
		if (StringUtils.hasLength(params.getTitulo())) {
			sb.append("and lower(e.titulo) like lower(:titulo) ");
		}
		if (StringUtils.hasLength(params.getISBN())) {
			sb.append("and lower(e.ISBN) = lower(:isbn) ");
		}
		TypedQuery<LivroModel> query = entityManager.createQuery(sb.toString(), LivroModel.class);

		if (StringUtils.hasLength(params.getTitulo())) {
			query.setParameter("titulo", "%" + params.getTitulo() + "%");
		}
		if (StringUtils.hasLength(params.getISBN())) {
			query.setParameter("isbn", params.getISBN());
		}

		return query.getResultList();
	}

	@Override
	public List<LivroModel> listarLivros(Integer offset, Integer size) {
		TypedQuery<LivroModel> query = entityManager.createQuery("select e from LivroModel e", LivroModel.class);
		query.setFirstResult(offset);
		query.setMaxResults(size);
	
		return query.getResultList();
	}

}
