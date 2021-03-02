package br.com.cesarcastro.biblioteko.arquitetura.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeArchives;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

public class ControllersArchRulesTest {

	private static final String CONTROLLERS_PACKAGE = "br.com.cesarcastro.biblioteko.controller";

	private JavaClasses classes = new ClassFileImporter().withImportOption(new DoNotIncludeTests())
			.withImportOption(new DoNotIncludeJars()).withImportOption(new DoNotIncludeArchives())
			.importPackages(CONTROLLERS_PACKAGE);

	@Test
	public void controllersDeveSerAnotadorComRestController() {
		ArchRule rule = ArchRuleDefinition.classes().should().beAnnotatedWith(RestController.class)
				.because("TODOS OS CONTROLLERS DEVEM TER A ANOTAÇÃO @RestController");
		rule.check(classes);
	}

	@Test
	public void controllersDeveSerAnotadorComAutowiredApenasNoConstrutor() {
		ArchRule rule = ArchRuleDefinition.classes().should().notBeAnnotatedWith(Autowired.class)
				.because("APENAS OS CONSTRUTORES DEVEM SER ANOTADOS COM O @Autowired");
		rule.check(classes);
	}

	@Test
	public void controllersDevemSerAcessadosAPenasPorOutrosControllers() {
		ArchRule rule = ArchRuleDefinition.classes().should().onlyBeAccessed().byAnyPackage("..controller..")
				.because("CONTROLLERS SÓ PODEM SER ACESSADOS POR CLASSES DA MESMA CAMADA");
		rule.check(classes);
	}

	@Test
	public void controllersDevemTerMetodosPublicosQuerRetornamResponseEntitys() {
		ArchRule rule = ArchRuleDefinition.methods().that().arePublic().should().haveRawReturnType(ResponseEntity.class)
				.because("DEVE RETORNAR SEMPRE UMA ResponseEntity.class NOS MÉTODOS PUBLICOS DOS CONTROLLERS");
		rule.check(classes);
	}

	@Test
	public void controllersDevemTerNoFInalDONomeDaClasseAStringController() {
		ArchRule rule = ArchRuleDefinition.classes().should().haveSimpleNameEndingWith("Controller")
				.because("TODAS AS CLASSES DA CAMADA DE CONTROLE DEVER TER O SUFIXO Controller");
		rule.check(classes);
	}

	@Test
	public void controllersDeveSeranotadosComRequestMapping() {
		ArchRule rule = ArchRuleDefinition.classes().should().beAnnotatedWith(RequestMapping.class)
				.because("CONT5ROLLERS DEVEM SER ANOTADOS COM @RequestMapping");
		rule.check(classes);
	}
}
