package com.yoj.core.level4;

public class FoodSelect {
    public static void main(String[] args) {
        Food food = new Food();
        Selectable selectable = new Food();

        callFoodMethods(food);
        callSelectableMethods(selectable);
    }

    public static void callFoodMethods(Food food) {
        food.onSelect();
        food.onEat();
        //тут добавьте вызов методов для переменной food
    }

    public static void callSelectableMethods(Selectable selectable) {
        selectable.onSelect();
        //тут добавьте вызов методов для переменной selectable
    }

    interface Selectable {
        void onSelect();
    }

    static class Food implements  Selectable {
        final static String SELECTED = "The food was selected";

        public void onEat() {
            System.out.println("The food was eaten");
        }

        @Override
        public void onSelect() {
            System.out.println(SELECTED);
        }
    }
}
