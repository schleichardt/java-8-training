package info.schleichardt.training.java8.lecture2;

import java.security.MessageDigest;
import java.util.List;

import static java.util.Arrays.asList;

public class P1_FunctionalInterfaces {
    /*
     see https://docs.oracle.com/javase/8/docs/api/java/lang/FunctionalInterface.html

     => functional interfaces have one abstract method, default methods are not abstract

     example:
     */

    @FunctionalInterface//compile checks if requirement of one abstract method is met
    public interface HashFunction {
        byte[] hash(final String input) throws Exception;
    }

    @FunctionalInterface
    public interface Whatever<V> {
        V aMethod();//one abstract method

        //impl method
        default List<V> anotherMethod() {
            return asList(aMethod(), aMethod());
        }
    }

    //two abstract methods
    public interface NotAFunctionalInterface<V> {
        V aMethod();
        List<V> anotherMethod();
    }

    /*
    Functional interfaces can be implemented and used the classic way:
     */
    class Md5HashFunction implements HashFunction {
        @Override
        public byte[] hash(final String input)  throws Exception {
            return MessageDigest.getInstance("MD5").digest(input.getBytes());
        }
    }

    class ClassicMd5HashFunctionUser {
        void demo() throws Exception {
            final HashFunction hashFunction = new Md5HashFunction();
            final byte[] bytes = hashFunction.hash("hello");
        }
    }

    class AnonymousClassHashFunctionUser {
        void demo() throws Exception {
            final HashFunction hashFunction = new HashFunction() {
                @Override
                public byte[] hash(final String input) throws Exception {
                    return MessageDigest.getInstance("SHA1").digest(input.getBytes());
                }
            };
            final byte[] bytes = hashFunction.hash("hello");
        }
    }

    /** Java 8 enables to use lambda functions: */

    static class LambdaHashFunctionUser {
        void demo() throws Exception {
            final HashFunction hashFunction = (final String input) -> MessageDigest.getInstance("SHA2").digest(input.getBytes());
            final byte[] bytes = hashFunction.hash("hello");
        }
    }

    static class UseInferenceAndConvention {
        void demo() throws Exception {
            final HashFunction hashFunction = input -> MessageDigest.getInstance("SHA12").digest(input.getBytes());
            final byte[] bytes = hashFunction.hash("hello");
        }
    }

    static class Blocks {
        void demo() throws Exception {
            final HashFunction hashFunction = input -> {//parenthesis
                final MessageDigest sha12 = MessageDigest.getInstance("SHA12");
                return sha12.digest(input.getBytes());//return statement
            };
            final byte[] bytes = hashFunction.hash("hello");
        }
    }

    static class MultipleParameters {
        interface Op {
            long calculate(final long first, final long second);
        }

        void demo() throws Exception {
            final Op addition = (left, right) -> left + right;
        }
    }
}