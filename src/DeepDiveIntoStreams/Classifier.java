package DeepDiveIntoStreams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Classifier {

    public static Map<Integer, List<String>> group(List<String> words) {
        // write your code here
        return words.stream().collect(Collectors.groupingBy(String::length));
    }

    public static void main(String[] args) {
        List<String> words = List.of("Java", "stream", "main", "util", "int");
        Map<Integer, List<String>> group = group(words);
        group.entrySet().stream()
                .map(n -> n.getValue() + " - " + n.getKey())
                .forEach(System.out::println);
    }
}