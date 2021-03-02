package br.com.cesarcastro.biblioteko.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.cesarcastro.biblioteko.dao.DaoGenerico;
import br.com.cesarcastro.biblioteko.exceptions.DaoException;

public class DaoGenericoImpl<T, K extends Serializable> implements DaoGenerico<T, K> {

    private EntityManager entityManager;

    private Class<T> claz;

    public DaoGenericoImpl() {
    }

    public void instanciate(EntityManager entityManager, Class<T> claz) {
        this.entityManager = entityManager;
        this.claz = claz;
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

    @Override
    public void excluir(T obj) throws DaoException {
        try {
            entityManager.remove(obj);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public T recuperar(K id) throws DaoException {
        try {
            return entityManager.find(claz, id);
        } catch (NoResultException ex) {
            return null;
        } catch (Exception e) {
            String erro = String.format(" erro ao recuperar %s com id  %s", claz.getName(),
                    id == null ? "" : id.toString());
            throw new DaoException(erro, e);
        }
    }

    

    @Override
    public T recuperar(Object id) throws DaoException {
        try {
            return entityManager.find(claz, id);
        } catch (NoResultException ex) {
            return null;
        } catch (Exception e) {
            String erro = String.format(" erro ao recuperar %s com id  %s", claz.getName(),
                    id == null ? "" : id.toString());
            throw new DaoException(erro, e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> recuperar() throws DaoException {
        try {
            return entityManager.createQuery("FROM " + claz.getName()).getResultList();
        } catch (Exception e) {
            String erro = String.format(" erro ao recuperar todos os objetos do tipo  %s", claz.getName());
            throw new DaoException(erro, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void salvar(T obj) throws DaoException {
        try {
            entityManager.persist(obj);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void salvarBulk(List<T> objs) throws DaoException {
        try {
            for (T obj : objs) {
                entityManager.persist(obj);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public T merge(T obj) throws DaoException {
        try {
            return entityManager.merge(obj);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
