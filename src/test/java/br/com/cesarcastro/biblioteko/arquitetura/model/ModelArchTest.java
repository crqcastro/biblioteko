package br.com.cesarcastro.biblioteko.arquitetura.model;

import org.junit.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeArchives;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import br.com.cesarcastro.biblioteko.arquitetura.ArchUnitPersonalizedExtensions;

public class ModelArchTest {

	private static final String MODEL_PACKAGE = "br.com.cesarcastro.biblioteko.model";

	private JavaClasses classes = new ClassFileImporter().withImportOption(new DoNotIncludeTests())
			.withImportOption(new DoNotIncludeJars()).withImportOption(new DoNotIncludeArchives())
			.importPackages(MODEL_PACKAGE);

	@Test
	public void modelsDevemTerTerConstrutorPadrao() {
		ArchRule rule = ArchRuleDefinition.classes().that().haveSimpleNameEndingWith("Model")
				.should(ArchUnitPersonalizedExtensions.haveDefaultConstructor())
				.because("MODELS PRECISAM TER CONSTRUTOR PADRÃO USADOS NA SEREALIZAÇÃO/DESSEREALIZAÇÃO");

		rule.check(classes);
	}

}
