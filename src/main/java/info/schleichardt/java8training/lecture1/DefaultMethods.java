package info.schleichardt.java8training.lecture1;

public class DefaultMethods {
    /*
    Since Java 8, interfaces can have methods with implementations.

    It is controversal if it should be used for multiple inheritance,
    but you can use it for convenience methods:
    */

    public interface Container<T> {
        long length();

        default boolean isEmpty() {
            return length() > 0;//using other method in interface
        }//could be overridden for more effective check
    }

    /*
    As a result interfaces and abstract classes are kind of mixed up.
    In interface you don't have fields and cannot override the methods from Object
    like toString(). In abstract classes, methods can final, in interfaces every method can be overridden.

    It is also possible to provide static methods, for example as factories:
     */
    public interface Identifiable {
        String getId();

        static Identifiable of(final String id) {//return type is interfache
            return new IdentifiableImpl(id);//usage of hidden impl class
        }
    }

    private static class IdentifiableImpl implements Identifiable {
        private final String id;

        public IdentifiableImpl(final String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }
    }
}
