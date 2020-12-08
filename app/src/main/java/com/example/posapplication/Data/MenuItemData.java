package com.example.posapplication.Data;

import com.example.posapplication.MenuItemEntity;

public class MenuItemData {
    public static MenuItemEntity[] populateMenuItemDatabase() {
        return new MenuItemEntity[] {
                // Breakfast
                new MenuItemEntity("Nat's Breakfast", 12.99),
                new MenuItemEntity("Sunny Start", 10.56),
                new MenuItemEntity("Benedict", 8.36),

                // Burgers
                new MenuItemEntity("Legendary Burger", 13.80),
                new MenuItemEntity("BC Burger", 15.39),
                new MenuItemEntity("Impossible", 18.59),
                new MenuItemEntity("Chicken Burger", 18.59),

                // Deserts
                new MenuItemEntity("Strawberry Cheescake", 18.59),
                new MenuItemEntity("Blueberry Cheescake", 18.59),
                new MenuItemEntity("Brownie", 18.59),
                new MenuItemEntity("Apple Pie", 18.59),

                // Drinks
                new MenuItemEntity("Pop", 18.59),
                new MenuItemEntity("Tea", 18.59),
                new MenuItemEntity("Coffee", 18.59),
                new MenuItemEntity("Shakes", 18.59),

                // Pastas and Bowls
                new MenuItemEntity("Fettuciine", 18.59),
                new MenuItemEntity("Bolognese", 18.59),
                new MenuItemEntity("Teriyaki", 18.59),
                new MenuItemEntity("Power Bowl", 18.59)
        };
    }
}