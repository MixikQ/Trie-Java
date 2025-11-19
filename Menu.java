
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
            System.out.println("\tWord \"" + command + "\" was added to the Trie");
        }
    }

    public Pages printCheck() {
        System.out.println("To check with prefix just type a word. Example: \"word\"");
        System.out.println("To check for word type a word with a dot at the end. Example: \"word.\"");
        while (true) { 
            System.out.println("Type word or prefix you want to check or 0 to return:");
            String command = input();
            boolean isWord = (command.endsWith("."));
            command = command.replace(".", "");
            while (!isCorrectNumber(command, 0, 0) && !isCorrectString(command)) {
                System.out.println("Wrong input!\nType word or prefix you want to check or 0 to return:");
                command = input();
                isWord = (command.endsWith("."));
                command = command.replace(".", "");
            }
            if (isCorrectNumber(command, 0, 0)) {
                return Pages.MENU;
            }
            if (isWord) {
                if (trie.containsWord(command)) {
                    System.out.println("\tWord \"" + command + "\" contains in the Trie");
                } else {
                    System.out.println("\tWord \"" + command + "\" does not contain in the Trie");
                }
            } else {
                if (trie.startsWith(command)) {
                    System.out.println("\tWord with prefix \"" + command + "\" contains in the Trie");
                } else {
                    System.out.println("\tWord with prefix \"" + command + "\" does not contain in the Trie");
                }
            }
        }
        
    }

    public Pages printList() {
        while (true) { 
            System.out.println("Type prefix you want to find all words starting with or 0 to return:");
            String command = input();
            while (!isCorrectNumber(command, 0, 0) && !isCorrectString(command)) {
                System.out.println("Wrong input!\nType prefix you want to find all words starting with or 0 to return:");
                command = input();
            }
            if (isCorrectNumber(command, 0, 0)) {
                return Pages.MENU;
            }
            String[] wordList = trie.getByPrefix(command).toArray(new String[0]);
            if (wordList.length > 0) {
                System.out.println("Found words starting with \"" + command + "\":");
                for (String iter : wordList) {
                    System.out.println(iter);
                }
            } else {
                System.out.println("Nothing is found");
            }  
        }
    }
}
