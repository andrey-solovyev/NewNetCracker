package Contracts;

import People.Client;

import java.util.Calendar;
/**
 * Класс Контракт на проводной интернет со свойствами <b>maxSpeed</b> и наследованными полями.
 * @autor Андрей Соловьем
 */
public class EthernetContract extends Contract {
    /** Поле максимальная скорость */
    private int maxSpeed;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param date_contract - дата начала
     * @param date_end_contract - дата конца
     * @param client - клиент
     * @param maxSpeed - пакет
     * @see EthernetContract()
     */
    public EthernetContract(Calendar date_contract, Calendar date_end_contract, Client client, int maxSpeed) {
        super(date_contract, date_end_contract, client);
        this.maxSpeed = maxSpeed;
    }

    public int getmaxSpeed() {
        return maxSpeed;
    }

    public void setmaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}

