package algorithms.sets;

import java.util.Set;

public class SelectSubsetsTest {

    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5};
        SelectSubsets<Integer> selector = new SelectSubsets<>();
        Set<Set<Integer>> subsets = selector.selectSubsetsOfSize(array, 0, 5);
        for (Set<Integer> subset : subsets) {
            for (Integer i : subset) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
        System.out.println("Size = " + subsets.size());
    }
}
