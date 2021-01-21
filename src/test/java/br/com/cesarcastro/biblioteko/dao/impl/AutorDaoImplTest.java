package br.com.cesarcastro.biblioteko.dao.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cesarcastro.biblioteko.model.AutorModel;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class AutorDaoImplTest {

	@Autowired
	private AutorDaoImpl autorDao;
	
	@Test
	public void DeveSalvarUmAltorComSucessoERetornaloNaLista() {
		AutorModel autor = new AutorModel();
		autor.setNome("Cesar");
		autor.setSobrenome("Castro");
		autor.setShortName("Castro, C");
		
		autorDao.salvar(autor);
		
		assertTrue(autorDao.findAutoresByProps(autor).size()==1);
		
	}

}
