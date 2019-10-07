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
            throw new InvalidProductException();
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

        vendingMachine.addStock(saltySnacks,6);
        vendingMachine.addStock(softDrinks,3);
        vendingMachine.addStock(chocolate, 1);

        try {
            vendingMachine.buy(saltySnacks);
            vendingMachine.buy(saltySnacks);
            vendingMachine.buy(saltySnacks);
            vendingMachine.buy(softDrinks);
            vendingMachine.buy(chocolate);
            vendingMachine.buy(chocolate);
        } catch (ProductNotFoundException e) {
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

class ProductNotFoundException  extends Exception {
    ProductNotFoundException(){
        System.out.println("Product not found!");
    }
}

class InvalidProductException extends RuntimeException{
    InvalidProductException(){
        System.out.println("The product entered is not valid!");
    }
}

class SoftDrinksOutOfStock extends ProductNotFoundException{
    SoftDrinksOutOfStock(){
        System.out.println("Soft Drinks out of stock!");
    }
}

class SaltySnackOutOfStock extends ProductNotFoundException{
    SaltySnackOutOfStock(){
        System.out.println("Salty snacks out of stock!");
    }
}

class ChocolateOutOfStock extends ProductNotFoundException{
    ChocolateOutOfStock(){
        System.out.println("Chocolate out of stocok!");
    }
}
