package AdvancedFunctionalConceptsAndTheirApplications;

import java.util.function.Function;
import java.util.function.Predicate;

class ExtensionService {

    public static Function<String, String> addExtension(Predicate<String> predicate1, Predicate<String> predicate2) {
        // write your code here
        return n-> {
            if (predicate1.test(n)) return n.concat(".xml");
            else if (predicate2.test(n)) return n.concat(".json");
            else return n;
        };
    }

    public static void main(String[] args) {
        Function<String, String> function = addExtension(
                reportName -> reportName.contains("report"),
                logsName -> logsName.contains("logs")
        );

        String file = function.apply("main_report");

        System.out.println(file); // main_report.xml
    }
}
