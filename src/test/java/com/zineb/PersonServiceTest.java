package com.zineb;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PersonServiceTest {

    public static List<Person> filterByAddress(String address) {
        List<Person> mockPersonsDatabase = Arrays.asList(
                Person.builder().firstName("Zineb").familyName("El Mouden").birthDate(LocalDate.of(1995, 1, 10))
                        .address("123 Rue A").build(),
                Person.builder().firstName("Basma").familyName("El Harrat").birthDate(LocalDate.of(1990, 4, 22))
                        .address("456 Rue B").build(),
                Person.builder().firstName("Mohammed").familyName("Ziani").birthDate(LocalDate.of(1987, 9, 15))
                        .address("123 Rue A").build()
        );

        Predicate<Person> hasAddress = person -> person.getAddress().equals(address);

        return mockPersonsDatabase.stream()
                .filter(hasAddress)
                .collect(Collectors.toList());
    }

    public static List<Person> filterAdults() {
        List<Person> mockPersonsDatabase = Arrays.asList(
                Person.builder().firstName("Zineb").familyName("El Mouden").birthDate(LocalDate.of(1995, 1, 10))
                        .address("123 Rue A").build(),
                Person.builder().firstName("Basma").familyName("El Harrat").birthDate(LocalDate.of(1990, 4, 22))
                        .address("456 Rue B").build(),
                Person.builder().firstName("Ahmed").familyName("Noureddine").birthDate(LocalDate.of(2007, 7, 20))
                        .address("123 Rue A").build()
        );

        Predicate<Person> isAdult = person -> person.getAge() >= 18;

        return mockPersonsDatabase.stream()
                .filter(isAdult)
                .collect(Collectors.toList());
    }

    public static Set<Person> removeSpecificPersonWithoutIterator() {
        Set<Person> people = new HashSet<>();
        people.add(Person.builder().firstName("Yassine").familyName("Sebbahi").build());
        people.add(Person.builder().firstName("Ahmed").familyName("Noureddine").build());
        people.add(Person.builder().firstName("Basma").familyName("El Harrat").build());

        try {
            for (Person person : people) {
                if (person.getFamilyName().equals("Noureddine")) {
                    people.remove(person);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Erreur : Suppression sans iterator non sécurisée !");
        }

        return people;
    }

    public static Set<Person> removeSpecificPersonUsingIterator() {
        Set<Person> people = new HashSet<>();
        people.add(Person.builder().firstName("Yassine").familyName("Sebbahi").build());
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