package info.schleichardt.training.java8.lecture4;

import org.junit.Test;
import info.schleichardt.training.java8.lecture4.P1_Optional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class P1_OptionalTest {
    public static List<P1_Optional.Person> PERSONS = P1_Optional.PERSONS;
    public static Optional<String> filled = Optional.of("hello");
    public static Optional<String> empty = Optional.empty();
    public static Optional<P1_Optional.Person> personOption = Optional.of(new P1_Optional.Person("John", "Smith", new P1_Optional.Address("Berlin", new P1_Optional.StreetData(null, "12"))));
    public static Optional<P1_Optional.Person> personOption2 = Optional.of(new P1_Optional.Person("John", "X", new P1_Optional.Address("Berlin", new P1_Optional.StreetData(null, "12"))));

    @Test
    public void listOfStreetNames() throws Exception {
        assertThatThrownBy(() -> P1_Optional.listOfStreetNames(PERSONS))
        .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void listOfStreetNamesCheckingNull() throws Exception {
        assertThat(P1_Optional.listOfStreetNamesCheckingNull(PERSONS)).containsExactly("Parkstr.", "Schumannstr.");
    }

    @Test
    public void listOfStreetNamesWithOptional() throws Exception {
        assertThat(P1_Optional.listOfStreetNamesWithOptional(PERSONS)).containsExactly("Parkstr.", "Schumannstr.");
    }

    @Test
    public void throwNullPointerExceptionIfOptionalIsAbsent() throws Exception {
        assertThat(P1_Optional.throwNullPointerExceptionIfOptionalIsAbsent(filled)).isEqualTo("hello");
        assertThatThrownBy(() -> P1_Optional.throwNullPointerExceptionIfOptionalIsAbsent(empty)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void getLastName() throws Exception {
        assertThat(P1_Optional.getLastName(personOption)).contains("Smith");
    }

    @Test
    public void returnEmptyOptionalIfLastNameIsSmith() throws Exception {
        assertThat(P1_Optional.returnEmptyOptionalIfLastNameIsSmith(personOption)).isEmpty();
        assertThat(P1_Optional.returnEmptyOptionalIfLastNameIsSmith(personOption2)).isEqualTo(personOption2);
    }

    @Test
    public void computeIfAbsent() throws Exception {
        assertThat(P1_Optional.computeIfAbsent(filled)).isEqualTo(filled.get());
        assertThat(P1_Optional.computeIfAbsent(empty)).isEqualTo("slow");
    }

    @Test
    public void unwrap() throws Exception {
        assertThat(P1_Optional.unwrapp(Optional.of("first name"), Optional.of("last name"))).isEqualTo(Optional.of("first name last name"));
        assertThat(P1_Optional.unwrapp(Optional.of("first name"), empty)).isEmpty();
        assertThat(P1_Optional.unwrapp(empty, Optional.of("first name"))).isEmpty();
        assertThat(P1_Optional.unwrapp(empty, empty)).isEmpty();
    }
}