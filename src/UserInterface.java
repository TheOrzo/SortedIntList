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

            String[] cmd;
            String line = Terminal.readLine();
            switch(line.split(" ")[0]) {
                case "quit":
                    exit = true;
                    break;
                case "add":
                    addCmd(line);
                    break;
                case "get":
                    getCmd(line);
                    break;
                case "print":
                    Terminal.printLine(list.print());
                    break;
                case "size":
                    Terminal.printLine(list.size());
                    break;
                case "isEmpty":
                    Terminal.printLine(list.isEmpty());
                    break;
                case "clear":
                    list.clear();
                    break;
                case "indexOf":
                    indexOfCmd(line);
                    break;
                case "lastIndexOf":
                    lastIndexOfCmd(line);
                    break;
                case "remove":
                    removeCmd(line);
                    break;
                case "contains":
                    containsCmd(line);
                    break;
                    default:
                        Terminal.printLine("Command not found");
                        break;
            }
        }
    }

    private void addCmd(String line) {
        String[]cmd = line.split(" ");
        if (cmd.length != 2) {
            Terminal.printLine("Wrong amount of arguments. Usage: add [number to be added]");
            return;
        }

        if (cmd[1].matches("^-?[0-9]*$")) {
            list.add(Integer.parseInt(cmd[1]));
        } else {
            Terminal.printLine(cmd[1] + " is supposed to be a number");
        }
    }

    private void getCmd(String line) {
        String[] cmd = line.split(" ");
        if (cmd.length != 2) {
            Terminal.printLine("Wrong amount of arguments. Usage: get [index]");
            return;
        }

        if (cmd[1].matches("^[0-9]*$")) {
            Terminal.printLine(list.get(Integer.parseInt(cmd[1])));
        } else {
            Terminal.printLine(cmd[1] + " is supposed to be a possitive index number");
        }
    }

    private void indexOfCmd(String line) {
        String[] cmd = line.split(" ");
        if (cmd.length != 2) {
            Terminal.printLine("Wrong amount of arguments. Usage: indexOf [number to be found]");
            return;
        }

        if (cmd[1].matches("^-?[0-9]*$")) {
            Terminal.printLine(list.indexOf(Integer.parseInt(cmd[1])));
        } else {
            Terminal.printLine(cmd[1] + " is supposed to be a number");
        }
    }

    private void lastIndexOfCmd(String line) {
        String[] cmd = line.split(" ");
        if (cmd.length != 2) {
            Terminal.printLine("Wrong amount of arguments. Usage: lastIndexOf [number to be found]");
            return;
        }

        if (cmd[1].matches("^-?[0-9]*$")) {
            Terminal.printLine(list.lastIndexOf(Integer.parseInt(cmd[1])));
        } else {
            Terminal.printLine(cmd[1] + " is supposed to be a number");
        }
    }

    private void removeCmd(String line) {
        String[] cmd = line.split(" ");
        if (cmd.length != 2) {
            Terminal.printLine("Wrong amount of arguments. Usage: remove [index to be removed]");
            return;
        }

        if (cmd[1].matches("^[0-9]*$")) {
            Terminal.printLine(list.remove(Integer.parseInt(cmd[1])));
        } else {
            Terminal.printLine(cmd[1] + " is supposed to be a possitive index number");
        }
    }

    private void containsCmd(String line) {
        String[] cmd = line.split(" ");
        if (cmd.length != 2) {
            Terminal.printLine("Wrong amount of arguments. Usage: contains [number to be found]");
            return;
        }

        if (cmd[1].matches("^-?[0-9]*$")) {
            Terminal.printLine(list.contains(Integer.parseInt(cmd[1])));
        } else {
            Terminal.printLine(cmd[1] + " is supposed to be a number");
        }
    }
}
