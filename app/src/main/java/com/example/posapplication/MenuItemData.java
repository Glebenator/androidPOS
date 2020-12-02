package com.example.posapplication;

public class MenuItemData {
    public static MenuItemEntity[] populateMenuItemDatabase() {
        return new MenuItemEntity[] {
                new MenuItemEntity("Nat's Breakfast", 12.99),
                new MenuItemEntity("Sunny Start", 10.56),
                new MenuItemEntity("Benedict", 8.36),
                new MenuItemEntity("Hash", 14.58),
                new MenuItemEntity("Legendary Burger", 13.80),
                new MenuItemEntity("BC Burger", 15.39),
        };
    }
}
