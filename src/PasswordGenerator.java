import java.util.Stack;
import java.util.Random;

public class PasswordGenerator {
    public String generatePassword(int size) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()_+-/.,<>?;':[]{}\"\\|`~";

        Stack<Character> stack = new Stack<>();
        Random r = new Random();

        while (stack.size() < size) {
            int index = (int) (r.nextFloat() * chars.length());
            char randomChar = chars.charAt(index);
            stack.push(randomChar);
        }

        StringBuilder password = new StringBuilder();
        while (!stack.isEmpty()) {
            password.append(stack.pop());
        }

        return password.toString();
    }

    public static void main(String[] args) {
        int passwordLength = 8; // Change the length as per your requirement
        PasswordGenerator generator = new PasswordGenerator();
        String password = generator.generatePassword(passwordLength);
        System.out.println("Generated Password: " + password);
    }
}