package TestDrivenSpringBoot;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.INTERFACES;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;

@AnalyzeClasses(packages = "TestDrivenSpringBoot",
        importOptions = {ImportOption.DoNotIncludeTests.class, ImportOption.DoNotIncludeJars.class, ImportOption.DoNotIncludeArchives.class}
)
public class CodingConventionRules {
        @ArchTest
        ArchRule rest_controllers_are_stateless_and_depend_on_interfaces =
        fields().that().areDeclaredInClassesThat().areAnnotatedWith(RestController.class)
                .should().beFinal()
                .andShould().bePrivate()
                .andShould().haveRawType(INTERFACES);
}
