package edu.estu;

import java.util.*;

/**
 * A generic type declaration is compiled once and for all, and turned into a single class file,
 * just like an ordinary class or interface declaration.
 * <p>
 * Type parameters are analogous to the ordinary parameters used in methods or constructors.
 * Much like a method has formal value parameters that describe the kinds of values it operates on,
 * a generic declaration has formal type parameters.
 * <p>
 * When a method is invoked, actual arguments are substituted for the formal parameters, and the method body is evaluated.
 * When a generic declaration is invoked, the actual type arguments are substituted for the formal type parameters.
 */
public class Pair<F, S> {
    private F first; //first member of pair
    private S second; //second member of pair

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    static class MyClass implements Comparable<MyClass> {
        int age;
        String name;

        public MyClass(int age, String name) {
            this.age = age;
            this.name = name;

        }

        @Override
        public boolean equals(Object o) {
            System.out.println("equals is called");
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyClass myClass = (MyClass) o;
            return age == myClass.age &&
                    Objects.equals(name, myClass.name);
        }

        @Override
        public int hashCode() {
            System.out.println("hash code is called");
            return Objects.hash(age, name);
        }

        @Override
        public int compareTo(MyClass o) {
            System.out.println("compare to is called");
            return this.age - o.age;
        }

        @Override
        public String toString() {
            return "MyClass{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        Pair<Integer, String> p1 = new Pair<>(1, "h");
        Pair<Double, List<String>> p2 = new Pair<>(5D, List.of("a", "b", "c"));
        Pair<MyClass, Float> p3 = new Pair<>(new MyClass(40, "ahmet"), 5F);
        Pair<String, Float> p4 = new Pair<>("hello", 5F);

        System.out.println("avg = " + average(List.of(1, 2, 3, 4, 6)));
        System.out.println("avg = " + average(List.of(1D, 2D, 3D, 4D, 6D)));
        System.out.println("avg = " + average(List.of(1.2F, 2.3F, 3.6F, 4F, 5F, 6F)));

        System.out.println("mean = " + Pair.mean(List.of(1, 2, 3, 4, 6)));
        System.out.println("mean = " + Pair.mean(List.of(1D, 2D, 3D, 4D, 6D)));
        System.out.println("mean = " + Pair.mean(List.of(1.2F, 2.3F, 3.6F, 4F, 5F, 6F)));

        MyClass burcu = new MyClass(20, "helin");
        MyClass helin = new MyClass(20, "helin");
        // if(helin==burcu) == checks reference
        // To compare object references, you use == and != , while equals() is used to compare the values
        if (helin==burcu) {
            System.out.println("they are the same");
        }

        Set<MyClass> set = new TreeSet<>();
        set.add(new MyClass(40, "ahmet"));
        set.add(new MyClass(40, "ahmet"));
        set.add(new MyClass(20, "burcu"));
        set.add(new MyClass(30, "helin"));

        System.out.println("The size of the set is : " + set.size());

        System.out.println(set);

        Map<MyClass, Integer> map = new TreeMap<>();

        map.put(new MyClass(40, "ahmet"), 0);
        map.put(new MyClass(40, "ahmet"), 1);
        map.put(new MyClass(20, "burcu"), 2);
        map.put(new MyClass(30, "helin"), 3);

        System.out.println("The size of the map is :" + map.size());


    }

    /**
     * So what is the supertype of all kinds of lists? It's written List<?> (pronounced "lis of unknown"),
     * that is, a lise whose element type matches anything. It's called a wildcard type for obvious reasons.
     */
    static double average(List<? extends Number> list) {
        double sum = 0.0;

        for (Number n : list)
            sum += n.doubleValue();

        return sum / list.size();

    }

    /**
     * When you should use wildcard or generic methods.
     * One question that arises is: when should I use <p>generic methods<p/>, and when should I use <p>wildcard types<p/>?
     * <p>
     * The return type doesn't depend on the type parameter, nor does any other argument to the method
     * (in this case, there simply is only one argument). This tells us that the type argument is being used for polymorphism;
     * its only effect is to allow a variety of actual argument types to be used at different invocation sites.
     * If that is the case, one should use wildcards. Wildcards are designed to support flexible subtyping,
     * which is what we're trying to express here.
     * <p>
     * Generic methods allow type parameters to be used to express dependencies among the types of one or more arguments to a method and/or its return type.
     * If there isn't such a dependency, a generic method should not be used.
     * <p>
     * It is possible to use both generic methods and wildcards in tandem. Here is the method Collections.copy():
     */
    static <U extends Number> double mean(List<U> list) {
        double sum = 0.0;

        for (Number n : list)
            sum += n.doubleValue();

        return sum / list.size();
    }
}