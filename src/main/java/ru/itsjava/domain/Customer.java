package ru.itsjava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Customer {
    private long id;
    private final String name;
    private final String email;
    private final String animal;
    private final Pet pet;
}
