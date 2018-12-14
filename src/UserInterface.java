import edu.kit.informatik.Terminal;

public class UserInterface {

    private boolean exit;

    private SortedIntList list;

    public UserInterface() {
        exit = false;
        list = new SortedIntList();

        inputLoop();
    }

    public void inputLoop() {
        while (!exit) {
            Terminal.printLine("> ");

            String line = Terminal.readLine();
            switch(line) {
                case "quit":
                    Terminal.printLine("Bye");
                    exit = true;
                    break;
                case "print":
                    Terminal.printLine(list.print());
                    break;

                    default:
                        Terminal.printLine("Command not found");
                        break;
            }
        }
    }
}
