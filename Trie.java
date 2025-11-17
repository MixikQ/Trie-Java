
import java.util.Arrays;

public class Trie {
    private Trie[] childs;
    private boolean terminal;
    private final char letter;
    
    public Trie(char letter) {
        childs = new Trie[0];
        terminal = false;
        this.letter = letter;
    }
    public Trie() {
        this((char) 0);
    }

    public int findLetterIndex(char letter) {
        for (int i = 0; i < this.childs.length; ++i) { // go by elements and return when found
            if (this.childs[i].letter == letter) {  
                return i;
            }
        } 
        return -1; // return -1 if not found
    }

    public void insertWord(String word) {
        word = word.toLowerCase();
        if (word.isEmpty()) return;

        char letter = word.charAt(0); // get first letter from word
        String newWord = word.substring(1); // cut first letter from word

        if (newWord.isEmpty()) {
            terminal = true;
        }

        int index = findLetterIndex(letter);
        if (index >= 0) { // go to next Trie vertex
            Trie NewTrie = this.childs[index];
            NewTrie.insertWord(newWord);
        } else {
            Trie NewTrie = new Trie(letter);
            childs = Arrays.copyOf(childs, childs.length + 1); // add slot to new Trie vertex
            childs[childs.length - 1] = NewTrie; // change last empty element to NewTrie
            NewTrie.insertWord(newWord); // same function with substring
        }
    }

    public boolean containsWord(String word) {
        word = word.toLowerCase();
        if (word.isEmpty()) return true;

        char letter = word.charAt(0); // get first letter from word
        String newWord = word.substring(1); // cut first letter from word

        if (newWord.isEmpty() && this.terminal == false) {
            return false;
        }

        int index = this.findLetterIndex(letter);
        if (index >= 0) {
            Trie newTrie = this.childs[index];
            return newTrie.containsWord(newWord);
        }
        return false;
    }

    public boolean startsWith(String prefix) {
        prefix = prefix.toLowerCase();
        if (prefix.isEmpty()) return true;

        char letter = prefix.charAt(0); // get first letter from prefix
        String newPrefix = prefix.substring(1); // cut first letter from prefix

        int index = this.findLetterIndex(letter);
        if (index >= 0) {
            Trie newTrie = this.childs[index];
            return newTrie.startsWith(newPrefix);
        }
        return false;
    }

    // public List<String> getByPrefix(String prefix) {
    // 
    // }
}
