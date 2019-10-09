package AnExceptionalVendingMachine;

public class VendingMachine {

    int stockCounter = 0;

    int saltySnacksCounter = 0;
    int chocolateCounter = 0;
    int softDrinksCounter = 0;

    public void buy(Product product) throws ProductNotFoundException{
        if (product instanceof SaltySnack){
            if(saltySnacksCounter <= 0){
                throw new SaltySnackOutOfStock();
            } else {
                saltySnacksCounter--;
                stockCounter--;
                System.out.println("You have bought a "+ product.getName());
            }
        } else if (product instanceof SoftDrink){
            if(softDrinksCounter <= 0){
                throw new SoftDrinksOutOfStock();
            } else {
                softDrinksCounter--;
                stockCounter--;
                System.out.println("You have bought a "+ product.getName());
            }
        } else if (product instanceof Chocolate){
            if(chocolateCounter <= 0){
                throw new ChocolateOutOfStock();
            } else {
                chocolateCounter--;
                stockCounter--;
                System.out.println("You have bought a "+ product.getName());
            }
        } else {
            try {
                throw new InvalidProductException();
            }catch (InvalidProductException e){
                e.printStackTrace();
            }
        }
    }

    public void addStock(Product product, int newStock){
        if(!(product instanceof Product)){
            throw new InvalidProductException();
        } else {
            if (product instanceof SaltySnack){

                    saltySnacksCounter+= newStock;
                    stockCounter+= newStock;
                System.out.println("You have added " + newStock + " " + product.getName() + ". \nChocolates stock = " +
                        saltySnacksCounter + ". \nCurrent stock = " + stockCounter + ". \n----------------------------------");
                } else if (product instanceof SoftDrink){

                    softDrinksCounter+= newStock;
                    stockCounter+= newStock;
                System.out.println("You have added " + newStock + " " + product.getName() + ". \nSoft drinks stock = " +
                        softDrinksCounter + ". \nCurrent stock = " + stockCounter + ". \n----------------------------------");
                } else if (product instanceof Chocolate){

                    chocolateCounter+= newStock;
                    stockCounter += newStock;
                System.out.println("You have added " + newStock + " " + product.getName() + ". \nChocolates stock = " +
                        chocolateCounter + ". \nCurrent stock = " + stockCounter + ". \n----------------------------------");
                }
            }

        }

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        Product saltySnacks = new SaltySnack("Fritos");
        Product softDrinks = new SoftDrink("Coke");
        Product chocolate = new Chocolate("Cadbury");
        Fruits fruits = new Fruits("Orange");

        vendingMachine.addStock(saltySnacks,6);
        vendingMachine.addStock(softDrinks,3);
        vendingMachine.addStock(chocolate, 1);
//        vendingMachine.addStock(fruits,6);

        try {
            vendingMachine.buy(saltySnacks);
            vendingMachine.buy(saltySnacks);
            vendingMachine.buy(softDrinks);
            vendingMachine.buy(chocolate);
            vendingMachine.buy(fruits);
            vendingMachine.buy(chocolate);
        } catch ( InvalidProductException | ProductNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Product{

    String name;
    Product(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class SoftDrink extends Product{

    SoftDrink(String name) {
        super(name);
    }
}

class SaltySnack extends Product{

    SaltySnack(String name) {
        super(name);
    }
}

class Chocolate extends Product{

    Chocolate(String name) {
        super(name);
    }
}

class Fruits extends Product{
    String name;

    Fruits(String name) {
       super(name);
    }

    public String getName() {
        return name;
    }
}

class ProductNotFoundException  extends Exception {
    ProductNotFoundException(String msg){
        super(msg);
    }
}

class InvalidProductException extends RuntimeException{
    InvalidProductException(){
        super("The product entered is not valid!");
    }
}

class SoftDrinksOutOfStock extends ProductNotFoundException{
    SoftDrinksOutOfStock(){
        super("Soft Drinks out of stock!");
    }
}

class SaltySnackOutOfStock extends ProductNotFoundException{
    SaltySnackOutOfStock(){
        super("Salty snacks out of stock!");
    }
}

class ChocolateOutOfStock extends ProductNotFoundException{
    ChocolateOutOfStock(){
        super("Chocolate out of stock!");
    }
}
