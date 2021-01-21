package br.com.cesarcastro.biblioteko.dao.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cesarcastro.biblioteko.model.AutorModel;
import br.com.cesarcastro.biblioteko.model.EditoraModel;
import br.com.cesarcastro.biblioteko.model.LivroModel;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class LivroDAOImplTest {

	@Autowired
	private LivroDAOImpl livroDao;
	
	@Autowired
	private EditoraDAOImpl editoraDao;
	
	@Autowired
	private AutorDaoImpl autorDao;

	@Test
	public void deveSalvarOLivro() {

		EditoraModel em = new EditoraModel();
		em.setMunicipio("São Luis");
		em.setPais("Brasil");
		em.setNome("Teste");
		em.setUf("MA");

		editoraDao.salvar(em);
		
		AutorModel autor = new AutorModel();
		autor.setNome("Cesar");
		autor.setSobrenome("Castro");
		autor.setShortName("Castro, C");
		
		autorDao.salvar(autor);

		LivroModel livro = new LivroModel();
		livro.setAnoPublicacao(2021);
		List<AutorModel> listaAutores = new ArrayList<>();
		listaAutores.add(autor);
		livro.setAutores(listaAutores);
		livro.setEdicao(1);
		livro.setISBN("321-98797-54461");
		livro.setResumo("Resumo do livro");
		List<String> termosChave = new ArrayList<>();
		termosChave.add("Termo 1");
		termosChave.add("Termo 2");
		livro.setTermosChave(termosChave);
		livro.setEditora(em);
		livro.setStatus(1);
		livro.setTitulo("Livro Teste");

		livroDao.salvar(livro);

	}
	@Test
	public void deveRecuperarOLivro() {
		EditoraModel em = new EditoraModel();
		em.setMunicipio("São Luis");
		em.setPais("Brasil");
		em.setNome("Teste");
		em.setUf("MA");

		editoraDao.salvar(em);
		
		AutorModel autor = new AutorModel();
		autor.setNome("Cesar");
		autor.setSobrenome("Castro");
		autor.setShortName("Castro, C");
		
		autorDao.salvar(autor);

		LivroModel livro = new LivroModel();
		livro.setAnoPublicacao(2021);
		List<AutorModel> listaAutores = new ArrayList<>();
		listaAutores.add(autor);
		livro.setAutores(listaAutores);
		livro.setEdicao(1);
		livro.setISBN("321-98797-54461");
		livro.setResumo("Resumo do livro");
		List<String> termosChave = new ArrayList<>();
		termosChave.add("Termo 1");
		termosChave.add("Termo 2");
		livro.setTermosChave(termosChave);
		livro.setEditora(em);
		livro.setStatus(1);
		livro.setTitulo("Livro Teste");

		livroDao.salvar(livro);
		
		LivroModel lm = new LivroModel();
		List<LivroModel> listaLivros = livroDao.findByParams(lm);
		
		assertTrue(listaLivros.size()==1);

	}
	@Test
	public void deveRecuperarOLivroPeloNome() {
		EditoraModel em = new EditoraModel();
		em.setMunicipio("São Luis");
		em.setPais("Brasil");
		em.setNome("Teste");
		em.setUf("MA");

		editoraDao.salvar(em);
		
		AutorModel autor = new AutorModel();
		autor.setNome("Cesar");
		autor.setSobrenome("Castro");
		autor.setShortName("Castro, C");
		
		autorDao.salvar(autor);

		LivroModel livro = new LivroModel();
		livro.setAnoPublicacao(2021);
		List<AutorModel> listaAutores = new ArrayList<>();
		listaAutores.add(autor);
		livro.setAutores(listaAutores);
		livro.setEdicao(1);
		livro.setISBN("321-98797-54461");
		livro.setResumo("Resumo do livro");
		List<String> termosChave = new ArrayList<>();
		termosChave.add("Termo 1");
		termosChave.add("Termo 2");
		livro.setTermosChave(termosChave);
		livro.setEditora(em);
		livro.setStatus(1);
		livro.setTitulo("Livro Teste");

		livroDao.salvar(livro);
		
		LivroModel lm = new LivroModel();
		lm.setTitulo("Livro Teste");
		List<LivroModel> listaLivros = livroDao.findByParams(lm);
		
		assertTrue(listaLivros.size()==1);

	}
	
	@Test
	public void deveRecuperarPeloISBN() {
		
		EditoraModel em = new EditoraModel();
		em.setMunicipio("São Luis");
		em.setPais("Brasil");
		em.setNome("Teste");
		em.setUf("MA");

		editoraDao.salvar(em);
		
		AutorModel autor = new AutorModel();
		autor.setNome("Cesar");
		autor.setSobrenome("Castro");
		autor.setShortName("Castro, C");
		
		autorDao.salvar(autor);

		LivroModel livro = new LivroModel();
		livro.setAnoPublicacao(2021);
		List<AutorModel> listaAutores = new ArrayList<>();
		listaAutores.add(autor);
		livro.setAutores(listaAutores);
		livro.setEdicao(1);
		livro.setISBN("321-98797-54461");
		livro.setResumo("Resumo do livro");
		List<String> termosChave = new ArrayList<>();
		termosChave.add("Termo 1");
		termosChave.add("Termo 2");
		livro.setTermosChave(termosChave);
		livro.setEditora(em);
		livro.setStatus(1);
		livro.setTitulo("Livro Teste");

		livroDao.salvar(livro);
		
		LivroModel lm = new LivroModel();
		lm.setISBN("321-98797-54461");
		List<LivroModel> listaLivros = livroDao.findByParams(lm);
		
		assertTrue(listaLivros.size()==1);
	}
	
	@Test
	public void deveObterAlistDeLivros() {
		
		EditoraModel em = new EditoraModel();
		em.setMunicipio("São Luis");
		em.setPais("Brasil");
		em.setNome("Teste");
		em.setUf("MA");

		editoraDao.salvar(em);
		
		AutorModel autor = new AutorModel();
		autor.setNome("Cesar");
		autor.setSobrenome("Castro");
		autor.setShortName("Castro, C");
		
		autorDao.salvar(autor);

		LivroModel livro = new LivroModel();
		livro.setAnoPublicacao(2021);
		List<AutorModel> listaAutores = new ArrayList<>();
		listaAutores.add(autor);
		livro.setAutores(listaAutores);
		livro.setEdicao(1);
		livro.setISBN("321-98797-54461");
		livro.setResumo("Resumo do livro");
		List<String> termosChave = new ArrayList<>();
		termosChave.add("Termo 1");
		termosChave.add("Termo 2");
		livro.setTermosChave(termosChave);
		livro.setEditora(em);
		livro.setStatus(1);
		livro.setTitulo("Livro Teste");

		livroDao.salvar(livro);
		
		assertTrue(livroDao.listarLivros(0, 10).size()==1);
	}

}
