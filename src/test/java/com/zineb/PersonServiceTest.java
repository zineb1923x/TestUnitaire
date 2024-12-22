package com.zineb;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.Test;

public class PersonServiceTest {

    @Test
    public void testFindByAddress() {
        List<Person> peopleLivingIn123RueA = PersonService.findByAddress("123 Rue A");
        List<Person> expectedPersonsLivingIn123RueA = Arrays.asList(
                Person.builder().firstName("Zineb").familyName("El Mouden")
                        .birthDate(LocalDate.of(1995, 1, 10))
                        .address("123 Rue A").build(),
                Person.builder().firstName("Mohammed").familyName("Ziani")
                        .birthDate(LocalDate.of(1987, 9, 15))
                        .address("123 Rue A").build()
        );

        // Vérification avec AssertJ
        assertThat(peopleLivingIn123RueA).containsExactlyInAnyOrderElementsOf(expectedPersonsLivingIn123RueA);
    }

    @Test
    public void testFindAdult() {
        List<Person> adults = PersonService.findAdult();

        // Liste attendue des adultes (18 ans ou plus)
        List<Person> expectedAdultPersons = Arrays.asList(
                Person.builder().firstName("Zineb").familyName("El Mouden")
                        .birthDate(LocalDate.of(1995, 1, 10))
                        .address("123 Rue A").build(),
                Person.builder().firstName("Basma").familyName("El Harrat")
                        .birthDate(LocalDate.of(1990, 4, 22))
                        .address("456 Rue B").build()
        );

        // Vérification avec AssertJ
        assertThat(adults).containsExactlyInAnyOrderElementsOf(expectedAdultPersons);
    }

    @Test
    public void testSortPerson() {
        List<Person> people = new ArrayList<>();
        people.add(Person.builder().firstName("Hajar").familyName("Oulabasse").build());
        people.add(Person.builder().firstName("Mohammed").familyName("Ziani").build());
        people.add(Person.builder().firstName("Zineb").familyName("El Mouden").build());
        people.add(Person.builder().firstName("Basma").familyName("El Harrat").build());

        // Tri de la liste de personnes
        Collections.sort(people);

        assertThat(people.get(0))
                .isEqualTo(Person.builder().firstName("Zineb").familyName("El Mouden").build());
        assertThat(people.get(1))
                .isEqualTo(Person.builder().firstName("Basma").familyName("El Harrat").build());
        assertThat(people.get(2))
                .isEqualTo(Person.builder().firstName("Hajar").familyName("Oulabasse").build());
        assertThat(people.get(3))
                .isEqualTo(Person.builder().firstName("Mohammed").familyName("Ziani").build());
    }

    @Test
    public void testRemoveNoureddineWithoutIterator() {
        assertThatThrownBy(() -> PersonService.removeNoureddineWithoutIterator())
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    public void removeNoureddineUsingIterator() {
        Set<Person> people = new HashSet<>();
        people.add(Person.builder().firstName("Zineb").familyName("El Mouden").build());
        people.add(Person.builder().firstName("Basma").familyName("El Harrat").build());
        people.add(Person.builder().firstName("Hajar").familyName("Oulabasse").build());
        people.add(Person.builder().firstName("Mohammed").familyName("Ziani").build());

        Set<Person> peopleWithoutNoureddine = PersonService.removeNoureddineUsingIterator();

        assertThat(peopleWithoutNoureddine).containsExactlyInAnyOrderElementsOf(people);
    }
}
