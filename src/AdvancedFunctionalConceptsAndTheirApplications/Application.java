package AdvancedFunctionalConceptsAndTheirApplications;

// create Command interface and Executor class here
interface Command {
    void execute();
}

class Executor {

    public void executeCommand(Command cmd) {
        cmd.execute();
    }
}

class Application {

    private Executor executor;
    private Editor editor;

    private EmailService emailService;
    private SMSService smsService;

    public Application(EmailService emailService, SMSService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public Application(Executor executor, Editor editor) {
        this.executor = executor;
        this.editor = editor;
    }

    public void run() {
        // write your code here
        executor.executeCommand(editor::selectAll);
        executor.executeCommand(() -> editor.saveToClipboard(editor.getSelection()));
        executor.executeCommand(() -> editor.replaceSelection(editor.getClipboard()));
    }

    public void run(User user) {
        // write your code here
        Notifier notifier = new Notifier(emailService::sendEmail);
        notifier.UseNotificationStrategy(user);
        notifier.SetNotificationStrategy(smsService::sendSMS);
        notifier.UseNotificationStrategy(user);
    }
}

class ApplicationTest {
    public static void main(String[] args) {
        Executor executor = new Executor();
        Editor editor = new EditorImpl("Lorem ipsum dolor sit amet");
        Application application = new Application(executor, editor);
        application.run();

        EmailService emailService = new EmailServiceImpl();
        SMSService smsService = new SMSServiceImp();
        application = new Application(emailService, smsService);
        application.run(new User("user@gmail.com", "784-23-10"));
    }
}

interface Editor {

    /**
     * Select all text.
     */
    void selectAll();

    /**
     * Returns previously selected text.
     */
    String getSelection();

    /**
     * Replaces selected text with the specified value.
     *
     * @param value the text that replaces selected text
     */
    void replaceSelection(String value);

    /**
     * Returns text in the clipboard.
     */
    String getClipboard();

    /**
     * Saves provided value to the clipboard.
     *
     * @param value the text that will be saved to the clipboard
     */
    void saveToClipboard(String value);
}

class EditorImpl implements Editor {

    String text;
    String clipboard;

    public EditorImpl(String value) {
        text = value;
    }

    @Override
    public void selectAll() {
        System.out.println("Selecting all: \"" + text + "\"");
    }

    @Override
    public String getSelection() {
        return text;
    }

    @Override
    public void replaceSelection(String value) {
        text = value;
        System.out.println("Replacing selection to: \"" + value + "\"");
    }

    @Override
    public String getClipboard() {
        return clipboard;
    }

    @Override
    public void saveToClipboard(String value) {
        clipboard = value;
        System.out.println("Saving: \"" + value + "\" to clipboard");
    }
}

interface NotificationStrategy {
    void notifyCustomer(User user);
}

class Notifier {
    private NotificationStrategy notificationStrategy;

    public Notifier(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    public void SetNotificationStrategy(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    public void UseNotificationStrategy(User user) {
        notificationStrategy.notifyCustomer(user);
    }
}

class User {
    private final String email;
    private final String phoneNumber;

    User(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

interface SMSService {

    void sendSMS(User user);
}

interface EmailService {

    void sendEmail(User user);
}

class SMSServiceImp implements  SMSService {

    @Override
    public void sendSMS(User user) {
        System.out.println("Sending sms to " + user.getPhoneNumber());
    }
}

class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(User user) {
        System.out.println("Sending email to " + user.getEmail());
    }
}