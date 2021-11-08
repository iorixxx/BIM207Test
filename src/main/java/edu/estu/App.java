package edu.estu;

public class App {

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        System.out.println(max(-1.3F, 1.3F));
        System.out.println(max(new Pair.MyClass(40, "ahmet"), new Pair.MyClass(20, "yagmur")));
    }

    // this does not work: public static <T> T max(T x, T y) { return x > y ? x : y;}
    public static <T extends Comparable<T>> T max(T x, T y) {
        if (x.compareTo(y) > 0)
            return x;
        else return y;
    }
}
