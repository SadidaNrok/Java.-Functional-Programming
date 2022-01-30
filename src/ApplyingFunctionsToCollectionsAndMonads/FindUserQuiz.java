package ApplyingFunctionsToCollectionsAndMonads;

import java.util.*;
import java.util.stream.Collector;

class FindUserQuiz {

    public static Optional<User> findUserByAccountId(Set<User> users, String id) {
        // write your code here
        return users.stream()
                .filter(n -> n.getAccount()
                        .get().getId().equals(id))
                .filter(n -> n.getAccount().isPresent())
                .findFirst();
    }
}

class User {
    private final String login;
    private final Account account;

    public User(String login, Account account) {
        this.login = login;
        this.account = account;
    }

    public String getLogin() {
        return login;
    }

    public Optional<Account> getAccount() {
        return Optional.ofNullable(account);
    }
}