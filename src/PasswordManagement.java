import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class PasswordManagement {
    Scanner sc = new Scanner(System.in);
    PasswordGenerator passwordGenerator = new PasswordGenerator();


    private NodePassword first;
    private NodePassword last;

    private ArrayList<PasswordObj> PasswordArrayList;

    private HashMap<String, String> PasswordHashmap;
    private BST PasswordBST;
    public PasswordManagement() {
        this.first = null;
        this.last = null;
        PasswordArrayList = new ArrayList<>();
        PasswordHashmap = new HashMap<>();
        PasswordBST = new BST();
    }

    public void addPassword(String ServiceName, String username, String password) {
        /*
        System.out.println("==> ADD <==");

        System.out.println("Enter Service Name:");
        String ServiceName = sc.nextLine();

        System.out.println("Enter Username:");
        String username = sc.nextLine();

        String option = "";
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Do you want to generate password? (y/n) ");
            option = sc.nextLine();

            if (!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")) {
                System.out.println("Invalid choice");
            }
        } while (!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N"));


        String password = "";

        int length;
        if (option.equals("y") || option.equals("Y")) {
            do {
                System.out.println("Enter size:");
                length = sc.nextInt();

                if (length < 8) {
                    System.out.println("Minimum Password length = 8");
                }
            } while (length < 8);

            sc = new Scanner(System.in); // Clearing Input Buffer
            // password = passwordGenerator.generatePassword(length);
        } else {
            Boolean weak;
            do {
                ArrayList<String> errorMessages = new ArrayList<>();
                int strength = 5;
                weak = false;
                System.out.println("Enter Password: ");
                password = sc.nextLine();
                if (password == ServiceName) {
                    errorMessages.add("Password cannot be same as the Service name");
                    weak = true;

                }

                if (password == username) {
                    errorMessages.add("Password cannot be same as your Username");
                    weak = true;

                }

                if (password.length() < 8) {
                    errorMessages.add("Password should contain at least 8 characters");
                    weak = true;
                    strength--;
                }

                if (!password.matches(".*\\d.*\\d.*")) {
//                if (!password.matches("^(?=.*?\\d.*\\d)[a-zA-Z0-9]{8,}$")) {
                    errorMessages.add("Password must contain at least two numbers.");
                    weak = true;
                    strength--;
                }

                if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
                    errorMessages.add("Password must contain at least two special characters");
                    weak = true;
                    strength--;
                }

                if (!password.matches(".*[A-Z].*")) {
                    errorMessages.add("Password must contain at least one Uppercase character");
                    weak = true;
                    strength--;
                }

                if (!password.matches(".*[a-z].*")) {
                    errorMessages.add("Password must contain at least one Lowercase character");
                    weak = true;
                    strength--;
                }
                System.out.println("Password Strength:" + strength + " out of 5");

                for (int i = 0; i < 5; i++) {
                    if (strength > 0) {
                        System.out.print('\u25A0');
                        strength--;
                    } else {
                        System.out.print('\u25A1');
                    }
                }

                System.out.println();

                if (weak) {
                    System.out.println("Errors:");
                    for (String errorMessage : errorMessages) {
                        System.out.println(errorMessage);
                    }
                }
                System.out.println();

            } while (weak == true);
        }
        */

        NodePassword newNode = new NodePassword();
        newNode.serviceName = ServiceName;
        newNode.userName = username;
        newNode.password = password;
        if (first == null) {
            first = newNode;
        } else {
            last.next = newNode;
            newNode.previous = last;
        }
        this.last = newNode;

        /*
        String data = ServiceName + "<->" + username + "<->" + password + "\n";

        File file = new File("passwordDB.txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, true);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
            throw new RuntimeException(e);
        }
        */
    }

    // update from doubly linked list
    public void updatePassword() {

        Scanner input = new Scanner(System.in);
        String sn, un;

        System.out.println("Enter Service Name:");
        sn = input.nextLine();

        System.out.println("Enter Username:");
        un = input.nextLine();

        sc = new Scanner(System.in); // Clearing Input Buffer

        int flag = 0;
        NodePassword current = first;

        while (current != null && flag == 0) {
            if (sn.equals(current.serviceName) && un.equals((current.userName))) {
                System.out.println("Data Found");


                System.out.println("[1] To change password manually");
                System.out.println("[2] To change generate new password");
                String pass = "";
                short choice;
                do {
                    System.out.print("Choice: ");
                    choice = input.nextShort();
                    switch (choice) {
                        case 1:
                            System.out.println();
                            input = new Scanner(System.in); // refresh scanner to avoid errors
                            System.out.print("Enter Password: ");
                            pass = input.nextLine();
                            current.password = pass;
                            System.out.println();
                            break;
                        case 2:
                            int l;
                            do {
                                System.out.println();
                                input = new Scanner(System.in); // refresh scanner to avoid errors
                                System.out.println("Enter Length of you Password");
                                l = input.nextInt();
                                if (l < 8) {
                                    System.out.println("Minimum Password length = 8");
                                }
                            } while (l < 8);
                             pass = passwordGenerator.generatePassword(l);
                            current.password = pass;
                            break;
                        default:
                            System.out.println("ERROR: Choice not valid");
                    }
                } while (choice < 1 || choice > 2);
                flag = 1;
            }
            current = current.next;
        }

        if (flag == 0) {
            System.out.println("Data does not exist!");
        } else {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter("passwordDB.txt");
                writer.print("");
                writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                e.printStackTrace();
            }

            NodePassword c = first;
            while (c != null) {
                String data = c.serviceName + "<->" + c.userName + "<->" + c.password + "\n";

                File file = new File("passwordDB.txt");
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(file, true);
                    fileWriter.write(data);
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Error");
                    throw new RuntimeException(e);
                }
                c = c.next;
            }
        }

    }

    // update from arraylist
    public void updatePasswordArraylist() {

        Scanner input = new Scanner(System.in);
        String sn, un;

        System.out.println("Enter Service Name:");
        sn = input.nextLine();

        System.out.println("Enter Username:");
        un = input.nextLine();

        sc = new Scanner(System.in); // Clearing Input Buffer

        int flag = 0;

        for(int i = 0; i < PasswordArrayList.size(); i++) {
            if (sn.equals(PasswordArrayList.get(i).getServiceName()) && un.equals((PasswordArrayList.get(i).getUserName()))) {
                PasswordObj newObj = PasswordArrayList.get(i);
                System.out.println("Data Found");
                System.out.println("[1] To change password manually");
                System.out.println("[2] To change generate new password");
                String pass = "";
                short choice;
                do {
                    System.out.print("Choice: ");
                    choice = input.nextShort();
                    switch (choice) {
                        case 1:
                            System.out.println();
                            input = new Scanner(System.in); // refresh scanner to avoid errors
                            System.out.print("Enter Password: ");
                            pass = input.nextLine();
                            newObj.setPassword(pass);
                            PasswordArrayList.set(i, newObj) ;
                            System.out.println();
                            break;
                        case 2:
                            int l;
                            do {
                                System.out.println();
                                input = new Scanner(System.in); // refresh scanner to avoid errors
                                System.out.println("Enter Length of you Password");
                                l = input.nextInt();
                                if (l < 8) {
                                    System.out.println("Minimum Password length = 8");
                                }
                            } while (l < 8);
                            pass = passwordGenerator.generatePassword(l);
                            newObj.setPassword(pass);
                            PasswordArrayList.set(i, newObj) ;
                            break;
                        default:
                            System.out.println("ERROR: Choice not valid");
                    }
                } while (choice < 1 || choice > 2);
                flag = 1;
                break;
            }
        }

        if (flag == 0) {
            System.out.println("Data does not exist!");
        } else {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter("passwordDB.txt");
                writer.print("");
                writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                e.printStackTrace();
            }

            for(int i = 0; i < PasswordArrayList.size(); i++) {
                String data = PasswordArrayList.get(i).getServiceName() + "<->" + PasswordArrayList.get(i).getUserName() + "<->" + PasswordArrayList.get(i).getPassword() + "\n";

                File file = new File("passwordDB.txt");
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(file, true);
                    fileWriter.write(data);
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Error");
                    throw new RuntimeException(e);
                }
            }
        }

    }

    // update from hashmap
    public void updatePasswordHashmap() {
        Scanner input = new Scanner(System.in);
        String sn, un;

        System.out.println("Enter Service Name:");
        sn = input.nextLine();

        System.out.println("Enter Username:");
        un = input.nextLine();

        sc = new Scanner(System.in); // Clearing Input Buffer

        int flag = 0;

        if(PasswordHashmap.containsKey(sn + "<->" + un)) {
            System.out.println("Data Found");
            System.out.println("[1] To change password manually");
            System.out.println("[2] To change generate new password");
            String pass = "";
            short choice;
            do {
                System.out.print("Choice: ");
                choice = input.nextShort();
                switch (choice) {
                    case 1:
                        System.out.println();
                        input = new Scanner(System.in); // refresh scanner to avoid errors
                        System.out.print("Enter Password: ");
                        pass = input.nextLine();
                        PasswordHashmap.replace(sn + "<->" + un, pass);
                        System.out.println();
                        break;
                    case 2:
                        int l;
                        do {
                            System.out.println();
                            input = new Scanner(System.in); // refresh scanner to avoid errors
                            System.out.println("Enter Length of you Password");
                            l = input.nextInt();
                            if (l < 8) {
                                System.out.println("Minimum Password length = 8");
                            }
                        } while (l < 8);
                        pass = passwordGenerator.generatePassword(l);
                        PasswordHashmap.replace(sn + "<->" + un, pass);
                        break;
                    default:
                        System.out.println("ERROR: Choice not valid");
                }
            } while (choice < 1 || choice > 2);
            flag = 1;
        }

        if (flag == 0) {
            System.out.println("Data does not exist!");
        } else {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter("passwordDB.txt");
                writer.print("");
                writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                e.printStackTrace();
            }

            String[] keyArr;
            for (Map.Entry<String, String> set : PasswordHashmap.entrySet()) {
                // split key
                keyArr = set.getKey().split("<->");
                String data = keyArr[0] + "<->" + keyArr[1] + "<->" + set.getValue() + "\n";

                File file = new File("passwordDB.txt");
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(file, true);
                    fileWriter.write(data);
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Error");
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // update from BST
    public void updatePasswordBST() {
        Scanner input = new Scanner(System.in);
        String sn, un;

        System.out.println("Enter Service Name:");
        sn = input.nextLine();

        System.out.println("Enter Username:");
        un = input.nextLine();

        sc = new Scanner(System.in); // Clearing Input Buffer

        int flag = 0;

        if(PasswordBST.search(sn + "<->" + un) != null) {
            System.out.println("Data Found");
            System.out.println("[1] To change password manually");
            System.out.println("[2] To change generate new password");
            String pass = "";
            short choice;
            do {
                System.out.print("Choice: ");
                choice = input.nextShort();
                switch (choice) {
                    case 1:
                        System.out.println();
                        input = new Scanner(System.in); // refresh scanner to avoid errors
                        System.out.print("Enter Password: ");
                        pass = input.nextLine();
                        PasswordBST.insert(sn + "<->" + un, pass);
                        System.out.println();
                        break;
                    case 2:
                        int l;
                        do {
                            System.out.println();
                            input = new Scanner(System.in); // refresh scanner to avoid errors
                            System.out.println("Enter Length of you Password");
                            l = input.nextInt();
                            if (l < 8) {
                                System.out.println("Minimum Password length = 8");
                            }
                        } while (l < 8);
                        pass = passwordGenerator.generatePassword(l);
                        PasswordBST.insert(sn + "<->" + un, pass);
                        break;
                    default:
                        System.out.println("ERROR: Choice not valid");
                }
            } while (choice < 1 || choice > 2);
            flag = 1;
        }

        if (flag == 0) {
            System.out.println("Data does not exist!");
        } else {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter("passwordDB.txt");
                writer.print("");
                writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                e.printStackTrace();
            }

            // save to textFile
            PasswordBST.saveTree();
        }
    }

    // delete from doubly linked list
    public void deletePassword() {
        System.out.println("==> DELETE <==");

        Scanner input = new Scanner(System.in);
        String ServiceName, UserName;

        System.out.println("Enter Service Name:");
        ServiceName = input.nextLine();

        System.out.println("Enter Username:");
        UserName = input.nextLine();

        if (first == null) {
            System.out.println("The list is empty");
            return;
        }

        if (ServiceName.equals(first.serviceName) && UserName.equals(first.userName)) {
            first = first.next;
        } else if (ServiceName.equals(last.serviceName) && UserName.equals(last.userName)) {
            last.previous.next = null;
            last = last.previous;
        } else {
            NodePassword current = first.next;
            while (current != null) {
                if (ServiceName.equals(current.serviceName) && UserName.equals(current.userName)) {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                    break;
                }
                current = current.next;
            }
        }

        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("passwordDB.txt");
            printWriter.print("");
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        NodePassword current = first;
        while (current != null) {
            String data = current.serviceName + "<->" + current.userName + "<->" + current.password + "\n";

            File file = new File("passwordDB.txt");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file, true);
                fileWriter.write(data);
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error");
                throw new RuntimeException(e);
            }
            current = current.next;
        }
        System.out.println("Password successfully deleted!");
    }

    // delete from array list
    public void deletePasswordArraylist() {
        System.out.println("==> DELETE <==");

        Scanner input = new Scanner(System.in);
        String ServiceName, UserName;

        System.out.println("Enter Service Name:");
        ServiceName = input.nextLine();

        System.out.println("Enter Username:");
        UserName = input.nextLine();

        if (PasswordArrayList.size() < 0 || PasswordArrayList.size() == 0) {
            System.out.println("The list is empty");
            return;
        }

        // delete
        for(int i = 0; i < PasswordArrayList.size(); i++) {
            if (ServiceName.equals(PasswordArrayList.get(i).getServiceName()) && UserName.equals((PasswordArrayList.get(i).getUserName()))) {
                PasswordArrayList.remove(i);
                break;
            }
        }

        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("passwordDB.txt");
            printWriter.print("");
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        for(int i = 0; i < PasswordArrayList.size(); i++) {
            String data = PasswordArrayList.get(i).getServiceName() + "<->" + PasswordArrayList.get(i).getUserName() + "<->" + PasswordArrayList.get(i).getPassword() + "\n";

            File file = new File("passwordDB.txt");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file, true);
                fileWriter.write(data);
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error");
                throw new RuntimeException(e);
            }
        }
        System.out.println("Password successfully deleted!");
    }

    // delete from hashmap
    public void deletePasswordHashmap() {
        System.out.println("==> DELETE <==");

        Scanner input = new Scanner(System.in);
        String ServiceName, UserName;

        System.out.println("Enter Service Name:");
        ServiceName = input.nextLine();

        System.out.println("Enter Username:");
        UserName = input.nextLine();

        if (PasswordHashmap.size() < 0 || PasswordHashmap.size() == 0) {
            System.out.println("The list is empty");
            return;
        }

        // delete
        if(PasswordHashmap.containsKey(ServiceName + "<->" + UserName)) {
            PasswordHashmap.remove(ServiceName + "<->" + UserName);
        }

        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("passwordDB.txt");
            printWriter.print("");
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        String[] keyArr;
        for (Map.Entry<String, String> set : PasswordHashmap.entrySet()) {
            // split key
            keyArr = set.getKey().split("<->");
            String data = keyArr[0] + "<->" + keyArr[1] + "<->" + set.getValue() + "\n";

            File file = new File("passwordDB.txt");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file, true);
                fileWriter.write(data);
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error");
                throw new RuntimeException(e);
            }
        }
        System.out.println("Password successfully deleted!");
    }

    // delete from BST
    public void deletePasswordBST() {
        System.out.println("==> DELETE <==");

        Scanner input = new Scanner(System.in);
        String ServiceName, UserName;

        System.out.println("Enter Service Name:");
        ServiceName = input.nextLine();

        System.out.println("Enter Username:");
        UserName = input.nextLine();

        if (PasswordBST.getRoot() == null) {
            System.out.println("The list is empty");
            return;
        }

        // delete
        if(PasswordBST.search(ServiceName + "<->" + UserName) != null) {
            PasswordBST.delete(ServiceName + "<->" + UserName);
        }

        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("passwordDB.txt");
            printWriter.print("");
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        // save to textFile
        PasswordBST.saveTree();

        System.out.println("Password successfully deleted!");
    }

    // display passwords from the doubly linked list
    public void displayList() {

        System.out.printf("| %-8s | %-8s | %-8s |\n", "Service", "Username", "Password");
        System.out.printf("----------------------------------\n");
        NodePassword current = first;
        while (current != null) {
            current.displayNode();
            current = current.next;
        }



    }

    // display passwords from the array list
    public void displayFromArraylist() {

        for(int i = 0; i < PasswordArrayList.size(); i++) {
            System.out.printf("| %-8s | %-8s | %-8s |\n", PasswordArrayList.get(i).getServiceName(), PasswordArrayList.get(i).getUserName(), PasswordArrayList.get(i).getPassword());

        }


    }

    // display passwords from the hashmap
    public void displayFromHashmap() {

        String[] keyArr;
        for (Map.Entry<String, String> set : PasswordHashmap.entrySet()) {
            // split key
            keyArr = set.getKey().split("<->");

            // Printing all elements of a Map
            System.out.printf("| %-8s | %-8s | %-8s |\n", keyArr[0], keyArr[1], set.getValue());

        }


    }

    // display passwords from BST
    public void displayFromBST() {


        PasswordBST.display();


    }

    private void displayTree(BSTNodePassword root) {
        if (root != null) {
            displayTree(root.left);
            System.out.println("Key: " + root.key + ", Value: " + root.value);
            displayTree(root.right);
        }
    }

    // get passwords from text-file and add them to 3 containers with different data structures
    public void getAllPasswords() {
        File file = new File("passwordDB.txt");
        try {
            Scanner reader = new Scanner(file);
            String line;
            String[] stringArray;

            PasswordArrayList.clear();
            PasswordHashmap.clear();

            while (reader.hasNext()) {
                line = reader.nextLine();
                stringArray = line.split("<->");

                // add passwords to doubly linked list data structure
                NodePassword newNode = new NodePassword();
                newNode.serviceName = stringArray[0];
                newNode.userName = stringArray[1];
                newNode.password = stringArray[2];
                if (first == null) {
                    first = newNode;
                } else {
                    last.next = newNode;
                    newNode.previous = last;
                }
                this.last = newNode;

                // add passwords to array list data structure
                PasswordObj passObj = new PasswordObj(stringArray[0], stringArray[1], stringArray[2]);
                PasswordArrayList.add(passObj);

                // add passwords to hashmap data structure
                PasswordHashmap.put(stringArray[0] + "<->" + stringArray[1], stringArray[2]);

                // add passwords to bst data structure
                PasswordBST.insert(stringArray[0] + "<->" + stringArray[1], stringArray[2]);
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }
    public void compareDataType(){
        long timeListStart=System.nanoTime();
        displayList();
        long timeListExce=(System.nanoTime()-timeListStart)/1000000;
        long timeArrayStart=System.nanoTime();
        displayFromArraylist();
        long timeArrayExce=(System.nanoTime()-timeArrayStart)/1000000;
        long timeHashmapStart=System.nanoTime();
        displayFromHashmap();
        long timeHashmapExce=(System.nanoTime()-timeHashmapStart)/1000000;
        long timeBSTStart=System.nanoTime();
        displayFromBST();
        long timeBSTExce=(System.nanoTime()-timeBSTStart)/1000000;

        System.out.println("Double Linked List View: "+ timeListExce);
        System.out.println("Array List View: "+timeArrayExce);
        System.out.println("Hashmap View:  "+ timeHashmapExce);
        System.out.println("BST View:  "+ timeBSTExce);

    }
    public void compareDelete(){
        //Input Data
        System.out.println("==> DELETE <==");

        Scanner input = new Scanner(System.in);
        String ServiceName, UserName;

        System.out.println("Enter Service Name:");
        ServiceName = input.nextLine();

        System.out.println("Enter Username:");
        UserName = input.nextLine();


        //Double Linked List
        long searchDoubleLinkedTimeStart=System.nanoTime();
        if (first == null) {
            System.out.println("The list is empty");
            return;
        }

        if (ServiceName.equals(first.serviceName) && UserName.equals(first.userName)) {
            first = first.next;
        } else if (ServiceName.equals(last.serviceName) && UserName.equals(last.userName)) {
            last.previous.next = null;
            last = last.previous;
        } else {
            NodePassword current = first.next;
            while (current != null) {
                if (ServiceName.equals(current.serviceName) && UserName.equals(current.userName)) {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                    break;
                }
                current = current.next;
            }
        }
        long searchDoubleLinkedDuration=System.nanoTime()-searchDoubleLinkedTimeStart;


        long deleteDoubleLinkedStart=System.nanoTime();
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("passwordDB.txt");
            printWriter.print("");
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        NodePassword current = first;
        while (current != null) {
            String data = current.serviceName + "<->" + current.userName + "<->" + current.password + "\n";

            File file = new File("passwordDB.txt");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file, true);
                fileWriter.write(data);
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error");
                throw new RuntimeException(e);
            }
            current = current.next;
        }
        System.out.println("Password successfully deleted!");
        long deleteDoubleLinkedDuration=(System.nanoTime()-deleteDoubleLinkedStart)/1000000;


        //ArrayList
//Searching
        long searchArrayListTimeStart=System.nanoTime();
        if (PasswordArrayList.size() < 0 || PasswordArrayList.size() == 0) {
            System.out.println("The list is empty");
            return;
        }

        if (PasswordArrayList.size() < 0 || PasswordArrayList.size() == 0) {
            System.out.println("The list is empty");
            return;
        }
        long searchArrayListDuration=System.nanoTime()-searchArrayListTimeStart;
        // delete
        long deletionArrayStart=System.nanoTime();
        for(int i = 0; i < PasswordArrayList.size(); i++) {
            if (ServiceName.equals(PasswordArrayList.get(i).getServiceName()) && UserName.equals((PasswordArrayList.get(i).getUserName()))) {
                PasswordArrayList.remove(i);
                break;
            }
        }

        printWriter = null;
        try {
            printWriter = new PrintWriter("passwordDB.txt");
            printWriter.print("");
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        for(int i = 0; i < PasswordArrayList.size(); i++) {
            String data = PasswordArrayList.get(i).getServiceName() + "<->" + PasswordArrayList.get(i).getUserName() + "<->" + PasswordArrayList.get(i).getPassword() + "\n";

            File file = new File("passwordDB.txt");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file, true);
                fileWriter.write(data);
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error");
                throw new RuntimeException(e);
            }
        }
        System.out.println("Password successfully deleted!");
        long deletionArrayDuration=(System.nanoTime()-deletionArrayStart)/1000000;
//HashMap
        //search
        long searchHashMapStart=System.nanoTime();
        if (PasswordHashmap.size() < 0 || PasswordHashmap.size() == 0) {
            System.out.println("The list is empty");
            return;
        }
        long searchHashMapDuration=System.nanoTime()-searchHashMapStart;
        // delete
        long deletionHashMapStart=System.nanoTime();
        if(PasswordHashmap.containsKey(ServiceName + "<->" + UserName)) {
            PasswordHashmap.remove(ServiceName + "<->" + UserName);
        }

       printWriter = null;
        try {
            printWriter = new PrintWriter("passwordDB.txt");
            printWriter.print("");
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        String[] keyArr;
        for (Map.Entry<String, String> set : PasswordHashmap.entrySet()) {
            // split key
            keyArr = set.getKey().split("<->");
            String data = keyArr[0] + "<->" + keyArr[1] + "<->" + set.getValue() + "\n";

            File file = new File("passwordDB.txt");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file, true);
                fileWriter.write(data);
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error");
                throw new RuntimeException(e);
            }
        }
        System.out.println("Password successfully deleted!");
        long deletionHashMapDuration=(System.nanoTime()-deletionHashMapStart)/1000000;


        //BST
        long searchBSTStart=System.nanoTime();
        if (PasswordBST.getRoot() == null) {
            System.out.println("The list is empty");
            return;
        }
        long searchBSTDuration=System.nanoTime()-searchBSTStart;
        // delete
        long deletionBSTStart=System.nanoTime();
        if(PasswordBST.search(ServiceName + "<->" + UserName) != null) {
            PasswordBST.delete(ServiceName + "<->" + UserName);
        }

        printWriter = null;
        try {
            printWriter = new PrintWriter("passwordDB.txt");
            printWriter.print("");
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        // save to textFile
        PasswordBST.saveTree();

        System.out.println("Password successfully deleted!");
        long deletionBSTDuration=(System.nanoTime()-deletionBSTStart)/1000000;

        System.out.println("===========Search Time Comparing (in unit of nanosecond) ==========");
        System.out.println("Double Linked List:  "+ searchDoubleLinkedDuration);
        System.out.println("ArrayList:  "+ searchArrayListDuration);
        System.out.println("HashMap:  "+searchHashMapDuration);
        System.out.println("BST:  "+searchBSTDuration);
        System.out.println("======Deletion time comparing (in unit of milisecond)=====");
        System.out.println(" Double Linked List:   "+deleteDoubleLinkedDuration);
        System.out.println(" Array List:  "+deletionArrayDuration);
        System.out.println("HashMap:  "+ deletionHashMapDuration);
        System.out.println("BST:  "+deletionBSTDuration);



    }
    public void compareUpdate(){
        Scanner input = new Scanner(System.in);
        String sn, un;

        System.out.println("Enter Service Name:");
        sn = input.nextLine();

        System.out.println("Enter Username:");
        un = input.nextLine();

        sc = new Scanner(System.in); // Clearing Input Buffer
//Searching and Update
        long doubleLinkedUpdateStart=System.nanoTime();
        int flag = 0;
        NodePassword current = first;

        while (current != null && flag == 0) {
            if (sn.equals(current.serviceName) && un.equals((current.userName))) {
                System.out.println("Data Found");
                String pass = "";
                int l=16;
                pass = passwordGenerator.generatePassword(l);
                current.password = pass;

                flag=1;
            }
            current = current.next;
        }

        if (flag == 0) {
            System.out.println("Data does not exist!");
        } else {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter("passwordDB.txt");
                writer.print("");
                writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                e.printStackTrace();
            }

            NodePassword c = first;
            while (c != null) {
                String data = c.serviceName + "<->" + c.userName + "<->" + c.password + "\n";

                File file = new File("passwordDB.txt");
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(file, true);
                    fileWriter.write(data);
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Error");
                    throw new RuntimeException(e);
                }
                c = c.next;
            }
        }
        long doubleLinkedUpdateDuration=(System.nanoTime()-doubleLinkedUpdateStart)/1000000;
        //Array List
        sc = new Scanner(System.in); // Clearing Input Buffer
        long arrayListUpdateStart=System.nanoTime();
        flag = 0;

        for(int i = 0; i < PasswordArrayList.size(); i++) {
            if (sn.equals(PasswordArrayList.get(i).getServiceName()) && un.equals((PasswordArrayList.get(i).getUserName()))) {
                PasswordObj newObj = PasswordArrayList.get(i);
                System.out.println("Data Found");
               int k=16;
                String pass = "";
                pass = passwordGenerator.generatePassword(k);
                newObj.setPassword(pass);
                PasswordArrayList.set(i, newObj) ;
                flag = 1;

            }
        }

        if (flag == 0) {
            System.out.println("Data does not exist!");
        } else {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter("passwordDB.txt");
                writer.print("");
                writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                e.printStackTrace();
            }

            for(int i = 0; i < PasswordArrayList.size(); i++) {
                String data = PasswordArrayList.get(i).getServiceName() + "<->" + PasswordArrayList.get(i).getUserName() + "<->" + PasswordArrayList.get(i).getPassword() + "\n";

                File file = new File("passwordDB.txt");
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(file, true);
                    fileWriter.write(data);
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Error");
                    throw new RuntimeException(e);
                }
            }
        }
        long arrayListUpdateDuration=(System.nanoTime()-arrayListUpdateStart)/1000000;

//HashMap
        sc = new Scanner(System.in); // Clearing Input Buffer
        long hashMapUpdateStart=System.nanoTime();
        flag = 0;

        if(PasswordHashmap.containsKey(sn + "<->" + un)) {
            System.out.println("Data Found");
            int m=16;
            String pass = "";
            pass = passwordGenerator.generatePassword(m);
            PasswordHashmap.replace(sn + "<->" + un, pass);
            flag = 1;
        }

        if (flag == 0) {
            System.out.println("Data does not exist!");
        } else {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter("passwordDB.txt");
                writer.print("");
                writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                e.printStackTrace();
            }

            String[] keyArr;
            for (Map.Entry<String, String> set : PasswordHashmap.entrySet()) {
                // split key
                keyArr = set.getKey().split("<->");
                String data = keyArr[0] + "<->" + keyArr[1] + "<->" + set.getValue() + "\n";

                File file = new File("passwordDB.txt");
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(file, true);
                    fileWriter.write(data);
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Error");
                    throw new RuntimeException(e);
                }
            }
        }
        long hashMapUpdateDuration=(System.nanoTime()-hashMapUpdateStart)/1000000;
///BST
        sc = new Scanner(System.in); // Clearing Input Buffer
        long bSTUpdateStart=System.nanoTime();
        flag = 0;

        if(PasswordBST.search(sn + "<->" + un) != null) {
            System.out.println("Data Found");
            int n=16;

            String pass = "";
            pass = passwordGenerator.generatePassword(n);
            PasswordBST.insert(sn + "<->" + un, pass);
            flag = 1;
        }

        if (flag == 0) {
            System.out.println("Data does not exist!");
        } else {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter("passwordDB.txt");
                writer.print("");
                writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                e.printStackTrace();
            }

            // save to textFile
            PasswordBST.saveTree();
        }
        long bSTUpdateDuration=(System.nanoTime()-bSTUpdateStart)/1000000;


        System.out.println("==========Compare Update Data type (in unit of milisecond)=======");
        System.out.println("Double Linked List:   "+doubleLinkedUpdateDuration);
        System.out.println("Array List:  "+ arrayListUpdateDuration);
        System.out.println("HashMap:   "+ hashMapUpdateDuration);
        System.out.println("BST:   "+bSTUpdateDuration);





    }
    
}