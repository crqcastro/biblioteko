package br.com.cesarcastro.biblioteko.arquitetura;

import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

import org.junit.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeArchives;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

public class GenericArchTest {

	private static final String CONTROLLER = "Controller";
	private static final String SERVICE = "Service";

	private static final String ALL_PACKAGE = "br.com.cesarcastro.biblioteko..";

	private JavaClasses classes = new ClassFileImporter().withImportOption(new DoNotIncludeTests())
			.withImportOption(new DoNotIncludeJars()).withImportOption(new DoNotIncludeArchives())
			.importPackages(ALL_PACKAGE);

	@Test
	public void naoDevelancarExceptionsGenericas() {
		ArchRule rule = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS
				.because("NÃO DEVEM SER LANÇADAS EXCEPTIONS GENÉRICAS");
		rule.check(classes);
	}

	@Test
	public void nenhumaClasseDeveAcessarStreamPadrao() {
		ArchRule rule = NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS
				.because("NENHUMA CLASSE DEVE ESCREVER DIRETAMENTE NO CONSOLE. DEVE-SE USAR O LOGGER");
		rule.check(classes);
	}

	@Test
	public void deveSerMantidoAIntegridadeEntreCamadas() {
		ArchRule rule = Architectures.layeredArchitecture().layer(CONTROLLER).definedBy("..controller").layer(SERVICE)
				.definedBy("..service.impl").whereLayer(CONTROLLER).mayNotBeAccessedByAnyLayer().whereLayer(SERVICE)
				.mayOnlyBeAccessedByLayers(CONTROLLER, SERVICE)
				.because("DEVE-SE MANTER A SEPARAÇÃO DAS CAMADAS E SEUS ACESSOS");
		rule.check(classes);
	}

	@Test
	public void naoDevemExistirDependenciasCiclicas() {
		ArchRule rule = SlicesRuleDefinition.slices().matching("..(*)").should().beFreeOfCycles()
				.because("NAO DEVEM EXISTIR DEPENDENCIAS CICLICAS	");
		rule.check(classes);
	}
}
