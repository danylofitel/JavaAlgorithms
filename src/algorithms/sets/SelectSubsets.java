package algorithms.sets;

import java.util.HashSet;
import java.util.Set;

public class SelectSubsets<Type> {

    private Type[] from;
    private Set<Set<Type>> to;
    private int minSize;
    private int maxSize;

    public Set<Set<Type>> selectSubsetsOfSize(Type[] set, int minSize, int maxSize) {
        this.from = set;
        this.to = new HashSet<>();
        this.minSize = minSize;
        this.maxSize = maxSize;
        selectSubsets(new HashSet<Type>(), 0);
        return to;
    }

    private void selectSubsets(Set<Type> current, int currentIndex) {
        if (current.size() >= minSize) {
            Set<Type> withoutCurrent = new HashSet<>();
            withoutCurrent.addAll(current);
            to.add(withoutCurrent);
        }

        if (currentIndex + 1 < from.length) {
            selectSubsets(current, currentIndex + 1);
        }

        if (current.size() + 1 <= maxSize) {
            current.add(from[currentIndex]);

            if (current.size() >= minSize) {
                Set<Type> withCurrent = new HashSet<>();
                withCurrent.addAll(current);
                to.add(withCurrent);
            }

            if (currentIndex + 1 < from.length) {
                selectSubsets(current, currentIndex + 1);
            }
            
            current.remove(from[currentIndex]);
        }
    }
}
