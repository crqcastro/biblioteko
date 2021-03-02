package br.com.cesarcastro.biblioteko.arquitetura.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeArchives;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

public class ServiceArchRulesTest {

	private static final String SERVICE_PACKAGE = "br.com.cesarcastro.biblioteko.service.impl";

	private JavaClasses classes = new ClassFileImporter().withImportOption(new DoNotIncludeTests())
			.withImportOption(new DoNotIncludeJars()).withImportOption(new DoNotIncludeArchives())
			.importPackages(SERVICE_PACKAGE);

	@Test
	public void servicesDeveSerAnotadorComService() {
		ArchRule rule = ArchRuleDefinition.classes().should().beAnnotatedWith(Service.class)
				.because("TODOS OS SERVICES DEVEM TER A ANOTAÇÃO @Service");
		rule.check(classes);
	}

	@Test
	public void servicesDeveSerAnotadorComAutowiredApenasNoConstrutor() {
		ArchRule rule = ArchRuleDefinition.classes().should().notBeAnnotatedWith(Autowired.class)
				.because("APENAS OS CONSTRUTORES DEVEM SER ANOTADOS COM O @Autowired");
		rule.check(classes);
	}

	@Test
	public void servicesDevemSerAcessadosAPenasPorOutrosServicesEControllers() {
		ArchRule rule = ArchRuleDefinition.classes().should().onlyBeAccessed()
				.byAnyPackage("..controller..", "..service..")
				.because("SERVICES SÓ PODEM SER ACESSADOS POR CLASSES DA MESMA CAMADA OU DA CAMADA CONTROLLER");
		rule.check(classes);
	}

	@Test
	public void servicesDevemAcessarApenasOutrosServicesEMappers() {
		ArchRule rule = ArchRuleDefinition.classes().should().accessClassesThat()
				.resideInAnyPackage("..service..", "..dao..", "java.lang..").because(
						"AS CLASSES DA CAMADA SERVICE SÓ PODEM ACESSAR OUTRAS CLASSES DA MESMA CAMADA E DA CAMADA MAPPER");
		rule.check(classes);
	}

	@Test
	public void servicesImplDevemTerNoFInalDONomeDaClasseAStringServiceImpl() {
		ArchRule rule = ArchRuleDefinition.classes().should().haveSimpleNameEndingWith("ServiceImpl")
				.because("TODAS AS CLASSES DA CAMADA DE IMPLEMENTAÇÃO DE SERVIÇO DEVER TER O SUFIXO ServiceImpl");
		rule.check(classes);
	}

}
