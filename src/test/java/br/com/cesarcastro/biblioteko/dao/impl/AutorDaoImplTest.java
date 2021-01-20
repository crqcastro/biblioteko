package br.com.cesarcastro.biblioteko.dao.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cesarcastro.biblioteko.model.AutorModel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutorDaoImplTest {

	@Autowired
	private AutorDaoImpl autorDao;
	
	@Test
	public void DeveSalvarUmAltorComSucessoERetornáloNaLista() {
		AutorModel autor = new AutorModel();
		autor.setNome("Cesar");
		autor.setSobrenome("Castro");
		autor.setShortName("Castro, C");
		
		autorDao.salvar(autor);
		
		assertTrue(autorDao.findAutoresByProps(autor).size()==1);
		
	}

}
