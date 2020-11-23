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
public class HappyMeal {

    private final MainDish mainDish;
    private final AddDish addDish;
    private final Drink drink;
    private final String toy;
    private final String box;

    public HappyMeal(MainDish mainDish, AddDish addDish, Drink drink, String toy, String box){
        this.mainDish = mainDish;
        this.addDish = addDish;
        this.drink = drink;
        this.toy = toy;
        this.box = box;
    }
    
    public MainDish getMainDish() {
        return mainDish;
    }

    public AddDish getAddDish() {
        return addDish;
    }
    public Drink getDrink() {
        return drink;
    }
    public String getToy() {
        return toy;
    }

    public String getBox() {
        return box;
    }
}
