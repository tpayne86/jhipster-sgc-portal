package com.velocity.sgc.portal;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.velocity.sgc.portal");

        noClasses()
            .that()
            .resideInAnyPackage("com.velocity.sgc.portal.service..")
            .or()
            .resideInAnyPackage("com.velocity.sgc.portal.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.velocity.sgc.portal.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
