package br.com.cesarcastro.biblioteko.arquitetura.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

public class ControllersRulesTest {

	@Test
	public void controllersDeveSerAnotadorComRestController() {
		JavaClasses classes = new ClassFileImporter().importPackages("br.com.cesarcastro.biblioteko.controller");
		
		ArchRule rule = ArchRuleDefinition
				.classes().that().resideInAPackage("br.com.cesarcastro.biblioteko.controller")
				.should().beAnnotatedWith(RestController.class)
				.because("tODOS OS CONTROLLERS DEVEM TER A ANOTAÇÃO @RestController");
		
		rule.check(classes);
	}
	
	@Test
	public void controllersDeveSerAnotadorComAutowiredApenasNoConstrutor() {
		JavaClasses classes = new ClassFileImporter().importPackages("br.com.cesarcastro.biblioteko.controller");
		
		ArchRule rule = ArchRuleDefinition
				.classes().should().notBeAnnotatedWith(Autowired.class)
				.because("APENAS OS CONSTRUTORES DEVEM SER ANOTADOS COM O @Autowired");
		
		rule.check(classes);
	}
}
