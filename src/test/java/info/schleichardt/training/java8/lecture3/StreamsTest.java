package info.schleichardt.training.java8.lecture3;

import org.junit.Test;

import java.util.*;

import static info.schleichardt.training.java8.lecture3.Streams.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class StreamsTest {

    private static final List<String> firstNamesList = asList("John", "Michael", null, "Laura");
    private static final List<String> firstNamesListWithoutNull = asList("John", "Michael", "Laura");
    private static final List<String> lastNamesList = asList("Smith", "Müller", "Jackson", "");
    private static final Set<String> lastNamesSet = new HashSet<>(lastNamesList);

    @Test
    public void lastNameSet() throws Exception {
        assertThat(Streams.lastNameSet(PERSONS)).isEqualTo(lastNamesSet);
    }

    @Test
    public void lastNameSetViaStream() throws Exception {
        assertThat(Streams.lastNameSetViaStream(PERSONS)).isEqualTo(lastNamesSet);
    }

    @Test
    public void lastNameListViaStream() throws Exception {
        assertThat(Streams.lastNameListViaStream(PERSONS)).isEqualTo(lastNamesList);
    }

    @Test
    public void FirstNameListViaStream() throws Exception {
        assertThat(Streams.FirstNameListViaStream(PERSONS)).isEqualTo(firstNamesList);
    }

    @Test
    public void FirstNameListViaStreamFilterOutNulls() throws Exception {
        assertThat(Streams.FirstNameListViaStreamFilterOutNulls(PERSONS)).isEqualTo(firstNamesListWithoutNull);
    }

    @Test
    public void personsWithCompleteName() throws Exception {
        assertThat(Streams.personsWithCompleteName(PERSONS)).containsExactly(PERSONS.get(0), PERSONS.get(1));
    }

    @Test
    public void anyMatchLastNameSmith() throws Exception {
        assertThat(Streams.anyMatchLastNameSmith(PERSONS)).isTrue();
        assertThat(Streams.anyMatchLastNameSmith(Collections.<Person>emptyList())).isFalse();
    }

    @Test
    public void countLastNameSmith() throws Exception {
        assertThat(Streams.countLastNameSmith(PERSONS)).isEqualTo(1);
        assertThat(Streams.countLastNameSmith(Collections.<Person>emptyList())).isEqualTo(0);
    }

    @Test
    public void queryOneForFirstNameLaura() throws Exception {
        assertThat(Streams.queryOneForFirstNameLaura(PERSONS)).isEqualTo(Optional.of(PERSONS.get(3)));
        assertThat(Streams.queryOneForFirstNameLaura(Collections.<Person>emptyList())).isEqualTo(Optional.empty());
    }

    @Test
    public void findPersonWithLongestLastName() throws Exception {
        assertThat(Streams.findPersonWithLongestLastName(PERSONS)).isEqualTo(Optional.of(PERSONS.get(2)));
        assertThat(Streams.findPersonWithLongestLastName(Collections.<Person>emptyList())).isEqualTo(Optional.empty());
    }

    @Test
    public void sortByLastNameAscThenFirstNameAsc() throws Exception {
        assertThat(Streams.sortByLastNameAscThenFirstNameAsc(PERSONS)).containsExactly(PERSONS.get(3), PERSONS.get(2), PERSONS.get(1), PERSONS.get(0));
        final List<Person> persons = asList(new Person("B", "A"), new Person("A", "B"), new Person("A", "A"), new Person("B", "B"));
        assertThat(Streams.sortByLastNameAscThenFirstNameAsc(persons)).containsExactly(new Person("A", "A"), new Person("B", "A"), new Person("A", "B"), new Person("B", "B"));
    }

    @Test
    public void sortByLastNameAscThenFirstNameAscButSkipFirstOneAndFetchOnly2() throws Exception {
        assertThat(Streams.sortByLastNameAscThenFirstNameAscButSkipFirstOneAndFetchOnly2(PERSONS)).containsExactly(PERSONS.get(2), PERSONS.get(1));
        final List<Person> persons = asList(new Person("B", "A"), new Person("A", "B"), new Person("A", "A"), new Person("B", "B"));
        assertThat(Streams.sortByLastNameAscThenFirstNameAscButSkipFirstOneAndFetchOnly2(persons)).containsExactly(new Person("B", "A"), new Person("A", "B"));
    }

    @Test
    public void sortList() throws Exception {
        assertThat(Streams.sortList(asList(3, 8, 6, 1))).containsExactly(1, 3, 6, 8);
    }

    @Test
    public void mapCreation() throws Exception {
        final Map<String, String> expected = new HashMap<>();
        expected.put("John Smith", "Streams.Person[firstName=John,lastName=Smith]");
        expected.put("null Jackson", "Streams.Person[firstName=<null>,lastName=Jackson]");
        expected.put("Michael Müller", "Streams.Person[firstName=Michael,lastName=Müller]");
        expected.put("Laura ", "Streams.Person[firstName=Laura,lastName=]");

        assertThat(Streams.mapCreation(PERSONS)).isEqualTo(expected);
    }
}