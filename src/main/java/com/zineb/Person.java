package com.zineb;

import java.time.LocalDate;
import java.util.Objects;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class Person implements Comparable<Person> {
    private String firstName;
    private String familyName;
    private LocalDate birthDate;
    private String address;

    public Person(String firstName, String familyName, LocalDate birthDate, String address) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.birthDate = birthDate;
        this.address = address;
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    @Override
    public int compareTo(Person other) {
        int familyNameComparison = this.familyName.compareTo(other.familyName);
        if (familyNameComparison != 0) {
            return familyNameComparison;
        }
        return this.firstName.compareTo(other.firstName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
               Objects.equals(familyName, person.familyName) &&
               Objects.equals(birthDate, person.birthDate) &&
               Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, familyName, birthDate, address);
    }
}
