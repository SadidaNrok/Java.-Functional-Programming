package FunctionalInterfacesAndTheirImplementations;

@FunctionalInterface
public interface TernaryIntPredicate {
    // write your code here
    boolean test(int a, int b, int c);
}

class TestTernary {
    public static final TernaryIntPredicate allValuesAreDifferentPredicate = (a, b, c) -> a != b && a != c && b != c;// write a lambda expression here

    public static void main(String[] args) {
        boolean trueBool = allValuesAreDifferentPredicate.test(1,4,5);
        boolean falseBool = allValuesAreDifferentPredicate.test(1,4,1);
        System.out.println(trueBool);
        System.out.println(falseBool);
    }
}