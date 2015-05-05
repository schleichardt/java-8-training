package info.schleichardt.training.java8.lecture4;

import info.schleichardt.training.LabTask;
import io.sphere.sdk.models.Base;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletionException;

import static info.schleichardt.training.Placeholders.todo;
import static info.schleichardt.training.java8.lecture4.PZ_Optional.*;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class P1_Optional {
    public static class StreetData extends Base {
        private final String street;
        private final String number;

        public StreetData(final String street, final String number) {
            this.number = number;
            this.street = street;
        }

        public String getNumber() {
            return number;
        }

        public String getStreet() {
            return street;
        }
    }

    public static class Address extends Base {
        private final String city;
        private final StreetData streetData;

        public Address(final String city, final StreetData streetData) {
            this.city = city;
            this.streetData = streetData;
        }

        public String getCity() {
            return city;
        }

        public StreetData getStreetData() {
            return streetData;
        }
    }

    public static class Person extends Base {
        private final String firstName;
        private final String lastName;
        private final Address address;

        public Person(final String firstName, final String lastName, final Address address) {
            this.address = address;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Address getAddress() {
            return address;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }

    public static List<Person> PERSONS = asList(
            new Person("John", "Smith", new Address("Berlin", new StreetData(null, "12"))),
            new Person("Michael", "Müller", new Address(null, new StreetData("Parkstr.", "5"))),
            new Person(null, "Jackson", new Address("Berlin", new StreetData("Schumannstr.", null))),
            new Person("Laura", "", null)
    );

    //what happens?
    public static List<String> listOfStreetNames(final List<Person> persons) {
        return persons.stream().map(p -> p.getAddress().getStreetData().getStreet()).collect(toList());
    }

    //this sucks
    public static List<String> listOfStreetNamesCheckingNull(final List<Person> persons) {
        return persons.stream()
                .map(p -> {
                    String result = null;
                    final Address address = p.getAddress();
                    if (address != null) {
                        final StreetData streetData = address.getStreetData();
                        if (streetData != null) {
                            result = streetData.getStreet();//evil: reassigning the reference
                        }
                    }
                    return result;
                })
                .filter(value -> value != null) //additional filter step
                .collect(toList());
    }

    /*
    Since we posses the classes we could add null checks:
     */
    public static class AddressWithNullchecks extends Base {
        private final String city;
        private final StreetData streetData;

        public AddressWithNullchecks(final String city, final StreetData streetData) {
            this.city = Objects.requireNonNull(city);//throws NullPointerException if city is null => fail fast
            this.streetData = Objects.requireNonNull(streetData);
        }

        public String getCity() {
            return city;
        }

        public StreetData getStreetData() {
            return streetData;
        }
    }

    /*
    But often we do not posses data classes or a part and still need to deal with null.


      Optional to the rescue.
      https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
     */
    public static List<String> listOfStreetNamesWithOptional(final List<Person> persons) {
        return persons.stream()
                .map(p -> Optional.ofNullable(p)
                                .map(Person::getAddress)
                                .map(Address::getStreetData)
                                .map(StreetData::getStreet)
                )
                .filter(Optional::isPresent)//ok filter and map necessary, but code is not so clumsy with null checks
                .map(Optional::get)
                .collect(toList());
    }

    public static void classicUnwantedApproach() {
        final String value;
        final Optional<String> optional = getAnOptional();
        if (optional.isPresent()) {
            value = optional.get();
        } else {
            value = "default";
        }
    }

    public static void wantedApproach() {
        final Optional<String> optional = getAnOptional();
        final String value = optional.orElse("default");
    }

    public static void sideEffectsIfReallyNecessary() {
        final Optional<String> optional = getAnOptional();
        optional.ifPresent(value -> System.out.println("The value is present and is " + value));
    }

    //Exception without message
    @LabTask
    public static String throwNullPointerExceptionIfOptionalIsAbsent(final Optional<String> optional) {
        return todo();
    }

    @LabTask
    public static Optional<String> getLastName(final Optional<Person> optional) {
        return todo();
    }

    @LabTask
    public static Optional<Person> returnEmptyOptionalIfLastNameIsSmith(final Optional<Person> optional) {
        return todo();
    }

    public static String expensiveComputation() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new CompletionException(e);
        }
        return "slow";
    }

    @LabTask//rewrite to functional style
    public static String computeIfAbsent(final Optional<String> optional) {
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return expensiveComputation();
        }
    }

    @LabTask//rewrite to functional style
    public static Optional<String> unwrapp(final Optional<String> firstNameOption, final Optional<String> lastNameOption) {
        if (firstNameOption.isPresent() && lastNameOption.isPresent()) {
            final String fullName = firstNameOption.get() + " " + lastNameOption.get();
            return Optional.of(fullName);
        } else {
            return Optional.empty();
        }
    }
}
