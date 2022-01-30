package ApplyingFunctionsToCollectionsAndMonads;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Account {
    private String owner;
    private long balance;
    private boolean locked;
    private String id;
    private String type;

    Account(String owner, long balance, boolean locked) {
        this.owner = owner;
        this.balance = balance;
        this.locked = locked;
    }

    public Account(String id) {
        this.id = id;
    }

    public Account(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public static Comparator<Account> getComparatorByLockedBalanceAndOwner() {
        // write your code here
        return Comparator.comparing(Account::isLocked).thenComparing(Account::getBalance, Comparator.reverseOrder()).thenComparing(Account::getOwner);
    }

    public String getOwner() {
        return owner;
    }

    public long getBalance() {
        return balance;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return owner + " " + balance + " " + locked;
    }
}

class TestAccount {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();

        accounts.add(new Account("Peter", 1000L, false));
        accounts.add(new Account("John", 1000L, false));
        accounts.add(new Account("Ivan", 8000L, true));
        accounts.add(new Account("Helen", 5000L, false));
        accounts.add(new Account("Nicole", 3000L, true));

        accounts.sort(Account.getComparatorByLockedBalanceAndOwner());

        accounts.forEach(System.out::println);
    }
}