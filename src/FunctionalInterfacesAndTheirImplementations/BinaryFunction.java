package FunctionalInterfacesAndTheirImplementations;

@FunctionalInterface
interface BinaryFunction<T, K, R> {
    // write your code here
    R apply(T t, K k);
}
