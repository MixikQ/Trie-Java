
public class Menu extends IO {
    private final Trie trie = new Trie();
    
    public Pages printMenu() {
        System.out.println("Current Trie:");
        // print Trie

        System.out.println("\n");
        System.out.println("Available commands:");
        System.out.println("\t1 - Insert word");
        System.out.println("\t2 - Check for word / words with prefix");
        System.out.println("\t3 - Print all words / words with prefix");

        System.out.println("\n\t0 - Exit...");
        System.out.println("\nType number of command:");
        String command = input();
        while (!isCorrectNumber(command, 0, 3)) {
            System.out.println("Wrong number!\nType number of command:");
            command = input();
        }
        return Pages.values()[Integer.parseInt(command)];
    }

    public Pages printInsert() {
        while (true) {
            System.out.println("Type word you want to add to Trie or 0 to return:");
            String command = input();
            while (!isCorrectNumber(command, 0, 0) && !isCorrectString(command)) {
                System.out.println("Wrong input!\nType word you want to add to Trie or 0 to return:");
                command = input();
            }
            if (isCorrectNumber(command, 0, 0)) {
                return Pages.MENU;
            }
            trie.insertWord(command);
            System.out.println("\tWord \"" + command + "\" wes added to the Trie");
        }
        
    }

    public void printCheck() {

    }

    public void printList() {

    }
}
