
import java.util.Scanner;

abstract public class IO {
    Scanner sc = new Scanner(System.in);

    public String input() {
        return sc.nextLine().toLowerCase().strip();
    }

    public boolean isCorrect(String s) {
        for (char iter : s.toCharArray()) {
            if (!"abcdefghijklmnopqrstuvwxyz".contains(String.valueOf(iter))) return false;
        }
        return true;
    }
}
