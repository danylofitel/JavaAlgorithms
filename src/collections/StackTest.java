package collections;

public class StackTest {

    public static void main(String[] args) {
        Stack<String> stack = new StackOnArrays<>();
        final int length = 500;

        System.out.println("Is empty = " + stack.isEmpty());
        System.out.println("Size = " + stack.size());

        System.out.println("Adding elements");
        for (int i = 0; i < length; ++i) {
            stack.push(String.valueOf(i));
            System.out.println(stack.top() + " inserted");
        }

        System.out.println("Is empty = " + stack.isEmpty());
        System.out.println("Size = " + stack.size());

        System.out.println("Iterating through stack");
        for (String s : stack) {
            System.out.println(s);
        }

        System.out.println("Removing elements");
        for (int i = 0; i < length; ++i) {
            System.out.println(stack.pop() + " removed");
        }

        System.out.println("Is empty = " + stack.isEmpty());
        System.out.println("Size = " + stack.size());

        for (String s : stack) {
            System.out.println(s);
        }

        for (int i = 0; i < 100; ++i) {
            stack.push(String.valueOf(i));
            stack.pop();
        }

        System.out.println("Is empty = " + stack.isEmpty());
        System.out.println("Size = " + stack.size());

        for (String s : stack) {
            System.out.println(s);
        }
    }
}
