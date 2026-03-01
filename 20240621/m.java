
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class m {
    static String password="123456";
static ArrayList<String> books = new ArrayList<>(Arrays.asList("java", "c++"));
static ArrayList<String> borrowbooks = new ArrayList<>();
static int borrowprice=3;
static int buyprice=10;
static int total=0;
static int numofborrow=0;
static int numofbuy=0;
    static Scanner input = new Scanner(System.in);
   
        public static void add() {
    System.out.println("Enter name of the new book:");
    String book = input.next();
    if(books.contains(book)){
        System.out.println("Book already exists "); 
    } else {
        books.add(book);
        System.out.println("Book added successfully "); 
    }
}
        
        public static void remove() {
    System.out.println("Enter the name of the book to remove:");
    String book = input.next();
    if(books.contains(book)){
        books.remove(book);
        System.out.println("Book removed successfully "); 
    } else {
        System.out.println("Book not found"); 
    }
}
          public static void display() {
    System.out.println("Available Books:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println( books.get(i));
        }
}
public static void displayBorrowed() {
    System.out.println("Borrowed Books:");
        for (int i = 0; i < borrowbooks.size(); i++) {
            System.out.println( borrowbooks.get(i));
        }
    }
public static void member() {
    int q;
    do { 
        System.out.println("\n=== Member Menu ===");
        System.out.println("1 - Borrow a book");
        System.out.println("2 - Return a book");
        System.out.println("3 - Buy a book");
        System.out.println("0 - Exit");
        
        q = input.nextInt();
        
        if(q == 1)
            borrow();
        else if(q == 2)
            returns();
        else if(q == 3)
            buy(); 
        else if(q != 0)
            System.out.println("Invalid choice! Please select 0, 1, 2, 3");
        
    } while (q != 0);
    
    System.out.println("Exiting Member menu");
}
public static void borrow() {
    if(books.isEmpty()){
        System.out.println("The library is empty, you cannot borrow any book.");
        return;
    }
    display();
    System.out.println("Enter book to borrow:");
    String book=input.next();
    if(books.contains(book)){
System.out.print("Book is found");
numofborrow++;
total+=borrowprice;
        books.remove(book);
        borrowbooks.add(book);
    }
    else
    System.out.println("Book not avaliable");
}
public static void returns() {
    if(borrowbooks.isEmpty()){
        System.out.println("The library is empty, you cannot return any book.");
        return;
    }
    displayBorrowed();
    System.out.println("Enter book to return:");
    String book = input.next(); 

    if(borrowbooks.contains(book)){
        System.out.println("Book returned successfully "); 
        borrowbooks.remove(book);
        books.add(book);
    } else {
        System.out.println("This book was not borrowed "); 
    }
}
public static void buy() {
     if(books.isEmpty()){
        System.out.println("The library is empty, you cannot buy any book.");
        return;
    }
    display();
    System.out.println("Enter book to buy:");
    String book = input.next(); 
    if(books.contains(book)){
        System.out.println("Book bought successfully ");
        numofbuy++;
        total +=buyprice;
        books.remove(book);
    } else {
        System.out.println("Book not available "); 
    }
}
public static void stat() {
    System.out.println("=== Library Statistics ===");
    System.out.println("Number of books bought: " + numofbuy);
    System.out.println("Number of books borrowed: " + numofborrow);
    System.out.println("Total money earned: $" + total);
}
 public static void main(String[] args) {
    int role;
    do {
        System.out.println("Choose role:");
        System.out.println("1 - Librarian");
        System.out.println("2 - Member");
        System.out.println("0 - Exit");
        
        role = input.nextInt();
        
        switch (role) {
            case 1 -> librarian();
            case 2 -> member();
            case 0 -> System.out.println("Good bye");
            default -> System.out.println("Invalid choice Please choose 1, 2, or 0.");
        }
        
    } while(role != 0); 
    }
      public static void librarian() {
    System.out.println("Enter password:");
    String pass = input.next();

    if( pass.equals(password)){
        int q;
        do { 
            System.out.println("\nLibrarian Menu:");
            System.out.println("1 - Add book");
            System.out.println("2 - Remove book");
            System.out.println("3 - Show stats");
            System.out.println("0 - Exit");
            
            q = input.nextInt();
            
            switch (q) {
                case 1 -> add();
                case 2 -> remove();
                case 3 -> stat();
                case 0 -> System.out.println("Exiting Librarian menu");
                default -> System.out.println("Invalid choice! Please select 0, 1, or 2..");
            }

        } while (q != 0);
    } else {
        System.out.println("Incorrect username or password ");
    }
}
}
