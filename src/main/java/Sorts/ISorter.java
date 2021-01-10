package Sorts;

import java.util.Comparator;

public interface ISorter<T> {
    void sort(Comparator<T> comparator,T[] array,int lastIndex);
}
