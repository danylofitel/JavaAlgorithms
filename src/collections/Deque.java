package collections;

public interface Deque<Item> extends Iterable<Item> {

    public boolean isEmpty();

    public int size();

    public void addFirst(Item item);

    public void addLast(Item item);

    public Item getFirst();

    public Item getLast();

    public Item removeFirst();

    public Item removeLast();
}
