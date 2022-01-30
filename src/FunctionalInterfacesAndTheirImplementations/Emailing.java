package FunctionalInterfacesAndTheirImplementations;

import java.util.Scanner;
import java.util.function.Function;

class Emailing {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String sender = scanner.nextLine();
        String receiver = scanner.nextLine();
        String signature = scanner.nextLine();

        Function<String, String> emailFormatter = text -> sender + '|' + receiver + '|' + text + '|' + signature; // modify the lambda expression

        System.out.println(emailFormatter.apply(scanner.nextLine()));
    }
}