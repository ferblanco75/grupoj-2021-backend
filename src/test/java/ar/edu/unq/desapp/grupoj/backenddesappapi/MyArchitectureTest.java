package ar.edu.unq.desapp.grupoj.backenddesappapi;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class MyArchitectureTest {
    @Test
    public void some_architecture_rule() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("ar.edu.unq.desapp.grupoj.backenddesappapi");

        ArchRule rule = classes().that().areAnnotatedWith(Service.class).or()
                .haveNameMatching(".*Service").should().resideInAPackage("..service..");
        //.orShould().beAnnotatedWith(LegacyBridge.class);// see next section

        rule.check(importedClasses);
    }

}