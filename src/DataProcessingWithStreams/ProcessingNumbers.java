package DataProcessingWithStreams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ProcessingNumbers {

    public static Stream<Integer> getStream(Collection<Integer> numbers) {
        // write your code here
        return numbers.stream()
                .sorted((a, b) -> b.compareTo(a))
                .dropWhile(n -> n >= 100)
                .sorted();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Collection<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(HashSet::new));

        String result = getStream(numbers)
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }
}