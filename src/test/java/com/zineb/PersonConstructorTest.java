package com.zineb;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import org.junit.Test;

public class PersonConstructorTest {

    @Test
    public void testPersonAttributes() {
        Person person = new Person();
        person.setFirstName("Zineb");
        person.setFamilyName("El Mouden");
        person.setAddress("Rue 41");
        person.setBirthDate(LocalDate.of(2003, 4, 13));

        assertThat(person).isNotNull();
        assertThat(person.getFirstName()).isEqualTo("Zineb");
        assertThat(person.getFamilyName()).isEqualTo("El Mouden");
        assertThat(person.getAddress()).isEqualTo("Rue 41");
        assertThat(person.getBirthDate()).isEqualTo(LocalDate.of(2003, 4, 13));
    }
}
