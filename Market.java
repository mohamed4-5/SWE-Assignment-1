import java.util.Scanner;
import java.util.ArrayList;

class Product {
    int id, quantity;
    String name;
    double price;

    Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

class Cart {
    int id;
    String name;
    double price;
    int quantity;

    Cart() {}

    Cart(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

public class Market {

    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<Cart> cart = new ArrayList<>();
    static boolean menu;

    static void customerMenu(Scanner input) {
        menu = false;
        System.out.println("\n \t --- Customer Menu --- ");
        int choice;
        do {
            System.out.println("\n1- Available Products \n2- Buy a Product \n3- Cart \n0- Exit");
            choice = input.nextInt();
            if (choice == 1) {
                availableProducts(products,menu,input);
            } else if (choice == 2) {
                
                buyProduct(products, input, cart);
            } else if (choice == 3){
                showCart(cart, input);
            } else if (choice == 0) {
                System.out.println("Exiting ...");
            } else {
                System.out.println("Wrong choice");
            }
        } while (choice != 0);
    }

    static void employeeMenu(Scanner input) {
        menu = true;
        System.out.println("\n \t --- Employee Menu --- ");
        int choice;
        do {
            System.out.println("\n1- Add Product \n2- Edit Products \n3- Available Products \n0- Exit");
            choice = input.nextInt();
            if (choice == 1) {
                addProduct(products, input);
            } else if(choice == 2){
                System.out.println("1- Edit Name \n2- Edit ID \n3- Edit Price \n4- Edit Quantity");
                int choice2 = input.nextInt();
                if(choice2==1){
                    editName(products, input);
                }
                else if(choice2==2){
                    editID(products, input);
                }
                else if(choice2==3){
                    editPrice(products, input);
                }
                else if(choice2==4){
                    editQuantity(products, input);
                }
                else {
                    System.out.println("Invalid Choice");
                }
            } else if (choice == 3) {
                availableProducts(products, menu, input);
            } else if (choice == 0) {
                System.out.println("Exiting ...");
            } else {
                System.out.println("Wrong choice");
            }
        } while (choice != 0);
    }

    static void availableProducts(ArrayList<Product> products,boolean menu,Scanner input) {
        System.out.println("\n \t --- Available Products --- \n");
        int choice = 1;
        do{

            for (Product p : products) {
                System.out.println(
                        "ID: " + p.id +
                        " | Name: " + p.name +
                        " | Price: $" + p.price +
                        " | Quantity: " + p.quantity
                );
            }
            System.out.println("\n1- Continue");
            System.out.println("0- Exit");
            choice = input.nextInt();
            if(choice==0){
                return;
            }

        } while(choice != 0);

    }

    static void addProduct(ArrayList<Product> products, Scanner input) {
        int choice = 1;
        do{
            System.out.println("Enter product name :");
            String name = input.next();
            System.out.println("Enter product ID :");
            int id = input.nextInt();
            boolean found = false;
            for (Product p : products) {
                if (id == p.id) {
                    System.out.println("This ID already exists!");
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                System.out.println("Enter product price :");
                double price = input.nextDouble(); 
                System.out.println("Enter product Quantity :");
                int quantity = input.nextInt();

                products.add(new Product(id, name, price, quantity));
                System.out.println("Added successfully");
            }

            System.out.println("\n1- Continue");
            System.out.println("0- Exit");
            choice = input.nextInt();
            if(choice==0){
                return;
            }
        } while(choice!=0);
    }

    
    static void buyProduct(ArrayList<Product> products, Scanner input, ArrayList<Cart> cartList) {
        System.out.println("\n \t ---Buy by ID--- \n");
        int choice = 1;
        do{

            System.out.println("Enter id and quantity needed : ");
            for (Product p : products) {
                System.out.println(p.id + "- " + p.name + " $" + p.price + " (Available: " + p.quantity + ")");
            }
            
            int id = input.nextInt();
            int quantity = input.nextInt();
            boolean found = false;
            
            for (Product p : products) {
                if (id == p.id) {
                    found = true;
                    
                    if (quantity <= p.quantity) { 
                        p.quantity -= quantity;
                        double totalPrice = p.price * quantity;
                        
                        cartList.add(new Cart(p.id, p.name, totalPrice, quantity));
                        System.out.println("Added to cart successfully. Total Price: " + totalPrice);
                    } else {
                        System.out.println("There is not enough quantity. Only " + p.quantity + " available.");
                    }
                    break;
                }
            }
            if (!found) {
                System.out.println("Invalid ID");
            }

            System.out.println("\n1- Continue");
            System.out.println("0- Exit");
            choice = input.nextInt();
            if(choice==0){
                return;
            }

        } while(choice!=0);
    }

    static void editPrice(ArrayList<Product> products, Scanner input) {
        System.out.println("\n \t ---Edit by ID--- \n");
        int choice;
        do{
            
            System.out.println("Enter id and new price");
            for (Product p : products) {
                System.out.println(p.id + "- " + p.name + " | Current price is " + p.price);
            }
            int id = input.nextInt();
            double price = input.nextDouble();
            boolean found = false;
            for (Product p : products) {
                if (p.id == id) {
                    p.price = price;
                    System.out.println("Price changed successfully");
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Invalid ID ");
            }

            System.out.println("\n1- Continue");
            System.out.println("0- Exit");
            choice = input.nextInt();
            if(choice==0){
                return;
            }

        }while(choice != 0);
    }

    static void editQuantity(ArrayList<Product> products, Scanner input) {
        System.out.println("\n \t ---Edit by ID--- \n");
        int choice;
        do{
            
            System.out.println("Enter id and quantity to add : ");
            for (Product p : products) {
                System.out.println(p.id + "- " + p.name + " | Current Quantity is " + p.quantity);
            }
            int id = input.nextInt();
            int quantity = input.nextInt();
            boolean found = false;
            for (Product p : products) {
                if (p.id == id) {
                    p.quantity += quantity ;
                    System.out.println("Quantity changed successfully");
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Invalid ID ");
            }

            System.out.println("\n1- Continue");
            System.out.println("0- Exit");
            choice = input.nextInt();
            if(choice==0){
                return;
            }
        }while (choice != 0);
    }
    
    static void editName(ArrayList<Product> products, Scanner input) {
        
        System.out.println("\n \t ---Edit by ID--- \n");
        int choice;
        do{
            
            System.out.println("Enter id : ");
            for (Product p : products) {
                System.out.println(p.id + "- " + p.name );
            }
            int id = input.nextInt();
            boolean found = false;
            for (Product p : products) {
                if (p.id == id) {
                    
                    System.out.println("Enter the new name : ");
                    String newName = input.next();
                    p.name=newName;
                    System.out.println("Name changed successfully");
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Invalid ID ");
            }

            System.out.println("\n1- Continue");
            System.out.println("0- Exit");
            choice = input.nextInt();
            if(choice==0){
                return;
            }
        }while (choice != 0);
    }

    static void editID(ArrayList<Product> products, Scanner input) {
        System.out.println("\n \t ---Edit by ID--- \n");
        int choice;
        do{
            
            System.out.println("Enter ID : ");
            for (Product p : products) {
                System.out.println(p.id + "- " + p.name );
            }
            int id = input.nextInt();
            boolean found = false;
            for (Product p : products) {
                if (p.id == id) {
                    boolean changed =false;
                    do{
                        boolean found2= false;
                        System.out.println("Enter new ID : ");
                        int newID = input.nextInt();
                        for (Product p2 : products) {
                            if(p2.id==newID)
                            {
                                System.out.println("This ID already exists!");
                                found2=true;
                                break;
                            }
                        }
                        if(!found2){
                            p.id = newID;
                            System.out.println("ID changed successfully");
                            changed = true;
                        }
                        
                   } while(!changed);

                    found = true;
                    break;
            }
            

        }
            if (!found) {
                System.out.println("Invalid ID ");
            }
            System.out.println("\n1- Continue");
            System.out.println("0- Exit");
            choice = input.nextInt();
            if(choice==0){
                return;
            }
        }while (choice != 0);
    }
    

    static void showCart(ArrayList<Cart> cart,Scanner input){
        int choice;
        if(cart.isEmpty()){
                System.out.println("Cart is empty! ");
                return;
            }
            System.out.println("\n \t ---Cart--- \n");
        do{
           
            double totalPrice =0;
            for(Cart c : cart){
                totalPrice += c.price;
                System.out.println(c.name + " | " + c.quantity + " units | $" + c.price );
            }
            System.out.println("Total Price is " + totalPrice);
            
            System.out.println("\n1- Continue");
            System.out.println("2- Remove Items");
            System.out.println("0- Exit");
            choice = input.nextInt();
            if(choice==0){
                return;
            }
            else if(choice==2){
                removeProduct(products, input);
            }
        } while(choice!=0);

    }

    static void removeProduct (ArrayList<Product> products, Scanner input){
        int choice;
        do{
            System.out.println("Which product you want to remove ? ");
            for(Cart c : cart){
                System.out.println(c.id +"- "+c.name + " | " + c.quantity + " units | $" + c.price );
            }
            int id =input.nextInt(), remain=0;
            boolean changed=false, found=false;
            for(Cart c : cart){
                if(id==c.id){
                    found=true;
                    do{
                        System.out.println("What is the quantity you want to remove ? ");
                        int quantity = input.nextInt();
                        if(quantity<=c.quantity){
                            c.quantity-=quantity;
                            
                            remain=c.quantity;
                            changed=true;
                            for(Product p: products){
                                if(p.id == id){
                                    p.quantity+=quantity;
                                    c.price = c.quantity*p.price;
                                    break;
                                }
                            }
                        }
                        else{
                            System.out.println("Less quantity! ");
                        }
                    }while(!changed);
                }
            }
            if(found){
                for(int i=0 ;i<cart.size();i++){
                    if(cart.get(i).id==id){
                        if(remain==0){
                            cart.remove(i);
                        }
                        System.out.println("Removed successfully ");
                    }
                }
            }
            else{
                System.out.println("Invalid ID ");
            }

            System.out.println("\n1- Continue");
            System.out.println("0- Exit");
            choice = input.nextInt();
            if(choice==0){
                return;
            }

        }while (choice !=0);
    }

    public static void main(String[] args) {

        products.add(new Product(1, "Milk", 20, 10));
        products.add(new Product(2, "Cheese", 12, 22));
        products.add(new Product(3, "Bread", 18, 26)); 

        Scanner input = new Scanner(System.in);
        System.out.println("What type of user ? \n1- Customer \n2- Employee");
        int type = input.nextInt();

        if (type == 1) {
            customerMenu(input);
        } else if (type == 2) {
            employeeMenu(input);
        } else {
            System.out.println("Wrong choice");
        }
    }
}