package info.schleichardt.training.java8.lecture3;

import info.schleichardt.training.LabTask;
import io.sphere.sdk.models.Base;

import java.util.*;
import java.util.stream.Collectors;

import static info.schleichardt.training.Placeholders.todo;
import static java.util.Arrays.asList;

public class Streams {
    //toString, hashCode and equals are implicitly implemented
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
        final Set<String> lastNames = new HashSet<>();//diamond operator is new in Java 7
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

    //it leaves out an intermediate operation
    @LabTask
    public static boolean anyMatchLastNameSmith(final List<Person> persons) {
        return todo();
    }

    @LabTask
    public static long countLastNameSmith(final List<Person> persons) {
        return todo();
    }

    @LabTask
    public static Optional<Person> queryOneForFirstNameLaura(final List<Person> persons) {
        return todo();
    }

    @LabTask
    public static Optional<Person> findPersonWithLongestLastName(final List<Person> persons) {
        return todo();
    }

    @LabTask
    public static List<Person> sortByLastNameAscThenFirstNameAsc(final List<Person> persons) {
        return todo();
    }

    @LabTask
    public static List<Person> sortByLastNameAscThenFirstNameAscButSkipFirstOneAndFetchOnly2(final List<Person> persons) {
        return todo();
    }

    @LabTask
    public static List<Integer> sortList(final List<Integer> list) {
        return todo();
    }

    //create a map with the following kind of entries: "John Smith" -> toString value of Person
    @LabTask
    public static Map<String, String> mapCreation(final List<Person> list) {
        return todo();
    }
}
