package collections;

public interface Stack<Item> extends Iterable<Item> {

    public boolean isEmpty();

    public int size();

    public void push(Item item);

    public Item top();

    public Item pop();
}
