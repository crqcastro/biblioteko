package br.com.cesarcastro.biblioteko.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.cesarcastro.biblioteko.exceptions.DaoException;

public interface DaoGenerico<T, K extends Serializable> {

    void flush();

    void excluir(T obj) throws DaoException;

    T recuperar(K id) throws DaoException;

    List<T> recuperar() throws DaoException;

    void salvar(T obj) throws DaoException;

    void salvarBulk(List<T> objs) throws DaoException;

    T merge(T obj) throws DaoException;

    EntityManager getEntityManager();

    T recuperar(Object id) throws DaoException;
}
