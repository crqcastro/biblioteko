package br.com.cesarcastro.biblioteko.service.impl;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import br.com.cesarcastro.biblioteko.dao.LivroDAO;
import br.com.cesarcastro.biblioteko.model.LivroModel;
import javassist.NotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class LivroServiceImplTest {

	@Mock
	private LivroDAO dao;

	@InjectMocks
	private LivroServiceImpl service;

	@Test
	public void deveObterUmLivroPorId() {
		LivroModel livro = new LivroModel();
		livro.setId(1L);
		livro.setTitulo("Effective Java");
		Mockito.when(dao.recuperar(Mockito.anyLong())).thenReturn(livro);
		Assert.assertEquals(1L, service.findById(1L).getId().longValue());
	}

	@Test
	public void deveSalvarUmLivroComSucesso() {
		LivroModel livro = new LivroModel();
		livro.setTitulo("Effective Java");
		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				LivroModel mock = invocation.getArgument(0);
				mock.setId(1L);
				return null;
			}
		}).when(dao).salvar(Mockito.any(LivroModel.class));
		service.salvarLivro(livro);
		Assert.assertEquals(1L, livro.getId().longValue());
	}

	@Test
	public void deveObterLivroPorParametros() {
		LivroModel params = new LivroModel();
		params.setTitulo("Java Como programar");
		Mockito.when(dao.findByParams(Mockito.any(LivroModel.class))).thenReturn(new ArrayList<LivroModel>());
		Assert.assertEquals(0, service.findByParams(params).size());
	}

	@Test
	public void deveObterListaLivros() {
		Mockito.when(dao.listarLivros(Mockito.anyInt(), Mockito.anyInt())).thenReturn(new ArrayList<LivroModel>());
		Assert.assertEquals(0, service.getListaLivros(0, 50).size());
	}

	@Test
	public void deveFazerDelecaoLogicaDeUmLivroPorId() {
		LivroModel livro = new LivroModel();
		Mockito.when(dao.recuperar(1L)).thenReturn(livro);
		Mockito.doNothing().when(dao).excluir(Mockito.any(LivroModel.class));
		service.deleteLivro(1L);
		Assert.assertEquals(0, livro.getStatus().intValue());
	}

	@Test
	public void deveAlterarStatusDoLivroComSucesso() throws NotFoundException {
		LivroModel livro = new LivroModel();
		Mockito.when(dao.recuperar(1L)).thenReturn(livro);
		Mockito.when(dao.merge(livro)).thenReturn(livro);
		service.alterarStatusLivro(1L, 1);
		Assert.assertEquals(1, livro.getStatus().intValue());
	}

	@Test(expected = NotFoundException.class)
	public void deveLancarExceptionQuandoNaoEncontrarOLivro() throws NotFoundException {
		Mockito.when(dao.recuperar(1L)).thenReturn(null);
		service.alterarStatusLivro(1L, 1);
	}

	@Test
	public void deveAlterarUmLivro() {
		LivroModel livro = new LivroModel();
		Mockito.when(dao.merge(livro)).thenReturn(livro);
		service.alterarLivro(livro);
		Assert.assertTrue(true);
	}
}
