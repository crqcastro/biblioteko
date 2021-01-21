package br.com.cesarcastro.biblioteko.dao.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cesarcastro.biblioteko.model.EditoraModel;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class EditoraDAOImplTest {

	@Autowired
	private EditoraDAOImpl editoraDao;
	
	@Test
	public void DeveSalvarUmaEditoraComSucessoERetornaloNaLista() {
		EditoraModel em = new EditoraModel();
		em.setMunicipio("São Luis");
		em.setPais("Brasil");
		em.setNome("Teste");
		em.setUf("MA");

		editoraDao.salvar(em);
		
		assertTrue(editoraDao.obterEditorasPeloNome(em).size()==1);
		
	}

}
