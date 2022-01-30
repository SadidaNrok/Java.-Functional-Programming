package FunctionalInterfacesAndTheirImplementations;

import java.util.function.Predicate;

public class Xor {
    public static <T> Predicate<T> xor(Predicate<T> predicate1, Predicate<T> predicate2) {
        return t -> (predicate1.test(t) || predicate2.test(t)) && !(predicate1.test(t) && predicate2.test(t)); // write your code here
    }

    public static void main(String[] args) {
        Predicate<Integer> p1 = n -> n < 4;
        Predicate<Integer> p2 = n -> n % 5 == 0;
        Predicate<Integer> p3 = xor(p1, p2);

        System.out.println(p3.test(5));
        System.out.println(p3.test(-5));
    }
}
