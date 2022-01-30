package DeepDiveIntoStreams;

import java.util.stream.Stream;

final class Utils {

    private Utils() { }

    public static Stream<User> generateUsers(int numberOfUsers) {
        // write your code here
        return Stream.iterate(1, n -> n <= numberOfUsers, n -> n + 1)
                .map(n -> new User(n, "user" + n + "@gmail.com"));
    }

    public static void main(String[] args) {
        Stream<User> users = generateUsers(5);
        users.forEach(n -> System.out.println(n.getEmail()));
    }
}

class User {
    private final long id;
    private final String email;

    User(long id, String email) {
        this.id = id;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}