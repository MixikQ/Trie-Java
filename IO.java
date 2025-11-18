
import java.util.Scanner;

abstract public class IO {
    Scanner sc = new Scanner(System.in);

    public String input() {
        return sc.nextLine().toLowerCase().strip();
    }

    public boolean isCorrectString(String s) {
        for (char iter : s.toCharArray()) {
            if (!"abcdefghijklmnopqrstuvwxyz".contains(String.valueOf(iter))) return false;
        }
        return true;
    }

    public boolean isCorrectNumber(String s) {
        return "012345".contains(s);
    }

    public void exit() {
        System.out.println("Exiting the program. Press Enter to continue...");
        input();
        System.exit(0);
    }
}
