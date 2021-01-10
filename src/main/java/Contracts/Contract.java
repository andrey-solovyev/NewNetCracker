package Contracts;

import People.Client;

import java.util.Calendar;

/**
 * Класс Контракт со свойствами <b>Id</b>, <b>dateContract</b>,<b>dateEndContract</b>,<b>client</b>.
 * @autor Андрей Соловьем
 */
public abstract class Contract implements Comparable<Contract> {
    //private UUID Id; Один из способов сделать уникальный id
    private static int countId;
    private static int countContract;
    /** Поле id */
    private int Id;
    /** Поле Календарь начала контракта */
    private Calendar dateContract;
    /** Поле Календарь окончания контракта  */
    private Calendar dateEndContract;
    /** Поле номер контракта */
    private int numberContract;
    /** Поле клиент */
    private Client client;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param dateContract - дата начала
     * @param dateEndContract - дата конца
     * @param client - клиент
     * @see Contract()
     */
    public Contract(Calendar dateContract, Calendar dateEndContract, Client client) {
        this.Id = ++countId;
        /** Умножим на 1.5 и таким образом не будет совпадать с id*/
        this.numberContract=(int)(countContract*1.5);
        this.dateContract = dateContract;
        this.dateEndContract = dateEndContract;
        this.client = client;
    }

    /** Getter and setter */

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Calendar getdateContract() {
        return dateContract;
    }

    public void setdateContract(Calendar dateContract) {
        this.dateContract = dateContract;
    }

    public Calendar getdateEndContract() {
        return dateEndContract;
    }

    public void setdateEndContract(Calendar dateEndContract) {
        this.dateEndContract = dateEndContract;
    }
    public String getRemainingTime(){
        return RemainingTime();
    }

    /**
     * Функция получения оставшихся дней контракта
     * @return возвращает строку с оставшимся днями
     */
    private String RemainingTime(){
        long diff = getdateContract().getTimeInMillis() - getdateEndContract().getTimeInMillis();
        long ddays = diff / (24 * 60 * 60 * 1000);
        return "Осталось дней: "+ddays;
    }

    /** compareTo Используется для сравнений и сортировок */

    @Override
    public int compareTo(Contract o) {
        return getId()- o.getId();
    }
}
