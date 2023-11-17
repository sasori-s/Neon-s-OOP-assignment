package Farmer;

import Farm.Farm;
import Foods.Food;
import Foods.Fruit;
import Foods.Vegetable;

public class Farmer {
    private String name;
    private int energy;
    private Farm farm;

    public Farmer(String name){
        this.name = name;
        this.energy = 100;
        this.farm = null;
    }

    public Farmer(String name, Farm farm){
        this.name = name;
        this.energy = 100;
        this.farm = farm;
    }

    public Food getFoodFromFarm(int index) {
        if (farm != null) {
            return farm.getFood(index);
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy; 
    }

    public void sleep() {
        energy = Math.min(energy + 35, 100);
    }

    public boolean seedFood(Food food){
        if (farm != null && energy >= requiredEnergy(food) && farm.seedFood(food)) {
            if (farm.seedFood(food)) {
                energy -= requiredEnergy(food);
                return true;
            }
            
        } 
        return false;
    }

    public int requiredEnergy(Food food){
        if (food instanceof Vegetable) {
            return 30;
        } else if (food instanceof Fruit) {
            return 50;
        }

        return 0;
    }

    public boolean buyFarm(Farm farm){
        if (this.farm == null){
            this.farm = farm;
            return true;
        }

        return false;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Farmer:").append(name).append("\n");
        builder.append("Energy left: ").append(energy).append("/100\n");
        if (farm != null){
            builder.append("Farm info:\n").append(farm.toString());
        } else {
            builder.append(name).append(" owns no farm");
        }

        return builder.toString();
    }

}