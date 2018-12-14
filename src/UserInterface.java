import edu.kit.informatik.Terminal;

public class UserInterface {

    private boolean exit;

    private SortedIntList list;

    public UserInterface(SortedIntList list) {
        exit = false;
        this.list = list;

        inputLoop();
    }

    public void inputLoop() {
        while (!exit) {
            Terminal.printLine("> ");

            String line = Terminal.readLine();
            switch(line.split(" ")[0]) {
                case "quit":
                    Terminal.printLine("Bye");
                    exit = true;
                    break;
                case "add":
                    String[] cmd = line.split(" ");
                    if (cmd.length != 2) {
                        Terminal.printLine("Wrong amount of arguments. Usage: add [number to be added]");
                        return;
                    }

                    if (cmd[1].matches("^-?[0-9]*$")) {
                        list.add(Integer.parseInt(cmd[1]));
                    } else {
                        Terminal.printLine(cmd[1] + " is supposed to be a number");
                    }
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
