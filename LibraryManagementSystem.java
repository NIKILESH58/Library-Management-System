import java.util.*;

public class LibraryManagementSystem {
    static int userCounter = 1, bookCounter = 1, borrowCounter = 1;
    static String[] userNames = new String[1000];
    static String[] bookTitles = new String[1000];
    static String[] bookAuthors = new String[1000];
    static boolean[] bookAvailability = new boolean[1000];
    static int[] borrowedByUser = new int[1000];
    static String[] borrowDates = new String[1000];
    static String[] returnDates = new String[1000];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String choice;
        do {
            System.out.println("WELCOME TO THE LIBRARY MANAGEMENT SYSTEM");
            System.out.println("1. NEW USER");
            System.out.println("2. BORROW BOOK");
            System.out.println("3. RETURN BOOK");
            System.out.println("4. VIEW BORROWED BOOKS");
            System.out.println("5. ADMIN");
            System.out.println("0. EXIT");
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    registerNewUser(in);
                    break;
                case "2":
                    borrowBook(in);
                    break;
                case "3":
                    returnBook(in);
                    break;
                case "4":
                    viewBorrowedBooks(in);
                    break;
                case "5":
                    adminSection(in);
                    break;
                case "0":
                    System.out.println("EXIT");
                    break;
                default:
                    System.out.println("INVALID CHOICE");
                    break;
            }
        } while (!choice.equals("0"));
    }

    private static void registerNewUser(Scanner in) {
        System.out.println("ENTER YOUR NAME:");
        userNames[userCounter] = in.nextLine();
        System.out.println("YOUR USER ID IS: " + userCounter);
        userCounter++;
    }

    private static void borrowBook(Scanner in) {
        System.out.println("ENTER YOUR USER ID:");
        int userId = in.nextInt();
        in.nextLine();
        if (userId < userCounter) {
            System.out.println("AVAILABLE BOOKS:");
            for (int i = 1; i < bookCounter; i++) {
                if (bookAvailability[i]) {
                    System.out.println(i + ". " + bookTitles[i] + " by " + bookAuthors[i]);
                }
            }
            System.out.println("SELECT BOOK NUMBER:");
            int bookIndex = in.nextInt();
            in.nextLine();
            if (bookIndex < bookCounter && bookAvailability[bookIndex]) {
                System.out.println("ENTER BORROW DATE (YYYY-MM-DD):");
                String borrowDate = in.nextLine();
                bookAvailability[bookIndex] = false;
                borrowedByUser[borrowCounter] = userId;
                borrowDates[borrowCounter] = borrowDate;
                returnDates[borrowCounter] = "";
                System.out.println("BOOK BORROWED SUCCESSFULLY!");
                borrowCounter++;
            } else {
                System.out.println("INVALID BOOK ID OR BOOK NOT AVAILABLE");
            }
        } else {
            System.out.println("INVALID USER ID");
        }
    }

     private static void returnBook(Scanner in) {
    System.out.println("ENTER YOUR USER ID:");
    int userId = in.nextInt();
    in.nextLine();
    if (userId < userCounter) {
        System.out.println("BORROWED BOOKS:");
        for (int i = 1; i < borrowCounter; i++) {
            if (borrowedByUser[i] == userId && returnDates[i].isEmpty()) {
                System.out.println(i + ". " + bookTitles[i] + " by " + bookAuthors[i] + " borrowed on " + borrowDates[i]);
            }
        }
        System.out.println("SELECT BORROWED BOOK NUMBER TO RETURN:");
        int borrowIndex = in.nextInt();
        in.nextLine();
        if (borrowIndex < borrowCounter && borrowedByUser[borrowIndex] == userId && returnDates[borrowIndex].isEmpty()) {
            System.out.println("ENTER RETURN DATE (YYYY-MM-DD):");
            String returnDate = in.nextLine();
            returnDates[borrowIndex] = returnDate;
            int bookIndex = borrowIndex;
            bookAvailability[borrowIndex] = true; 
            System.out.println("BOOK RETURNED SUCCESSFULLY!");
        } else {
            System.out.println("INVALID BORROWED BOOK ID");
        }
    } else {
        System.out.println("INVALID USER ID");
    }
     }
    private static void viewBorrowedBooks(Scanner in) {
        System.out.println("ENTER YOUR USER ID:");
        int userId = in.nextInt();
        in.nextLine();
        if (userId < userCounter) {
            System.out.println("BORROWED BOOKS FOR USER: " + userNames[userId]);
            boolean borrowFound = false;
            for (int i = 1; i < borrowCounter; i++) {
                if (borrowedByUser[i] == userId) {
                    System.out.println("Borrow ID: " + i +
                            ", Book: " + bookTitles[i] +
                            ", Author: " + bookAuthors[i] +
                            ", Borrow Date: " + borrowDates[i] +
                            ", Return Date: " + (returnDates[i].isEmpty() ? "Not Returned" : returnDates[i]));
                    borrowFound = true;
                }
            }
            if (!borrowFound) {
                System.out.println("NO BORROWED BOOKS FOUND");
            }
        } else {
            System.out.println("INVALID USER ID");
        }
    }
    private static void adminSection(Scanner in) {
        String choice;
        do {
            System.out.println("ADMIN SECTION");
            System.out.println("1. ADD NEW BOOK");
            System.out.println("2. VIEW ALL BOOKS");
            System.out.println("0. EXIT");
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    addNewBook(in);
                    break;
                case "2":
                    viewAllBooks();
                    break;
                case "0":
                    System.out.println("EXIT");
                    break;
                default:
                    System.out.println("INVALID CHOICE");
                    break;
            }
        } while (!choice.equals("0"));
    }
    private static void addNewBook(Scanner in) {
        System.out.println("ENTER BOOK TITLE:");
        bookTitles[bookCounter] = in.nextLine();
        System.out.println("ENTER BOOK AUTHOR:");
        bookAuthors[bookCounter] = in.nextLine();
        bookAvailability[bookCounter] = true;
        System.out.println("BOOK ADDED SUCCESSFULLY!");
        bookCounter++;
    }
    private static void viewAllBooks() {
        System.out.println("ALL BOOKS:");
        for (int i = 1; i < bookCounter; i++) {
            System.out.println(i + ". " + bookTitles[i] + " by " + bookAuthors[i] + " - " + (bookAvailability[i] ? "Available" : "Not Available"));
        }
    }
}
