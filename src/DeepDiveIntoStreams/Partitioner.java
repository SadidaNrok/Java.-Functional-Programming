package DeepDiveIntoStreams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Partitioner {

    public static Map<Boolean, List<Application>> getPartition(List<Application> applications) {
        // write your code here
        return applications.stream().collect(Collectors.partitioningBy(Application::isFree));
    }

    public static void main(String[] args) {
        List<Application> applications = List.of(
                new Application("Game", true),
                new Application("Soft", false),
                new Application("MiniSoft", true)
        );

        Map<Boolean, List<Application>> appsMap = getPartition(applications);
        appsMap.values().stream()
                .flatMap(Collection::stream)
                .map(Application::getName)
                .forEach(System.out::println);

        appsMap.get(true).stream()
                .map(Application::getName)
                .map(n -> n + " true")
                .forEach(System.out::println);

        appsMap.get(false).stream()
                .map(Application::getName)
                .map(n -> n + " false")
                .forEach(System.out::println);
    }
}

class Application {
    private final String name;
    private final boolean isFree;

    public Application(String name, boolean isFree) {
        this.name = name;
        this.isFree = isFree;
    }

    public String getName() {
        return name;
    }

    public boolean isFree() {
        return isFree;
    }
}