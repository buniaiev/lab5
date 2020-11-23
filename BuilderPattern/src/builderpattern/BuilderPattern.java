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
public class BuilderPattern {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        HappyMealBuilder builder = new HappyMealBuilder();
        
        builder.setMainDish(MainDish.CHICKEN);
        builder.setAddDish(AddDish.FRENCH_FRIES);
        builder.setDrink(Drink.WATER);
        builder.setToy("toy");
        builder.setBox("box");
        
        HappyMeal happymeal = builder.getResult();
    }
    
}
