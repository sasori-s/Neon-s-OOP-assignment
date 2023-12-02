package UnitTests;

// FREEZE CODE BEGIN

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.Assert.*;
import java.lang.reflect.Modifier;
import org.junit.Test;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import Farm.*;
import Farmer.Farmer;
import Foods.Apple;
import Foods.Cabbage;
import Foods.Carrot;
import java.lang.reflect.Field;

public class CheckProblem5 {

  // 5.1 - Farmer Constructor With Farm and Getters
  @Test
  public void check_constructorWithFarmAndGetters_Farmer() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Farm farm = new Farm(4900, Soil.Loam);
    Farmer farmer = new Farmer("Joe", farm);

    //I'll break encapsulation to check the farm
    try {
        Field field = Farmer.class.getDeclaredField("farm");
        field.setAccessible(true);
        try {
            //We should not make a copy of the arm in the constructor
            if (field.get(farmer) != farm) {
                output.append("The farmer should have a farm");
                flag = false;
                assertTrue(output.toString(), flag);
                return;
            }
        }
        catch (IllegalAccessException e){
            output.append("something when wrong, ping Juliano!");
            flag = false;
            assertTrue(output.toString(), flag);
            return;
        }

    }
    catch (NoSuchFieldException e){
        output.append("Make sure you have a Farm object named farm (follow the UML diagram)");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }

  // 5.2 - Farmer Constructor W/O Farm and Getters
  @Test
  public void check_constructorNoFarmOrGetters_Farmer() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    //First a farmer without a farm;
    Farmer f = new Farmer("Joe");
    if(!f.getName().equals("Joe") || f.getEnergy() != 100){
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    //I'll break encapsulation to check the farm
    try {
        Field field = Farmer.class.getDeclaredField("farm");
        field.setAccessible(true);
        try {
            if (field.get(f) != null) {
                output.append("The farmer should NOT have a farm");
                flag = false;
                assertTrue(output.toString(), flag);
                return;
            }
        }
        catch (IllegalAccessException e){
            output.append("something when wrong, ping Juliano!");
            flag = false;
            assertTrue(output.toString(), flag);
            return;
        }

    }
    catch (NoSuchFieldException e){
        output.append("Make sure you have a Farm object named farm (follow the UML diagram)");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }

  // 5.3 - Farmer Buy Farm
  @Test
  public void check_buyFarm_Farmer() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Farm farm = new Farm(4900, Soil.Loam);
    Farmer farmer = new Farmer("Joe");

    if(!farmer.buyFarm(farm)){
        output.append("The farmer had no farm, he can buy one");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    Farm farm2 = new Farm(100, Soil.Clay);
    if(farmer.buyFarm(farm2)){
        output.append("The farmer already have a farm, he cannot buy another one");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    flag = true;

    assertTrue(output.toString(), flag);
  }

  // 5.4 - Farmer Seed Food
  @Test
  public void check_seedFood_Farmer() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Farmer jeff = new Farmer("Jeff");

    try{
        if(jeff.seedFood(new Apple())){
            output.append("Jeff doesn't own a farm, he should not be able to seed anything");
            flag = false;
            assertTrue(output.toString(), flag);
            return;
        }
    }
    catch(NullPointerException e){
        output.append("Jeff doen't own a farm, the variable value is null (as it should be)");
        output.append("Make sure you are not calling a method on a null variable");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }


    Farmer farmer = new Farmer("Joe", new Farm(4900, Soil.Loam));

    if(!farmer.seedFood(new Apple())){
        output.append("The farmer has energy to seed an apple orchard");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    if(!farmer.seedFood(new Cabbage())){
        output.append("The farmer still have energy to seed some Cabbage (\"My Cabbage!\")");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    if(farmer.getEnergy() != 20){
        output.append("The farmer planted a fruit and a vegetable he should have 20 energy left");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    if(farmer.seedFood(new Apple())){
        output.append("The farmer doesn't have energy left to plant another Fruit");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }

  // 5.5 - Farmer Sleep
  @Test
  public void check_sleep_Farmer() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    //This method is interesting, it should recover the farmer (by 35) and grow the farm
    Farmer farmer = new Farmer("Joe", new Farm(4900, Soil.Loam));
    //Fruit costs 50 of energy
    farmer.seedFood(new Apple());

    farmer.sleep();
    if(farmer.getEnergy() != 100 - 50 + 35){
        output.append("Check the energy regen on the sleep method");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    //Lets check if the food has grown
    if(farmer.getFoodFromFarm(0).getDaysSincePotted() != 1){
        output.append("The farmer slept one night the apple should have grown one day");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }

  // 5.6 - Farmer toString (owns a farm)
  @Test
  public void check_toStringWithFarm_Farmer() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Farmer farmer = new Farmer("Joe", new Farm(4900, Soil.Loam));
    //Fruit costs 50 of energy
    farmer.seedFood(new Apple());
    farmer.sleep();
    farmer.seedFood(new Apple());
    farmer.seedFood(new Carrot());

    String expected = "Farmer: Joe\n" +
                    "Energy left: 5/100\n" +
                    "Farm info: \n" +
                    "Total farm value: 3250.32\n" +
                    "Ready to harvest value: 0.00\n" +
                    "Food available: \n" +
                    "0 - Apple (Loam) - 1/15 days\n" +
                    "1 - Apple (Loam) - 0/15 days\n" +
                    "2 - Carrot (Sand) - 0/15 days";
    String actual = farmer.toString();

    if(!expected.replaceAll("\\s+", "").equalsIgnoreCase(actual.replaceAll("\\s+",""))){
        output.append("Make sure your toString is correct");
        output.append("Expected: " + expected);
        output.append("Actual: " + actual);
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }

  // 5.7 - Farmer toString (no farm)
  @Test
  public void check_toStringNoFarm_Farmer() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Farmer farmer = new Farmer("Joe");
    String expected = "Farmer: Joe\n" +
            "Energy left: 100/100\n" +
            "Joe owns no farm";


    String actual = farmer.toString();


    if(!expected.replaceAll("\\s+", "").equalsIgnoreCase(actual.replaceAll("\\s+",""))){
        output.append("Make sure your toString is correct");
        output.append("Expected: " + expected);
        output.append("Actual: " + actual);
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }

}

// FREEZE CODE END
