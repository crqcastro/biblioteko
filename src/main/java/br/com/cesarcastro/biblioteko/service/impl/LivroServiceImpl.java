package br.com.cesarcastro.biblioteko.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cesarcastro.biblioteko.dao.LivroDAO;
import br.com.cesarcastro.biblioteko.model.LivroModel;
import br.com.cesarcastro.biblioteko.service.LivroService;
import javassist.NotFoundException;

@Service
public class LivroServiceImpl implements LivroService {

	private LivroDAO livroDAO;

	@Autowired
	public LivroServiceImpl(LivroDAO livroDAO) {
		this.livroDAO = livroDAO;
	}

	@Override
	public LivroModel findById(Long id) {
		return this.livroDAO.recuperar(id);
	}

	@Override
	public void salvarLivro(LivroModel livro) {
		this.livroDAO.salvar(livro);
	}

	@Override
	public List<LivroModel> findByParams(LivroModel params) {
		return this.livroDAO.findByParams(params);
	}

	@Override
	public List<LivroModel> getListaLivros(Integer offset, Integer size) {
		return this.livroDAO.listarLivros(offset, size);
	}

	@Override
	public void deleteLivro(Long id) {
		LivroModel lm = this.livroDAO.recuperar(id);
		lm.setStatus(0);
		;
		this.livroDAO.excluir(lm);
	}

	@Override
	public void alterarStatusLivro(Long id, Integer status) throws NotFoundException {
		LivroModel lm = this.livroDAO.recuperar(id);
		if (lm == null)
			throw new NotFoundException("Produto ID" + id + " não encontrado");
		lm.setStatus(status);
		this.livroDAO.merge(lm);
	}

	@Override
	public void alterarLivro(LivroModel livro) {
		this.livroDAO.merge(livro);

	}

}
