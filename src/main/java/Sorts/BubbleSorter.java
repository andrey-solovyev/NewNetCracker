package Sorts;

import java.util.Comparator;

public class BubbleSorter<T> implements ISorter<T> {
    @Override
    public void sort(Comparator<T> comparator, T[] array, int lastIndex) {
        bubbleSortI(comparator,array,lastIndex);
    }
    private void bubbleSortI(Comparator<T> comparator, T[] array,int lastIndex){
        boolean sorted = false;
        while (!sorted){
            sorted=true;
            for (int i = 0; i<lastIndex-1; i++) {
                if (comparator.compare(array[i],array[i+1]) > 0) {
                    swapElements(array, i, i + 1);
                }
            }
        }
        for (T c:array){
            System.out.println(c.toString());
        }
    }
    private void swapElements(T[] array, int firstIndex, int secondIndex) {
        T buf = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = buf;
    }
}
