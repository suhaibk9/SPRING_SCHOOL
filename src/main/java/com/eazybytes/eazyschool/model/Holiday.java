package com.eazybytes.eazyschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter // Generates getters for all fields
@AllArgsConstructor // Generates an all-args constructor
@ToString // Generates a toString() method
public class Holiday {

    private final String day;
    private final String reason;
    private final Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }
}