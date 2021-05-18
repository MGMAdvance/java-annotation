import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.lang.reflect.Field;
import java.util.Set;

@SupportedAnnotationTypes(
        "RunnerAnnotation"
)
public class RunnerAnnotationProcessor extends AbstractProcessor {

    public static <T> void validador(T objeto) {
        Class<?> classe = objeto.getClass();

        for (Field field : classe.getDeclaredFields()) {
            if (field.isAnnotationPresent(RunnerAnnotation.class)) {
                RunnerAnnotation runnerAnnotation = field.getAnnotation(RunnerAnnotation.class);

                String output = String.format(
                        "Name: %s\nAge: %d\n",
                        runnerAnnotation.name(),
                        runnerAnnotation.age()
                );

                System.out.println(output);
            }
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        for (TypeElement te : annotations) {
            for (Element e : roundEnvironment.getElementsAnnotatedWith(te)) {
                String message = "Annotation " + te.getSimpleName() + " for " + e.toString();
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
            }
        }
        return true;
    }
}
