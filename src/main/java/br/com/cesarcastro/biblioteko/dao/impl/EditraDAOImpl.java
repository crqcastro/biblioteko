package br.com.cesarcastro.biblioteko.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.cesarcastro.biblioteko.dao.EditoraDAO;
import br.com.cesarcastro.biblioteko.model.EditoraModel;

@Repository
public class EditraDAOImpl extends DaoGenericoImpl<EditoraModel, Long> implements EditoraDAO{

	@PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        super.instanciate(this.entityManager, EditoraModel.class);
    }

	@Override
	public List<EditoraModel> obterEditorasPeloNome(EditoraModel editora) {
		StringBuilder sb = new StringBuilder("select e from EditoraModel e where 1=1 ");
		if (StringUtils.hasLength(editora.getNome())) {
			sb.append("and lower(e.nome) like lower(:nome) ");
		}
		TypedQuery<EditoraModel> query = entityManager.createQuery(sb.toString(), EditoraModel.class);

		query.setParameter("nome", "%" + editora.getNome() + "%");

		return query.getResultList();
	}
}
