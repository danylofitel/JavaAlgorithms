package collections;

public interface Queue<Item> extends Iterable<Item> {

    public boolean isEmpty();

    public int size();

    public Item front();

    public Item back();

    public void pushBack(Item item);

    public Item popFront();
}
