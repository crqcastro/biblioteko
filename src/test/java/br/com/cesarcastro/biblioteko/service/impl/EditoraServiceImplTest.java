package br.com.cesarcastro.biblioteko.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.cesarcastro.biblioteko.dao.EditoraDAO;
import br.com.cesarcastro.biblioteko.model.EditoraModel;


@RunWith(MockitoJUnitRunner.class)
public class EditoraServiceImplTest {

	@Mock
	private EditoraDAO editoraDAO;
	
	@InjectMocks
	private EditoraServiceImpl service;
	
	@Test
	public void test() {
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

}
