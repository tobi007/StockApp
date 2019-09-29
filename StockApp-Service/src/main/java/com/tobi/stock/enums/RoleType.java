package com.tobi.stock.enums;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 25/09/2019
 * Time: 12:37 PM
 **/

public enum RoleType {
    BASIC("BASIC");

    private String value;

    RoleType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }


    public static RoleType get(String value) {

        for (RoleType roleType : values()) {

            if (roleType.toString().equalsIgnoreCase(value.toLowerCase())) {
                return roleType;
            }
        }

        return null;
    }
}
