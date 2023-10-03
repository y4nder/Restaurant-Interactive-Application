public class Menu {
    private MenuItem[] mainDishes;
    private MenuItem[] addOns;
    private int countMainDish;
    private int countAddOn;

    public Menu(MenuItem[] mainDishes, MenuItem[] addOns){
        this.mainDishes = mainDishes;
        this.addOns = addOns;
        
        countMainDish = mainDishes.length;
        countAddOn = addOns.length;
    }
    
    public void view(){
        System.out.println("Menu:");
        for(int i = 0; i < countMainDish; i++){
            System.out.println(mainDishes[i]);
        }
        System.out.println("Add Ons:");
        for(int i = 0; i < countAddOn; i++){
            System.out.println(addOns[i]);
        }
        
    }
    
    public static void main(String[] args){   
        MenuItem[] menuItem =  {    
                new MainDish("C1", 100),
                new MainDish("C2", 150),
                new MainDish("C3", 200)
        };
        
        MenuItem[] addOns = {
                new AddOn("R1", 35),
                new AddOn("R2", 35)
        };

        Menu menu = new Menu(menuItem, addOns);

        menu.view();

    }
}
