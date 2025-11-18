
public class Menu extends IO {
    private final Trie trie = new Trie();
    
    public void printMenu() {
        System.out.println("Current Trie:");
        // print Trie

        System.out.println("\n");
        System.out.println("Available commands:");
        System.out.println("\t1 - Insert word");
        System.out.println("\t2 - Check for word");
        System.out.println("\t3 - Check for words with prefix");
        System.out.println("\t4 - Print all words");
        System.out.println("\t5 - Print all words with prefix");
        System.out.println("\n\t0 - Exit...");
        System.out.println("\nType number of command:");
        String command = input();
        while (!isCorrectNumber(command)) {
            System.out.println("Wrong number!\nType number of command:");
            command = input();
        }
    }
}
