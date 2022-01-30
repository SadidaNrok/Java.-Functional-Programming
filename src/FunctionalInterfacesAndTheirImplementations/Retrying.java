package FunctionalInterfacesAndTheirImplementations;

import java.util.Scanner;

@FunctionalInterface
interface Retry {

    // declare the run method here
    int run(Runnable runnable, int attempts, long delay);
}

final class RetryUtils {
    public static Retry retry = (r, a, d) -> retryAction(r, a, d); // assign the retryAction method to this variable

    private RetryUtils() { }

    public static int retryAction(
            Runnable action, int maxAttempts, long delayBeforeRetryMs) {

        int fails = 0;
        while (fails < maxAttempts) {
            try {
                action.run();
                return fails;
            } catch (Exception e) {
                System.out.println("Something goes wrong");
                fails++;
                try {
                    Thread.sleep(delayBeforeRetryMs);
                } catch (InterruptedException interruptedException) {
                    throw new RuntimeException(interruptedException);
                }
            }
        }
        return fails;
    }
}

public class Retrying {
    private static final int MAX_ATTEMPTS = 10;
    private static final long DELAY_MS = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RetryUtils.retry.run(() -> System.out.println(scanner.nextLine()), MAX_ATTEMPTS, DELAY_MS);
    }
}