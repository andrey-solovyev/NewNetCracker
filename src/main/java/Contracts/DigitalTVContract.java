package Contracts;

import Packages.Package;
import People.Client;

import java.util.Calendar;
/**
 * Класс Контракт на цифровое телевидение  со свойствами <b>packageTv</b> и наследованными полями.
 * @autor Андрей Соловьем
 */
public class DigitalTVContract extends Contract {
    /** Поле пакет */
    private Package packageTv;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param date_contract - дата начала
     * @param date_end_contract - дата конца
     * @param client - клиент
     * @param packageTv - пакет
     * @see DigitalTVContract()
     */
    public DigitalTVContract(Calendar date_contract, Calendar date_end_contract, Client client, Package packageTv) {
        super(date_contract, date_end_contract, client);
        this.packageTv = packageTv;
    }

    /** Getter and setter*/

    public Package getpackageTv() {
        return packageTv;
    }

    public void setpackageTv(Package packageTv) {
        this.packageTv = packageTv;
    }
}
