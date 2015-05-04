package info.schleichardt.training.java8.lecture3;

import info.schleichardt.training.LabTask;
import info.schleichardt.training.Placeholders;
import io.sphere.sdk.models.Base;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static info.schleichardt.training.Placeholders.todo;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.*;

public class Streams {
    public static class Person extends Base {
        private final String firstName;
        private final String lastName;

        public Person(final String firstName, final String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }

    public static List<Person> PERSONS = asList(
            new Person("John", "Smith"),
            new Person("Michael", "Müller"),
            new Person(null, "Jackson"),
            new Person("Laura", "")
    );

    /*
    classical way of getting a set of the last names of some persons looks like this:
     */
    public static Set<String> lastNameSet(final List<Person> persons) {
        final Set<String> lastNames = new HashSet<>();//diamond operator is new in Java 8
        for (final Person person : persons) {
            lastNames.add(person.getLastName());
        }
        return lastNames;
    }

    /*
     * This is okay, but a lot of boilerplate and if you want to add additional functionality (sorting, filtering, counting),
     * you need to mix the logic to get each element. And you do stuff eagerly.
     *
     * Java 8 provides a facility to process data in a more functional way:
     * https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
     */

    public static Set<String> lastNameSetViaStream(final List<Person> persons) {
        return persons.stream()//stream is default method on collection
                .map(person -> person.getLastName())//intermediate operation
                .collect(Collectors.toSet());//terminal operation
    }

    @LabTask
    public static List<String> lastNameListViaStream(final List<Person> persons) {
        return todo();
    }

    @LabTask
    public static List<String> FirstNameListViaStream(final List<Person> persons) {
        return todo();
    }

    @LabTask
    public static List<String> FirstNameListViaStreamFilterOutNulls(final List<Person> persons) {
        return todo();
    }

    @LabTask
    public static List<Person> personsWithCompleteName(final List<Person> persons) {
        return todo();
    }


}
