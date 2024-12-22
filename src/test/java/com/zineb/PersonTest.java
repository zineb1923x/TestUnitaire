package com.zineb;

import org.junit.Test;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonTest {

    @Test
    public void testRemoveWithoutIterator() {
        Set<Person> people = new HashSet<>();
        people.add(Person.builder().firstName("Zineb").familyName("El Mouden").birthDate(LocalDate.of(1990, 5, 10)).address("123 Rue Exemple").build());
        people.add(Person.builder().firstName("Mohammed").familyName("Ziani").birthDate(LocalDate.of(1992, 4, 22)).address("456 Rue Exemple").build());
        people.add(Person.builder().firstName("Basma").familyName("El Harrat").birthDate(LocalDate.of(1995, 8, 30)).address("789 Rue Exemple").build());

        Set<Person> toRemove = new HashSet<>();
        for (Person person : people) {
            if (person.getFamilyName().equals("Ziani")) {
                toRemove.add(person);
            }
        }

        people.removeAll(toRemove);

        assertThat(people).hasSize(2)
                .doesNotContain(Person.builder().firstName("Mohammed").familyName("Ziani").birthDate(LocalDate.of(1992, 4, 22)).address("456 Rue Exemple").build())
                .contains(Person.builder().firstName("Zineb").familyName("El Mouden").birthDate(LocalDate.of(1990, 5, 10)).address("123 Rue Exemple").build(),
                        Person.builder().firstName("Basma").familyName("El Harrat").birthDate(LocalDate.of(1995, 8, 30)).address("789 Rue Exemple").build());
    }

    @Test
    public void testRemoveWithIterator() {
        Set<Person> people = new HashSet<>();
        people.add(Person.builder().firstName("Zineb").familyName("El Mouden").birthDate(LocalDate.of(1990, 5, 10)).address("123 Rue Exemple").build());
        people.add(Person.builder().firstName("Mohammed").familyName("Ziani").birthDate(LocalDate.of(1992, 4, 22)).address("456 Rue Exemple").build());
        people.add(Person.builder().firstName("Basma").familyName("El Harrat").birthDate(LocalDate.of(1995, 8, 30)).address("789 Rue Exemple").build());

        Iterator<Person> iterator = people.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person.getFamilyName().equals("Ziani")) {
                iterator.remove();
            }
        }

        assertThat(people).hasSize(2)
                .doesNotContain(Person.builder().firstName("Mohammed").familyName("Ziani").birthDate(LocalDate.of(1992, 4, 22)).address("456 Rue Exemple").build())
                .contains(Person.builder().firstName("Zineb").familyName("El Mouden").birthDate(LocalDate.of(1990, 5, 10)).address("123 Rue Exemple").build(),
                        Person.builder().firstName("Basma").familyName("El Harrat").birthDate(LocalDate.of(1995, 8, 30)).address("789 Rue Exemple").build());
    }
}
