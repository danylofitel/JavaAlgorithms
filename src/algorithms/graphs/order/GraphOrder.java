package algorithms.graphs.order;

public interface GraphOrder {

    Iterable<Integer> postOrder();

    Iterable<Integer> reversePostOrder();

    int orderCount();
}
