import java.util.Scanner;

public class PalindromeDiscoverer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word or phrase: ");
        String userInput = scanner.nextLine();

        scanner.close();

        System.out.println("'" + userInput + "' is" + (isPalindrome(userInput) ? " " : " not ") + "palindrome.");
    }

    /**
     * Method should return true if a string is identified as a palindrome.
     * There are many ways to do a palindrome check, this is just one of them.
     * If you are performing checks on very, very long strings you may want to
     * consider another algorithm.
     *
     * @param str
     * @return
     */
    public static boolean isPalindrome(String str) {
        str = str.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        for (int i = 0; i <= str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}


