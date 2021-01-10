package People;


import java.util.Calendar;
/**
 * Класс продукции со свойствами <b>Id</b>, <b>fcs</b>,<b>dateBirths</b>,<b>isMale</b>,<b>passportData</b>.
 * @autor Андрей Соловьем
 */
public class Client implements Comparable<Client>{
//Клиент - отдельная сущность ID, фио, дата_рождения, пол, номер_серия_паспорта
private static int countId;
    /** Поле id */
    private int Id;
    /** Поле фио */
    private String fcs;
    /** Поле дата_рождения */
    private Calendar dateBirths;
    /** Поле пол */
    private boolean isMale;
    /** Поле номер_серия_паспорта */
    private String passportData;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param fcs - фио
     * @param dateBirths - дата_рождения
     * @param isMale - пол
     * @param passportData - номер_серия_паспорта
     * @see Client()
     */
    public Client(String fcs, Calendar dateBirths, boolean isMale, String passportData) {
        Id = ++countId;
        this.fcs = fcs;
        this.dateBirths = dateBirths;
        this.isMale = isMale;
        this.passportData = passportData;
    }

    public Client() {
    }

    /** getter and setter */

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFcs() {
        return fcs;
    }

    public void setFcs(String fcs) {
        this.fcs = fcs;
    }

    public Calendar getdateBirths() {
        return dateBirths;
    }

    public void setdateBirths(Calendar dateBirths) {
        this.dateBirths = dateBirths;
    }

    public boolean isisMale() {
        return isMale;
    }

    public void setisMale(boolean isMale) {
        this.isMale = isMale;
    }

    public String getpassportData() {
        return passportData;
    }

    public void setpassportData(String passportData) {
        this.passportData = passportData;
    }
    /**
     * Функция получения возраста
     * @return возвращает влзраст клиента
     */
    public String getAge(){
        long diff = Calendar.getInstance().getTimeInMillis() - getdateBirths().getTimeInMillis();
        long ddays = diff / (24 * 60 * 60 * 1000);
        long years = ddays/365;
        return ""+years;
    }

    @Override
    public int compareTo(Client o) {
        return getId()-o.getId();
    }
}
