package com.example.posapplication;

public class MenuItemData {
    public static MenuItemEntity[] populateMenuItemDatabase() {
        return new MenuItemEntity[] {
                new MenuItemEntity("Nat's Hearty Breakfast", 12.99),
                new MenuItemEntity("The BC Sunny Start", 10.56),
                new MenuItemEntity("Classic Benedict", 8.36),
                new MenuItemEntity("CheeseSteak Hash", 14.58),
                new MenuItemEntity("Legendary Burger", 13.80),
                new MenuItemEntity("BC Burger", 15.39),
        };
    }
}
