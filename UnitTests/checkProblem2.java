package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Farm.Soil;
import Foods.Fruit;
import Foods.Vegetable;

public class CheckProblem2 {

  // 2.1 - Vegetable Constructor
  @Test
  public void check_constructor_Vegtable() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Vegetable veg = new Vegetable("Veggie", 10,5,100);
    if(veg.getPreferredSoil() != Soil.Loam){
        output.append("The default preferred soil of vegetables is Loam");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }



  // 2.2 - Vegetable grow(Soil)
  @Test
  public void check_grow_Vegatable() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    //First if preferred soil
    Vegetable veg = new Vegetable("Veggie", 10,5,100);
    if(veg.grow(Soil.Loam) != 1.0/5){
        output.append("When a vegetable is on its preferred soil it should grow as defined in food");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    //now on any other soil type
    veg = new Vegetable("Veggie", 10,5,100);
    if(veg.grow(Soil.Clay) != 0){
        output.append("Vegetables need two cycles to grow on a non-preferred Soil (skip one day)");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    if(veg.grow(Soil.Clay) != 1.0/5){
        output.append("This is the second call to grow on a non-preferred Soil. " +
                "The vegetable should grow normally");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }

  // 2.3 - Fruit Constructor
  @Test
  public void check_constructor_Fruit() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Fruit f = new Fruit("Fruit", 10,5,100);
    if(f.getPreferredSoil() != Soil.Silt){
        output.append("The default preferred soil of fruits is Silt");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }

    // 2.4 - Fruit grow(Soil)
  @Test
  public void check_grow_Fruit() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    //First if preferred soil
    Fruit f = new Fruit("Fruit", 10,5,100);
    if(f.grow(Soil.Silt) != 1.0/5){
        output.append("When a fruit is on its preferred soil it should grow as defined in food");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    //now on any other soil type
    f = new Fruit("Fruit", 10,5,100);
    if(f.grow(Soil.Sand) != 0){
        output.append("Fruits need three cycles (skip 2 days) to grow on a non-preferred Soil");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    if(f.grow(Soil.Sand) != 0){
        output.append("Fruits need three cycles (skip 2 days) to grow on a non-preferred Soil");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    if(f.grow(Soil.Sand) != 1.0/5){
        output.append("This is the third call to grow on a non-preferred Soil. " +
                "The fruit should grow normally");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }
}

// FREEZE CODE END
