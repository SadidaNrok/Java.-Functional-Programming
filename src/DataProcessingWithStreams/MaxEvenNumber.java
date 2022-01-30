package DataProcessingWithStreams;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

class MaxEvenNumber {

    public static int findMaxEvenNumber(Collection<Integer> numbers) {
        // write your code here
        return numbers.stream()
                .filter(n -> n % 2 == 0)
                .sorted((o1, o2) -> o2.compareTo(o1))
                .findFirst()
                .orElse(0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        System.out.println(findMaxEvenNumber(numbers));
    }
}