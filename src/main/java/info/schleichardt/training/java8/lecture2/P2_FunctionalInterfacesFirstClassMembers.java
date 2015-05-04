package info.schleichardt.training.java8.lecture2;

import java.security.MessageDigest;

public class P2_FunctionalInterfacesFirstClassMembers {

    @FunctionalInterface
    public interface HashFunction {
        byte[] hash(final String input) throws Exception;

        //return type, works also in classes
        static HashFunction ofAlgorithm(final String algorithm) {
            return input -> MessageDigest.getInstance(algorithm).digest(input.getBytes());
        }
    }

    //member in a class
    public static final HashFunction hashFunctionClassMember = input -> MessageDigest.getInstance("SHA2").digest(input.getBytes());
    //field
    private final HashFunction hashFunctionAsField = input -> MessageDigest.getInstance("SHA2").digest(input.getBytes());

    //input parameter
    public static byte[] hashInput(final String input, final HashFunction function) throws Exception {
        return function.hash(input);
    }

    public static void demoForReturnType() throws Exception {
        final byte[] hash1 = HashFunction.ofAlgorithm("SHA1").hash("hello");
        final byte[] hash2 = hashInput("hello", HashFunction.ofAlgorithm("SHA1"));
    }

    //example method that is not at all related to the interface HashFunction
    //but still has the same signature of taking a String as input and returning a byte array
    //name is not important
    //little brother of duck typing
    public static byte[] md5(final String input) throws Exception {
        return MessageDigest.getInstance("MD5").digest(input.getBytes());
    }

    //method reference
    public static void demoForMethodReference() throws Exception {
        final HashFunction function = P2_FunctionalInterfacesFirstClassMembers::md5;//double colon for method reference
    }
}
