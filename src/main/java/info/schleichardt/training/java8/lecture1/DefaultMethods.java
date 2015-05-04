package info.schleichardt.training.java8.lecture1;

import info.schleichardt.training.LabTask;

import java.util.Iterator;
import java.util.List;

public class DefaultMethods {
    /*
    Since Java 8, interfaces can have methods with implementations.

    It is controversal if it should be used for multiple inheritance,
    but you can use it for convenience methods:
    */

    public interface Container<T> extends Iterable<T> {
        int length();

        default boolean isEmpty() {
            return length() > 0;//using other method in interface
        }//could be overridden for more effective check
    }

    public static class ContainerImpl1<T> implements Container<T> {
        private final List<T> list;

        public ContainerImpl1(final List<T> list) {
            this.list = list;
        }

        @Override
        public int length() {
            return list.size();
        }

        @Override
        public Iterator<T> iterator() {
            return list.iterator();
        }
    }

    public static class ContainerImpl2<T> implements Container<T> {
        private final List<T> list;

        public ContainerImpl2(final List<T> list) {
            this.list = list;
        }

        @Override
        public int length() {
            return list.size();
        }

        @Override
        public boolean isEmpty() {//overridden
            return list.isEmpty();
        }

        @Override
        public Iterator<T> iterator() {
            return list.iterator();
        }
    }

    /*
     * Create a default method for {@link Container} which accepts a visitor, for example the PrintVisitor and let the visitor see every element.
     *
     * {@code void accept(final Visitor<T> visitor)}
     *
     */
    @LabTask
    public interface Visitor<T> {
        void visit(final T element);
    }

    public static class PrintVisitor<T> implements Visitor<T> {
        @Override
        public void visit(final T element) {
            System.out.println(element);
        }
    }

    /*
    As a result interfaces and abstract classes are kind of mixed up.
    In interface you don't have fields and cannot override the methods from Object
    like toString(). In abstract classes, methods can final, in interfaces every method can be overridden.

    It is also possible to provide static methods, for example as factories:
     */
    public interface Identifiable {
        String getId();

        static Identifiable of(final String id) {//return type is interface
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
