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
public interface Builder {
    public void setMainDish(MainDish mainDish);
    public void setAddDish(AddDish addDish);
    public void setDrink(Drink drink);
    public void setToy(String toy);
    public void setBox(String box);
}