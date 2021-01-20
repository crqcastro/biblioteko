package br.com.cesarcastro.biblioteko.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.cesarcastro.biblioteko.dao.AutorDAO;
import br.com.cesarcastro.biblioteko.model.AutorModel;

@Repository
public class AutorDaoImpl extends DaoGenericoImpl<AutorModel, Long> implements AutorDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@PostConstruct
	public void init() {
		super.instanciate(this.entityManager, AutorModel.class);
	}

	@Override
	public List<AutorModel> findAutoresByProps(AutorModel autor) {
		StringBuilder sb = new StringBuilder("select e from AutorModel e where 1=1 ");
		if (StringUtils.hasLength(autor.getNome())) {
			sb.append("and lower(e.nome) like lower(:nome) ");
		}
		if (StringUtils.hasLength(autor.getSobrenome())) {
			sb.append("and lower(e.sobrenome) like lower(:sobrenome) ");
		}
		if (StringUtils.hasLength(autor.getShortName())) {
			sb.append("and lower(e.shortName) like lower(:shortname) ");
		}
		TypedQuery<AutorModel> query = entityManager.createQuery(sb.toString(), AutorModel.class);

		query.setParameter("nome", "%" + autor.getNome() + "%");
		query.setParameter("sobrenome", "%" + autor.getSobrenome() + "%");
		query.setParameter("shortname", "%" + autor.getShortName() + "%");

		return query.getResultList();
	}

}
