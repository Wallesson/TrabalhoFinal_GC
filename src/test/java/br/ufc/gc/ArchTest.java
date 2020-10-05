package br.ufc.gc;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("br.ufc.gc");

        noClasses()
            .that()
                .resideInAnyPackage("br.ufc.gc.service..")
            .or()
                .resideInAnyPackage("br.ufc.gc.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..br.ufc.gc.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
