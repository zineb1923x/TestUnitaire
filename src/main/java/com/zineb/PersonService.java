package com.zineb;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PersonService {

    public static List<Person> findByAddress(String address) {
        List<Person> database = Arrays.asList(
                Person.builder().firstName("Zineb").familyName("El Mouden").birthDate(LocalDate.of(1995, 1, 10))
                        .address("123 Rue A").build(),
                Person.builder().firstName("Basma").familyName("El Harrat").birthDate(LocalDate.of(1990, 4, 22))
                        .address("456 Rue B").build(),
                Person.builder().firstName("Mohammed").familyName("Ziani").birthDate(LocalDate.of(1987, 9, 15))
                        .address("123 Rue A").build()
        );

        Predicate<Person> hasAddress = person -> person.getAddress().equals(address);

        return database.stream()
                .filter(hasAddress)
                .collect(Collectors.toList());
    }

    public static List<Person> findAdults() {
        List<Person> database = Arrays.asList(
                Person.builder().firstName("Zineb").familyName("El Mouden").birthDate(LocalDate.of(1995, 1, 10))
                        .address("123 Rue A").build(),
                Person.builder().firstName("Basma").familyName("El Harrat").birthDate(LocalDate.of(1990, 4, 22))
                        .address("456 Rue B").build(),
                Person.builder().firstName("Ahmed").familyName("Noureddine").birthDate(LocalDate.of(2007, 7, 20))
                        .address("123 Rue A").build()
        );

        Predicate<Person> isAdult = person -> person.getAge() >= 18;

        return database.stream()
                .filter(isAdult)
                .collect(Collectors.toList());
    }

    public static Set<Person> removeNoureddineUsingIterator() {
        Set<Person> people = new HashSet<>();
        people.add(Person.builder().firstName("Zineb").familyName("El Mouden").build());
        people.add(Person.builder().firstName("Ahmed").familyName("Noureddine").build());
        people.add(Person.builder().firstName("Basma").familyName("El Harrat").build());

        Iterator<Person> iterator = people.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person.getFamilyName().equals("Noureddine")) {
                iterator.remove();
            }
        }

        return people;
    }
}
