package WorkWithConracts;

import Contracts.Contract;
import Sorts.ISorter;
import Sorts.QuickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс для работы с контрактами со свойствами <b>Contract[]</b>.
 *
 * @autor Андрей Соловьем
 */
public class WorkWithContract {
    /**
     * Поле контракты
     */

    private Contract[] contracts;
    /**
     * Поле последнего индекса для добавления
     */

    private int lastIndex = 0;


    /**
     * Создание сортировки
     */
    private ISorter<Contract> sorter = new QuickSort<>();

    /**
     * Конструктор - создание нового объекта
     */
    public WorkWithContract() {
        this.contracts = new Contract[10];
    }

    /**
     * Функция для расширения массива
     */
    private void expandArrays() {
        setContracts(Arrays.copyOf(getContracts(), getContracts().length * 2));
    }

    /**
     * Функция для получения контракта по id
     */
    public Contract getContractById(int id) {
        return getContracts()[binarySearch(id, 0, getContracts().length - 1)];
    }

    /**
     * Функция для удаления контракта по id
     */
    public void deleteContractById(int id) {
        setContract(getContracts()[lastIndex], binarySearch(id, 0, getContracts().length - 1));
        setContract(null, lastIndex);
        lastIndex--;
        if (lastIndex > 2) {
            quickSort(0, lastIndex - 1);
        }
    }

    /**
     * Функция для добавления контракта
     */
    public void addNewContract(Contract contract) {
        if (lastIndex == getContracts().length) {
            expandArrays();
        }
        setContract(contract, lastIndex);
        lastIndex++;
//        if (lastIndex > 2) {
//            quickSort(0, lastIndex - 1);
//        }
    }

    public List<Contract> getAllContract() {
        return Arrays.asList(getContracts());
    }

    /**
     * Функция для добавления контрактов
     */
    public void addNewContract(Contract[] contracts) {
        for (Contract c : contracts) {
            if (lastIndex == getContracts().length) {
                expandArrays();
            }
            setContract(c, lastIndex);
            lastIndex++;
        }
        if (lastIndex > 2) {
            quickSort(0, lastIndex - 1);
        }
    }

    /**
     * В чем задумка сортировок? Я попытался уменьшить время на поиск в будущем,при размере больших размерах массива. Скорее это было сделано даже для тренировки
     */
    public void quickSort(int low, int high) {
        if (getContracts().length == 0)
            return;

        if (low >= high)
            return;
        int middle = low + (high - low) / 2;
        int opora = getContracts()[middle].getId();

        int i = low, j = high;
        while (i <= j) {
            while (getContracts()[i].getId() < opora) {
                i++;
            }

            while (getContracts()[j].getId() > opora) {
                j--;
            }

            if (i <= j) {
                Contract temp = getContracts()[i];
                getContracts()[i] = getContracts()[j];
                getContracts()[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(low, j);

        if (high > i)
            quickSort(i, high);
    }

    public List<Contract> findContract(Predicate<Contract> predicate) {
        List<Contract> result = new ArrayList<>();

        for (int i = 0; i < getContracts().length; i++) {
            Contract curr = getContracts()[i];
            if (predicate.test(curr)) {
                result.add(curr);
            }
        }
        return result;
    }

    /**
     * Обычный бинарный поиск на основе сортированного массива
     */
    public int binarySearch(int key, int low, int high) {
        int index = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (getContracts()[mid].getId() < key) {
                low = mid + 1;
            } else if (getContracts()[mid].getId() > key) {
                high = mid - 1;
            } else if (getContracts()[mid].getId() == key) {
                index = mid;
                break;
            }
        }
        return index;
    }

    /**
     * сортировка
     */
    public void sort(Comparator<Contract> comparator) {
        sorter.sort(comparator, getContracts(), getlastIndex());
    }
    /**
     * Проверка на существование человека в контрактах
     */
    public boolean isPersonInContracts(String passport){
        for (int i=0;i<lastIndex;i++){
            if (getContracts()[i].getClient().getpassportData().equals(passport)) return true;
        }
        return false;
    }
    /**
     * getter and setter
     */
    public Contract[] getContracts() {
        return contracts;
    }

    /**
     * изменения контракта по индексу
     */
    public void setContract(Contract contract, int index) {
        this.contracts[index] = contract;
    }

    public void setContracts(Contract[] contracts) {
        this.contracts = contracts;
    }

    public int getlastIndex() {
        return lastIndex;
    }
}
