package Sorts;

import java.util.Comparator;

public class QuickSort<T> implements ISorter<T> {
    @Override
    public void sort(Comparator<T> comparator, T[] array, int lastIndex) {
        quickSortI(comparator,array,0,lastIndex-1);
    }
      private void quickSortI(Comparator<T> comparator, T[] array, int low, int high){
        if (array.length == 0)
            return;

        if (low >= high)
            return;
        int middle = low + (high - low) / 2;
        T opora = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (comparator.compare(array[i],opora)<0) {
                i++;
            }
            while (comparator.compare(array[i],opora)>0) {
                j--;
            }

            if (i <= j) {
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j)
            quickSortI(comparator,array,low, j);

        if (high > i)
            quickSortI(comparator,array,i, high);
    }
}
