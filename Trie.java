
import java.util.Arrays;
import java.util.List;

public class Trie{
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
        if (word.isEmpty()) {
            terminal = true;
            return;
        }
        char letter = word.charAt(0); // get first letter from word
        String newWord = word.substring(1); // cut first letter from word

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

    private List<String> getAllWords(String currentWord) {
        String[] listWords = new String[0];
        currentWord += this.letter;

        if (this.terminal == true) { // if word full 
                listWords = Arrays.copyOf(listWords, listWords.length + 1); // add 1 blank space 
                listWords[listWords.length - 1] = currentWord; // fill blank with current word
            }

        for (int i = 0; i < this.childs.length; ++i) { // dfs
            Trie newTrie = this.childs[i]; // go to next child
            
            { // block for temp variables
                String[] newList = newTrie.getAllWords(currentWord).toArray(new String[0]); // recursively get all words with prefix
                String[] tempList = Arrays.copyOf(listWords, newList.length + listWords.length); // add space to newList
                System.arraycopy(newList, 0, tempList, listWords.length, newList.length); // paste newList to copy
                listWords = tempList; // replace listWords with larger tempList
            }
        }
        return Arrays.asList(listWords);
    }

    public List<String> getAllWords() {
        return getAllWords("");
    }

    public List<String> getByPrefix(String prefix) {
        prefix = prefix.toLowerCase();
        if (prefix.isEmpty()) return this.getAllWords();

        char letter = prefix.charAt(0); // get first letter from prefix
        String newPrefix = prefix.substring(1); // cut first letter from prefix

        int index = this.findLetterIndex(letter);
        if (index >= 0) {
            Trie newTrie = this.childs[index];
            return newTrie.getByPrefix(newPrefix);
        }
        return Arrays.asList(new String[0]);
    }
}
