package com.example.posapplication;

public class UsersData {

    public static UserEntity[] populateUserDatabase() {
        return new UserEntity[] {
                new UserEntity("Administrator", "Administrator", "12345", "Admin", "Admin"),
                new UserEntity("Server", "Server", "12345", "Server", "Server"),
                new UserEntity("Cook", "Cook", "12345", "Cook", "Cook")
        };
    }
}
