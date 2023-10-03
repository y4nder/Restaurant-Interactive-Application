import java.util.Scanner;

public class RestaurantInteractiveApplication {
    abstract class MenuItem{
        protected String itemName;
        protected double itemPrice;
        
        public String getItemName(){
            return itemName;
        }
        
        public double getItemPrice(){
            return itemPrice;
        }
    
        public String toString(){
            return "[" + itemName + "]" + " - Php " + itemPrice;
        }
    }

    public class MainDish extends MenuItem{
        public MainDish(String itemName, double itemPrice){
            this.itemName = itemName;
            this.itemPrice = itemPrice;
        }
    }

    public class AddOn extends MenuItem{
        public AddOn(String addOnName, double addOnPrice){
            this.itemName = addOnName;
            this.itemPrice = addOnPrice;
        }
    }

    public class Menu {
        private MenuItem[] mainDishes;
        private MenuItem[] addOns;
    
        public Menu(MenuItem[] mainDishes, MenuItem[] addOns){
            this.mainDishes = mainDishes;
            this.addOns = addOns;
        }
        
        public void view(){
            System.out.println("\n---Menu:----");
            int i = 0;
            for(; i < mainDishes.length; i++){
                System.out.println(mainDishes[i]);
            }
            System.out.println("\nAdd Ons:");
            for(int j = 0; j < addOns.length; j++){
                System.out.println(addOns[j]);
            }
        }
    
        public MenuItem getMenuItem(String choice){
            for(int i = 0; i < mainDishes.length; i++){
                if(mainDishes[i].getItemName().equals(choice)){
                    return mainDishes[i];
                }
            }
    
            for(int i = 0; i < addOns.length; i++){
                if(addOns[i].getItemName().equals(choice)){
                    return addOns[i];
                }
            }
            return null;
        }
    
    }
    
    static class DefaultMenu {
        static MenuItem[] mainDishes =  {    
            r.new MainDish("C1", 100),
            r.new MainDish("C2", 150),
            r.new MainDish("C3", 200)
        };
        static MenuItem[] addOns = {
            r.new AddOn("R1", 35),
            r.new AddOn("R2", 35)
        };
    }
    
    public class OrderedItem {
        private MenuItem orderedItem;
        private int quantity;
    
        public OrderedItem(MenuItem orderedItem, int quantity){
            this.orderedItem = orderedItem;
            this.quantity = quantity;
        }
    
        public double calculateTotal(){
            return orderedItem.getItemPrice() * quantity;
        }
    
        public String toString(){
            return String.format("%dx %s Php %.2f", quantity, orderedItem.getItemName(), calculateTotal());
        }
    }
    
    public class MyOrders implements ConvertToDollar{
        private OrderedItem[] orderedItems;
        private int orderedItemCount;
    
        public MyOrders(){
            orderedItems = new OrderedItem[50];
            orderedItemCount = 0;
        }
    
        public void addToOrder(OrderedItem orderedItem){
            orderedItems[orderedItemCount] = orderedItem;
            orderedItemCount++;
        }
    
        public void showMyOrders(){
            System.out.println("\n-----YOUR ORDERS-----");
            for(int i = 0; i < orderedItemCount; i++){
                System.out.println(orderedItems[i]);
            }
            System.out.printf("\n%s %.2f\n", "Total cost: Php", calculateTotal());
        }
    
        public void checkOut(){
            System.out.println("\nThank you for ordering!!");
            showMyOrders();
            System.out.printf("%s %.2f\n", "Total cost in Dollars: $", pesoToDollar());
        }
        
        @Override
        public double pesoToDollar() {
            return calculateTotal() / dollarValue;
        }    
    
        public double calculateTotal(){
            double total = 0.0;
            for(int i = 0; i < orderedItemCount; i++){
                total += orderedItems[i].calculateTotal();
            }
            return total;
        }
    
        public void clearOrders(){
            this.orderedItems = new OrderedItem[50];
            this.orderedItemCount = 0;
        }
    }

    interface ConvertToDollar {
        final double dollarValue = 56.84;
        public double pesoToDollar();
    }
        
    static RestaurantInteractiveApplication r = new RestaurantInteractiveApplication();
    static Scanner scan = new Scanner(System.in);
    static Menu menu = r.new Menu(DefaultMenu.mainDishes, DefaultMenu.addOns);
    public static void main(String[] args){
        MyOrders myOrders = r.new MyOrders();
        char option;
                do{
                    System.out.println("\n-----Restaurant Interactive Application-----");
                    System.out.println("1) Order Food");
                    System.out.println("2) View Orders");
                    System.out.println("3) Check out");
                    System.out.println("X) Exit");
            
                    System.out.print("\noption: ");
                    option = scan.next().charAt(0);
                    scan.nextLine();
            
                    switch(option){
                        case '1':
                            order(myOrders);
                            break;
                        case '2':
                            myOrders.showMyOrders();
                            break;
                        case '3':
                            myOrders.checkOut();
                            break;
                        case 'x':
                            System.out.println("Come again..");
                            break;
                        default: 
                            break;
                    }
                }
                while(option != 'x');
    }

    static void order(MyOrders myOrders){
        menu.view();
        MenuItem menuChoice = null;
        System.out.println("\nUse the Product Code");
        do{
            System.out.print("\nEnter your order: ");
            String choice = scan.nextLine().toUpperCase();
    
            menuChoice = menu.getMenuItem(choice);

            if(menuChoice == null){
                System.out.println("invalid choice");
            }
            else{
                System.out.print("Enter quantity: ");
                int quantity = scan.nextInt();
                myOrders.addToOrder(r.new OrderedItem(menuChoice, quantity));
            }
        }
        while(menuChoice == null);
    } 
}
