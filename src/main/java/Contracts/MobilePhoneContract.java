package Contracts;

import People.Client;

import java.util.Calendar;
import java.util.UUID;

/**
 * Класс Контракт на мобильную связь со свойствами <b>minites</b>,<b>megabytes</b>,<b>sms</b> и наследованными полями.
 *
 * @autor Андрей Соловьем
 */
public class MobilePhoneContract extends Contract {
    /**
     * Поле минуты
     */

    private int minites;
    /**
     * Поле мегабайты
     */

    private int megabytes;
    /**
     * Поле смс
     */

    private int sms;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param date_contract     - дата начала
     * @param date_end_contract - дата конца
     * @param client            - клиент
     * @param minites           - пакет
     * @param megabytes         - пакет
     * @param sms               - пакет
     * @see MobilePhoneContract()
     */
    public MobilePhoneContract(Calendar date_contract, Calendar date_end_contract, Client client, int minites, int megabytes, int sms) {
        super(date_contract, date_end_contract, client);
        this.minites = minites;
        this.megabytes = megabytes;
        this.sms = sms;
    }

    public int getMinites() {
        return minites;
    }

    public void setMinites(int minites) {
        this.minites = minites;
    }

    public int getMegabytes() {
        return megabytes;
    }

    public void setMegabytes(int megabytes) {
        this.megabytes = megabytes;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }
}

