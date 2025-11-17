
import java.util.Arrays;

public class Trie {
    private Trie[] childs;
    private boolean terminal;
    private final char letter;
    
    public Trie(char letter) {
        childs = new Trie[0];
        terminal = true;
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

        if (terminal != true) { // if last vertex
            Trie NewTrie = new Trie(letter); // create new Trie
            childs = Arrays.copyOf(childs, childs.length + 1); // add slot to new Trie vertex
            childs[childs.length - 1] = NewTrie; // change last empty element to NewTrie

            terminal = false; // set the Trie has childs
            NewTrie.insertWord(newWord); // same function with substring
        }
        else { // if not last
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
    }

    // public boolean containsWord(String word) {
    // 
    // }

    // public boolean startsWith(String prefix) {
    // 
    // }

    // public List<String> getByPrefix(String prefix) {
    // 
    // }
}
