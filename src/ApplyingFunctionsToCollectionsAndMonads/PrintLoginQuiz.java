package ApplyingFunctionsToCollectionsAndMonads;

import java.util.*;

class PrintLoginQuiz {

    public static void printLoginIfPro(Set<User> users, String id) {
        // write your code here
        users.stream()
                .filter(n -> n.getAccount().map(Account::getId).map(id::equals).orElse(false))
                .filter(n -> n.getAccount().map(Account::getType).map("pro"::equals).orElse(false))
                .findAny()
                .ifPresent(n -> System.out.println(n.getLogin()));
    }
}
