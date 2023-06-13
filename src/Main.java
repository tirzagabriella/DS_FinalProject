import java.io.IOException;
import java.util.Scanner;
//import java.swing;

public class Main {
    public static void main(String[] args) {
        print_header();
        loginPassword();
    }

    private static void loginPassword() {
        Scanner input = new Scanner(System.in);
        int tries = 0;

        while (tries < 3) {
            System.out.print("Enter master password: ");
            String password = input.nextLine();
            if (password.equals("admin")) {
                System.out.println("Login Successful");
                main_menu();
                break;
            } else {
                System.out.println("Login Failed");
                tries++;
                System.out.println("Remaining tries: " + (3 - tries));
            }
        }
        if (tries == 3) {
            System.out.println("You have exceeded the number of tries");
        }
        input.close();
    }

    private static void print_header() {
        System.out.println("*----------------------------------------*");
        System.out.println("|       Password Management System       |");
        System.out.println("*----------------------------------------*");
        System.out.println();
    }

    private static void main_menu() {
        System.out.println("-------->  Main Menu  <-------");
        System.out.println("=====[Doubly Linked List]=====");
        System.out.println("[1]- Update Password");
        System.out.println("[2]- Delete Password");
        System.out.println("[3]- Saved Passwords");
        System.out.println("=========[Array List]=========");
        System.out.println("[4]- Update Password");
        System.out.println("[5]- Delete Password");
        System.out.println("[6]- Saved Passwords");
        System.out.println("==========[Hash Map]==========");
        System.out.println("[7]- Update Password");
        System.out.println("[8]- Delete Password");
        System.out.println("[9]- Saved Passwords");
        System.out.println("=====[Binary Search Tree]=====");
        System.out.println("[10]- Update Password");
        System.out.println("[11]- Delete Password");
        System.out.println("[12]- Saved Passwords");
        System.out.println("==============================");
        System.out.println("[13]- Compare Update Password Data types");
        System.out.println("[14]- Compare Delete Password data types");
        System.out.println("[15]- Compare View Saved Passwords");
        System.out.println("==============================");
        System.out.println("[0]- Exit System");
        System.out.println("------------------------------");
        String choice;
        PasswordManagement pm = new PasswordManagement();
        pm.getAllPasswords();
        Scanner Buffer = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        char c;
        do {
            System.out.print("Choice: ");
            choice = input.nextLine();
            switch (choice) {
                case "0" -> System.exit(0);
                case "1" -> {
                    pm.updatePassword();
                    System.out.println("\nPress any key to return\n");
                    c = sc.next().charAt(0);
                    main_menu();
                }
                case "2" -> {
                    pm.deletePassword();
                    System.out.println("\nPress any key to return\n");
                    c = sc.next().charAt(0);
                    main_menu();
                }
                case "3" -> {                    pm.displayList();
                    System.out.println("\nPress any key to return\n");
                    c = sc.next().charAt(0);
                    main_menu();
                }
                case "4" -> {
                    pm.updatePasswordArraylist();
                    System.out.println("\nPress any key to return\n");
                    c = sc.next().charAt(0);
                    main_menu();
                }
                case "5" -> {
                    pm.deletePasswordArraylist();
                    System.out.println("\nPress any key to return\n");
                    c = sc.next().charAt(0);
                    main_menu();
                }
                case "6" -> {
                    pm.displayFromArraylist();
                    System.out.println("\nPress any key to return\n");
                    c = sc.next().charAt(0);
                    main_menu();
                }
                case "7" -> {
                    pm.updatePasswordHashmap();
                    System.out.println("\nPress any key to return\n");
                    c = sc.next().charAt(0);
                    main_menu();
                }
                case "8" -> {
                    pm.deletePasswordHashmap();
                    System.out.println("\nPress any key to return pm.displayFromHashmap()");
                    System.out.println("\nPress any key to return\n");
                    c = sc.next().charAt(0);
                    main_menu();
                }
                case"9"->{
                    pm.displayFromHashmap();
                    System.out.println("\nPress any key to return\n");
                    c = sc.next().charAt(0);
                    main_menu();

                }
                case "10" -> {
                    pm.updatePasswordBST();
                    System.out.println("\nPress any key to return\n");
                    c = sc.next().charAt(0);
                    main_menu();
                }
                case "11" -> {
                    pm.deletePasswordBST();
                    System.out.println("\nPress any key to return\n");
                    c = sc.next().charAt(0);
                    main_menu();
                }
                case "12" -> {
                    pm.displayFromBST();
                    System.out.println("\nPress any key to return\n");
                    c = sc.next().charAt(0);
                    main_menu();
                }
                case "13"->{
                    pm.compareUpdate();
                    System.out.println("\nPress any key to return\n");
                    c = sc.next().charAt(0);
                    main_menu();
                                    }
                case "14"->{
                    pm.compareDelete();
                    System.out.println("\nPress any key to return\n");
                    c = sc.next().charAt(0);
                    main_menu();
                }
                case "15"->{
                    pm.compareDataType();
                    System.out.println("\nPress any key to return\n");
                    c = sc.next().charAt(0);
                    main_menu();
                }

            }
            // default -> System.out.println("ERROR: Choice not valid");
        } while (choice != "0" && choice != "1" && choice != "2" && choice != "3" && choice != "4");
    }
}