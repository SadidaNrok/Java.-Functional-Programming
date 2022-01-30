package DeepDiveIntoStreams;

import java.util.Arrays;
import java.util.Scanner;

class NumbersProcessing {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .sorted()
                .map(n -> n * 2)
                .forEach(System.out::println);
    }
}