package info.schleichardt.training.java8.lecture3;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}