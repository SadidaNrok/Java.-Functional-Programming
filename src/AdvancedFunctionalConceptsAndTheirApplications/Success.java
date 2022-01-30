package AdvancedFunctionalConceptsAndTheirApplications;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Represents an operation that may potentially fail with an exception
 */
@FunctionalInterface
interface ThrowableOperation<T> {
    T execute() throws Throwable;
}

/**
 * Represents an action that may potentially fail with an exception
 */
@FunctionalInterface
interface ThrowableConsumer<T, E extends Throwable> {
    void accept(T t) throws E;
}

/**
 * Represents the result of a computation that could have succeeded with a value of the type T
 * or failed with a Throwable.
 */

/**
 * Represents a Function that accepts T and returns R and may fail with an exception
 */
@FunctionalInterface
interface ThrowableFunction<T, R> {
    R apply(T t) throws Throwable;
}

interface Try<T> {
    /**
     * Executes the given operation and returns the result wrapped in a Success or Failure
     */
    static <T> Try<T> of(ThrowableOperation<T> operation) {
        // write your code here
        try {
            return new Success(operation.execute());
        } catch (Throwable e) {
            return new Failure<>(e);
        }
    }

    /**
     * Returns true if the original operation succeeded, otherwise returns false
     */
    boolean isSuccess();

    /**
     * Returns the resulting value if this is a Success, otherwise throws the original exception
     */
    T get() throws Throwable;

    /**
     * Returns the resulting value if this is a Success,
     * otherwise throws the original exception wrapped in a RuntimeException
     *
     * @throws RuntimeException that wraps the original exception
     */
    T getUnchecked();

    /**
     * Converts this to a non-empty Optional that wraps the resulting value if this is Success,
     * otherwise returns an empty Optional
     */
    Optional<T> toOptional();

    /**
     * Returns the given default value if this is a Failure,
     * otherwise returns the resulting value
     */
    T getOrElse(T defaultValue);

    /**
     * Returns the resulting value if it is a Success,
     * otherwise returns the result produced by the given supplier
     */
    T getOrElseSupply(Supplier<? extends T> supplier);

    /**
     * Returns the given default value if this is a Failure,
     * otherwise throws an exception produced by the exception supplier
     *
     * @throws Throwable produced by the exception supplier
     */
    <X extends Throwable> T getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X;

    /**
     * Applies the given action to the resulting value if it is a Success, otherwise does nothing
     *
     * @return the current Try object
     * @throws E if the action throws an exception
     */
    <E extends Throwable> Try<T> onSuccess(ThrowableConsumer<T, E> action) throws E;

    /**
     * Applies the given action to the exception if it is a Failure, otherwise does nothing
     *
     * @return the current Try object
     * @throws E if the action throws an exception
     */
    <E extends Throwable> Try<T> onFailure(ThrowableConsumer<Throwable, E> action) throws E;

    /**
     * Converts this Success into a Failure (which holds NoSuchElementException)
     * if it is a Success and the predicate doesn't match for the value,
     * otherwise returns this Try (Success or Failure)
     */
    Try<T> filter(Predicate<T> predicate);

    /**
     * Applies the given function to the resulting value if is a Success, otherwise returns this Failure.
     * If that function fails a Failure is returned
     */
    <U> Try<U> map(ThrowableFunction<? super T, ? extends U> function);

    /**
     * Applies the given function to the resulting value if is a Success, otherwise returns this Failure.
     * If that function fails a Failure is returned.
     *
     * This method is similar to {@link Try#map}, but the mapping function already returns a Try.
     * Being invoked this method doesn't wrap the result of this function within a nested optional
     */
    <U> Try<U> flatMap(ThrowableFunction<? super T, Try<U>> function);

    /**
     * Applies the given function to recover from the throwable if it is a Failure,
     * otherwise returns this Success.
     *
     * @return a new Try in the case of failure, or the current Success
     */
    Try<T> recover(ThrowableFunction<? super Throwable, T> function);

    /**
     * Applies the given function to recover from the throwable if it is a Failure,
     * otherwise returns this Success.
     *
     * This method is similar to {@link Try#recover}, but the recovering function already returns a Try.
     * Being invoked this method doesn't wrap the result of this function within a nested optional
     *
     * @return a new Try provided by the given function in the case of failure, or the current Success
     */
    Try<T> recoverWith(ThrowableFunction<? super Throwable, Try<T>> function);
}


/**
 * Represents a successful execution
 */
class Success<T> implements Try<T> {
    private final T value;

    Success(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        // write your code here
        return value;
    }

    @Override
    public T getUnchecked() {
        // write your code here
        return value;
    }

    @Override
    public Optional<T> toOptional() {
        // write your code here
        return Optional.of(value);
    }

    @Override
    public boolean isSuccess() {
        // write your code here
        return true;
    }

    @Override
    public T getOrElse(T defaultValue) {
        // write your code here
        return value;
    }

    @Override
    public T getOrElseSupply(Supplier<? extends T> supplier) {
        // write your code here
        return value;
    }

    @Override
    public <X extends Throwable> T getOrElseThrow(Supplier<? extends X> exceptionSupplier) {
        // write your code here
        return value;
    }

    @Override
    public <E extends Throwable> Try<T> onSuccess(ThrowableConsumer<T, E> action) throws E {
        // write your code here
        action.accept(value);
        return this;
    }

    @Override
    public <E extends Throwable> Try<T> onFailure(ThrowableConsumer<Throwable, E> action) {
        // write your code here
        return this;
    }

    @Override
    public Try<T> filter(Predicate<T> predicate) {
        // write your code here
        if (predicate.test(value))
            return this;
        else
            return new Failure<>(new NoSuchElementException());
    }

    @Override
    public <U> Try<U> map(ThrowableFunction<? super T, ? extends U> function) {
        // write your code here
        return Try.of(() -> function.apply(value));
    }

    @Override
    public <U> Try<U> flatMap(ThrowableFunction<? super T, Try<U>> function) {
        // write your code here
        return Try.of(() -> function.apply(value).getOrElseThrow(Exception::new));
    }

    @Override
    public Try<T> recover(ThrowableFunction<? super Throwable, T> function) {
        // write your code here
        return this;
    }

    @Override
    public Try<T> recoverWith(ThrowableFunction<? super Throwable, Try<T>> function) {
        // write your code here
        return this;
    }

    @Override
    public String toString() {
        return "Success[" + value + "]";
    }
}

/**
 * Represents a failed execution
 */
class Failure<T> implements Try<T> {
    private final Throwable e;

    Failure(Throwable e) {
        this.e = e;
    }

    @Override
    public T get() throws Throwable {
        // write your code here
        throw e;
    }

    @Override
    public T getUnchecked() {
        // write your code here
        throw new RuntimeException(e);
    }

    @Override
    public Optional<T> toOptional() {
        // write your code here
        return Optional.empty();
    }

    @Override
    public boolean isSuccess() {
        // write your code here
        return false;
    }

    @Override
    public T getOrElse(T defaultValue) {
        // write your code here
        return  defaultValue;
    }

    @Override
    public T getOrElseSupply(Supplier<? extends T> supplier) {
        // write your code here
        return supplier.get();
    }

    @Override
    public <X extends Throwable> T getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        // write your code here
        throw  exceptionSupplier.get();
    }

    @Override
    public <E extends Throwable> Try<T> onSuccess(ThrowableConsumer<T, E> action) {
        return this;
    }

    @Override
    public <E extends Throwable> Try<T> onFailure(ThrowableConsumer<Throwable, E> action) throws E {
        // write your code here
        action.accept(e);
        return this;
    }

    @Override
    public Try<T> filter(Predicate<T> predicate) {
        // write your code here
        return this;
    }

    @Override
    public <U> Try<U> map(ThrowableFunction<? super T, ? extends U> function) {
        // write your code here
        return new Failure<>(e);
    }

    @Override
    public <U> Try<U> flatMap(ThrowableFunction<? super T, Try<U>> function) {
        // write your code here
        return new Failure<>(e);
    }

    @Override
    public Try<T> recover(ThrowableFunction<? super Throwable, T> function) {
        // write your code here
        return Try.of(() -> function.apply(e));
    }

    @Override
    public Try<T> recoverWith(ThrowableFunction<? super Throwable, Try<T>> function) {
        // write your code here
        return Try.of(() -> function.apply(e).getOrElseThrow(Exception::new));
    }

    @Override
    public String toString() {
        return "Failure[" + e + "]";
    }
}