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

import br.com.cesarcastro.biblioteko.dao.EditoraDAO;
import br.com.cesarcastro.biblioteko.model.EditoraModel;


@RunWith(MockitoJUnitRunner.class)
public class EditoraServiceImplTest {

	@Mock
	private EditoraDAO editoraDAO;
	
	@InjectMocks
	private EditoraServiceImpl service;
	
	@Test
	public void deveObterComSucessoUmaEditoraPorId() {
		EditoraModel mockEditora = new EditoraModel();
		mockEditora.setId(1L);
		mockEditora.setNome("Editora de teste");
		mockEditora.setMunicipio("São Luis");
		mockEditora.setPais("Brasil");
		mockEditora.setUf("MA");
		
		Mockito.when(editoraDAO.recuperar(Mockito.anyLong())).thenReturn(mockEditora);
		
		EditoraModel editora = service.obterEditoraById(1L);
		
		Assert.assertEquals(mockEditora.getId(), editora.getId());
	}
	
	@Test
	public void deveSalvarComSucessoUmaEditora() {
		EditoraModel mockEditora = new EditoraModel();
		mockEditora.setNome("Editora de teste");
		mockEditora.setMunicipio("São Luis");
		mockEditora.setPais("Brasil");
		mockEditora.setUf("MA");
		
		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				EditoraModel mock = invocation.getArgument(0);
				mock.setId(1L);
				return null;
			}
		}).when(editoraDAO).salvar(mockEditora);
		
		service.salvar(mockEditora);
		
		Assert.assertEquals(1L, (long)mockEditora.getId());
	}
	
	@Test
	public void deveObterUmaListaDeEditorasPorNome() {
		Mockito.when(editoraDAO.obterEditorasPeloNome(Mockito.any(EditoraModel.class))).thenReturn(new ArrayList<EditoraModel>());
		
		EditoraModel editora = new EditoraModel();
		editora.setNome("Editora");
		
		Assert.assertEquals(0,  service.obterEditorasPeloNome(editora).size());
	}

}
