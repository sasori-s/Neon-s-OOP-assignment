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
import Foods.*;

public class CheckProblem4 {

  // 4.1 - Farm Constructor
  @Test
  public void check_constructor_Farm() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Farm f = new Farm(1500, Soil.Clay);
    if(f.getTotalArea() != 1500 || f.getFarmSoil() != Soil.Clay){
        output.append("Make sure your constructor is initializing the area and soil");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }

  // 4.2 - Farm Seed Food
  @Test
  public void check_seedFood_Farm() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Farm f = new Farm(300, Soil.Clay);

    try {
        if(!f.seedFood(new Apple())){
            output.append("We have room for a new apple orchard the method should return true");
            flag = false;
            assertTrue(output.toString(), flag);
            return;
        }
        if(f.seedFood(new Apple())){
            output.append("We don't have room for another orchard");
            flag = false;
            assertTrue(output.toString(), flag);
            return;
        }
    }
    catch (NullPointerException e){
        output.append("Did you remember to initialize the ArrayList in the constructor?");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    flag = true;

    assertTrue(output.toString(), flag);
  }

  // 4.3 - Farm overnightGrow method
  @Test
  public void check_overnightGrow_Farm() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Farm f = new Farm(6000, Soil.Loam);
    Vegetable v = new Vegetable("Some Vegetable", 500.25, 1, 10);

    //OK, I am done testing if you initialized the ArrayList. I am assuming you did
    f.seedFood(v);
    f.seedFood(new Apple());
    //Grow one night
    f.overnightGrow();
    if(f.getFood(0).getDaysSincePotted() != 1 || f.getFood(1).getDaysSincePotted() != 1){
        output.append("One night of growth means it has been a day since potted");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    //Plant something new
    f.seedFood(new Cabbage());
    f.overnightGrow();
    if(f.getFood(0).getDaysSincePotted() != 2
            || f.getFood(1).getDaysSincePotted() != 2
            || f.getFood(2).getDaysSincePotted() != 0
    ){
        output.append("The veggie and apple should have 2 days, " +
                "cabbage zero (it was planted at day 2 but it doesn't like Loam soil)");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    flag = true;

    assertTrue(output.toString(), flag);
  }

  // 4.4 - Farm totalFarmValue
  @Test
  public void check_totalFarmValue_Farm() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Farm f = new Farm(600, Soil.Clay);
    try {
        if(f.getTotalFarmValue() != 0){
            output.append("Farm is empty... no value");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
        }
        f.seedFood(new Apple());
        f.seedFood(new Apple());
        if(f.getTotalFarmValue() != 1250 *2){
            output.append("We have two apple orchards the value should be 2500");
            flag = false;
            assertTrue(output.toString(), flag);
            return;
        }
    }
    catch (NullPointerException e){
        output.append("Did you remember to initialize the ArrayList in the constructor?");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    flag = true;

    assertTrue(output.toString(), flag);
  }

  // 4.5 - Farm readyToHarvestValue
  @Test
  public void check_readyToHarvestValue_Farm() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Farm f = new Farm(600, Soil.Loam);
    try {
        Vegetable v = new Vegetable("Some Vegetable", 500.25, 1, 10);
        Apple a = new Apple();
        f.seedFood(v);
        f.seedFood(a);

        f.overnightGrow();
        double readyValue = f.getReadyToHarvestValue();
        if(readyValue != 500.25){
            output.append("Make sure you implemented the overnightGrow method (no specific test for it)");
            output.append("The vegetable is ready to be harvested after one night, apple is not");
            flag = false;
            assertTrue(output.toString(), flag);
            return;
        }
    }
    catch (NullPointerException e){
        output.append("Did you remember to initialize the ArrayList in the constructor?");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    flag = true;

    assertTrue(output.toString(), flag);
  }

  // 4.6 - Farm harvest method
  @Test
  public void check_harvest_Farm() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Farm f = new Farm(15, Soil.Loam);
    Vegetable v = new Vegetable("Some Vegetable", 500.25, 1, 10);

    //OK, I am done testing if you initialized the ArrayList. I am assuming you did
    f.seedFood(v);

    if(f.seedFood(v)){
        output.append("The farm does not have enough space for another vegetable");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    if(f.harvestFood(0) != null){
        output.append("The vegetable is not ready to be harvested yet...");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    f.overnightGrow();

    Food harvest = f.harvestFood(0);
    if(harvest == null){
        output.append("The vegetable was ready to be harvested");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    if(f.getFoodQuantity() != 0){
        output.append("We harvested the vegetable it should not be on food list");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    Vegetable v2 = new Vegetable("Some Vegetable", 500.25, 1, 13);
    if(!f.seedFood(v2)){
        output.append("We harvested the only food in the farm, we should be able to seed another. There should be enough room");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    //I am using == here. You should not create copies. v and harvest should be the same object
    if(v != harvest){
        output.append("The vegetable object we seeded should be the same we harvest after");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }

  // 4.7 - Farm toString()
  @Test
  public void check_toString_Farm() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Farm f = new Farm(600, Soil.Loam);

    //First and empty farm
    String expected = "Total farm value: 0.00\n" +
            "Ready to harvest value: 0.00\n" +
            "No food available";
    String actual = f.toString();
    if(!expected.replaceAll("\\s+","").equalsIgnoreCase(actual.replaceAll("\\s+",""))){
        output.append("Make sure the toString is following the specification");
        output.append("Expected: " + expected);
        output.append("Actual: " + actual);
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    //Lets seed some stuff
    f.seedFood(new Cabbage());
    f.seedFood(new Carrot());
    f.seedFood(new Apple());

    expected = "Total farm value: 2240.07\n" +
            "Ready to harvest value: 0.00\n" +
            "Food available: \n" +
            "0 - Cabbage (Clay) - 0/10 days\n" +
            "1 - Carrot (Sand) - 0/15 days\n" +
            "2 - Apple (Loam) - 0/15 days";
    actual = f.toString();
    if(!expected.replaceAll("\\s+","").equalsIgnoreCase(actual.replaceAll("\\s+",""))){
        output.append("Make sure the toString is following the specification");
        output.append("Expected: " + expected);
        output.append("Actual: " + actual);
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }

  // 4.8 - AppleFarm seed
  @Test
  public void check_seedFood_AppleFarm() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Farm appleFarm = new AppleFarm(3000);
    if(appleFarm.seedFood(new Cabbage())){
        output.append("You can only seed Apples on an apple farm.\nAre you overriding seed?");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    if(!appleFarm.seedFood(new Apple())){
        output.append("hmm... I should be able to seed apples in a Apple farm");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    flag = true;

    assertTrue(output.toString(), flag);
  }

}

// FREEZE CODE END
