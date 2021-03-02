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

import br.com.cesarcastro.biblioteko.dao.AutorDAO;
import br.com.cesarcastro.biblioteko.model.AutorModel;

@RunWith(MockitoJUnitRunner.class)
public class AutorServiceImplTest {

	@Mock
	private AutorDAO dao;
	
	@InjectMocks
	private AutorServiceImpl service;
	
	@Test
	public void deveSalvarUmAutorComSucesso() {
		AutorModel autor = new AutorModel();
		autor.setNome("Deitel");
		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				AutorModel mock = invocation.getArgument(0);
				mock.setId(1L);
				return null;
			}
		}).when(dao).salvar(Mockito.any(AutorModel.class));
		service.salvar(autor);
		Assert.assertEquals(1L,  autor.getId().longValue());
	}
	
	@Test
	public void deveObterUmaListaDeAutoresPelasProriedades() {
		AutorModel props = new AutorModel();
		props.setNome("Tanembaun");
		Mockito.when(dao.findAutoresByProps(Mockito.any(AutorModel.class))).thenReturn(new ArrayList<AutorModel>());
		Assert.assertEquals(0,  service.findAutorByProps(props).size());
	}
	
	@Test
	public void deveObterUmAutorPeloId() {
		AutorModel autor = new AutorModel();
		autor.setNome("Silveira");
		autor.setId(1L);
		Mockito.when(dao.recuperar(Mockito.anyLong())).thenReturn(autor);
		Assert.assertEquals(1L, service.findAutorById(1L).getId().longValue());
	}
}
