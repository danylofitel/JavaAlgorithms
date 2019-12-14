package collections;

public class BagTest {

    public static void main(String[] args) {
        Bag<String> bag = new BagOnArrays<>();
        final int length = 500;

        System.out.println("Is empty = " + bag.isEmpty());
        System.out.println("Size = " + bag.size());

        System.out.println("Adding elements");
        for (int i = 0; i < length; ++i) {
            bag.add(String.valueOf(i));
        }

        System.out.println("Is empty = " + bag.isEmpty());
        System.out.println("Size = " + bag.size());

        System.out.println("Iterating through bag");
        for (String s : bag) {
            System.out.println(s);
        }
    }
}
