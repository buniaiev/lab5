/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builderpattern;

/**
 *
 * @author Ilya
 */
public class HappyMealBuilder implements Builder{
    
    private MainDish mainDish;
    private AddDish addDish;
    private Drink drink;
    private String toy;
    private String box;

    public void setMainDish(MainDish mainDish) {
        this.mainDish = mainDish;
    }

    public void setAddDish(AddDish addDish) {
        this.addDish = addDish;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public void setToy(String toy) {
        this.toy = toy;
    }

    public void setBox(String box) {
        this.box = box;
    }
    
    public HappyMeal getResult() {
        return new HappyMeal(mainDish, addDish, drink, toy, box);
    }
}
