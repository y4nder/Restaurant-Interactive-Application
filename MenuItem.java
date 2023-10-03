abstract class MenuItem{
    protected String itemName;
    protected double itemPrice;
    
    
    public String getItemName(){
        return itemName;
    }
    
    public double getItemPrice(){
        return itemPrice;
    }

    public void updateItemPrice(double newPrice){
        this.itemPrice = newPrice;
    }

    public String toString(){
        return itemName + " - Php " + itemPrice;
    }
}