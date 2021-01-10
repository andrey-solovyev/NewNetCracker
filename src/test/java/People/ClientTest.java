package People;

import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void getAge() {
        Client client=new Client("Ivan",new GregorianCalendar(1963, 0 , 25),true,"3333333333");
        long diff = Calendar.getInstance().getTimeInMillis() - client.getdateBirths().getTimeInMillis();
        long ddays = diff / (24 * 60 * 60 * 1000);
        long years = ddays/365;
        System.out.println("Your Years Difference="+years);
        Assert.assertEquals(years,57);
    }
}