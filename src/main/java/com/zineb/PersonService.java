package com.zineb;

import java.time.LocalDate;
import java.util.*;

import java.util.stream.Collectors;

public class PersonService {

    private static final List<Person> DATABASE = Arrays.asList(
            Person.builder().firstName("Zineb").familyName("El Mouden").birthDate(LocalDate.of(1995, 1, 10))
                    .address("123 Rue A").build(),
            Person.builder().firstName("Basma").familyName("El Harrat").birthDate(LocalDate.of(1990, 4, 22))
                    .address("456 Rue B").build(),
            Person.builder().firstName("Ahmed").familyName("Noureddine").birthDate(LocalDate.of(2007, 7, 20))
                    .address("123 Rue A").build()
    );

    public static List<Person> findByAddress(String address) {
        return DATABASE.stream()
                .filter(person -> person.getAddress().equals(address))
                .collect(Collectors.toList());
    }

    public static List<Person> findAdult() {
        return DATABASE.stream()
                .filter(person -> person.getAge() >= 18)
                .collect(Collectors.toList());
    }

    public static Set<Person> removeNoureddineWithoutIterator() {
        Set<Person> people = new HashSet<>();
        people.add(Person.builder().firstName("abdeljabbar").familyName("alica").build());
        people.add(Person.builder().firstName("abdellah").familyName("Bob").build());
        people.add(Person.builder().firstName("zouani").familyName("Charlie").build());

        return people.stream()
                .filter(person -> !person.getFamilyName().equals("Bob"))
                .collect(Collectors.toSet());
    }

    public static Set<Person> removeNoureddineUsingIterator() {
        Set<Person> people = new HashSet<>();
        people.add(Person.builder().firstName("Zineb").familyName("El Mouden").build());
        people.add(Person.builder().firstName("Ahmed").familyName("Noureddine").build());
        people.add(Person.builder().firstName("Basma").familyName("El Harrat").build());

        Iterator<Person> iterator = people.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if ("Noureddine".equals(person.getFamilyName())) {
                iterator.remove();
            }
        }

        return people;
    }
}
