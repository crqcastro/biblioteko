package br.com.cesarcastro.biblioteko.integracao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cesarcastro.biblioteko.dao.impl.AutorDaoImpl;
import br.com.cesarcastro.biblioteko.dao.impl.EditoraDAOImpl;
import br.com.cesarcastro.biblioteko.dao.impl.LivroDAOImpl;
import br.com.cesarcastro.biblioteko.model.AutorModel;
import br.com.cesarcastro.biblioteko.model.EditoraModel;
import br.com.cesarcastro.biblioteko.model.LivroModel;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class IntegracaoTeste {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private LivroDAOImpl livroDao;
	
	@Autowired
	private AutorDaoImpl autorDao;
	
	@Autowired
	private EditoraDAOImpl editoraDao;
	
	@Test
	public void deveRecueprarumLivroExistente() {

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
		

		// Create a new HttpEntity
		final HttpEntity<LivroModel> entity = new HttpEntity<>(livro);

		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange("http://localhost:" + port + "/livro/1", HttpMethod.GET, entity, String.class);

		assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void deveCriarUmLivroNovo() {

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

		

		// Create a new HttpEntity
		final HttpEntity<LivroModel> entity = new HttpEntity<>(livro);

		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange("http://localhost:" + port + "/livro", HttpMethod.POST, entity, String.class);

		assertEquals(201, responseEntity.getStatusCodeValue());
	}

}
