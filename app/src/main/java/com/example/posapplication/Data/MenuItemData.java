package com.example.posapplication.Data;

import com.example.posapplication.MenuItemEntity;

public class MenuItemData {
    public static MenuItemEntity[] populateMenuItemDatabase() {
        return new MenuItemEntity[] {
                // Breakfast
                new MenuItemEntity("Nat's Breakfast", 15.49),
                new MenuItemEntity("Sunny Start", 13.99),
                new MenuItemEntity("Benedict", 15.49),

                // Burgers
                new MenuItemEntity("Legendary Burger", 14.99),
                new MenuItemEntity("BC Burger", 17.99),
                new MenuItemEntity("Impossible", 17.99),
                new MenuItemEntity("Chicken Burger", 17.99),

                // Deserts
                new MenuItemEntity("Strawberry Cheescake", 6.99),
                new MenuItemEntity("Blueberry Cheescake", 6.99),
                new MenuItemEntity("Brownie", 6.99),
                new MenuItemEntity("Apple Pie", 6.99),

                // Drinks
                new MenuItemEntity("Pop", 3.00),
                new MenuItemEntity("Tea", 3.00),
                new MenuItemEntity("Coffee", 3.49),
                new MenuItemEntity("Shakes", 5.99),

                // Pastas and Bowls
                new MenuItemEntity("Fettuccine", 19.49),
                new MenuItemEntity("Bolognese", 17.99),
                new MenuItemEntity("Teriyaki", 18.49),
                new MenuItemEntity("Power Bowl", 18.49)
        };
    }
}
