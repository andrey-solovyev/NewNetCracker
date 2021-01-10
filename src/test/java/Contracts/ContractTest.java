package Contracts;

import People.Client;
import org.junit.Assert;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class ContractTest {

    @Test
    public void getClient() {
        Client client=new Client("Ivan",new GregorianCalendar(2017, 0 , 25),true,"3333333333");
        Contract contract = new MobilePhoneContract(new GregorianCalendar(2017, 0 , 25),new GregorianCalendar(2017, 0 , 25),client,23,34,23);
        Assert.assertEquals(contract.getClient(),client);
    }

    @Test
    public void getId() {
        Client client=new Client("Ivan",new GregorianCalendar(2017, 0 , 25),true,"3333333333");
        Contract contract = new MobilePhoneContract(new GregorianCalendar(2017, 0 , 25),new GregorianCalendar(2017, 0 , 25),client,23,34,23);
        Assert.assertEquals(contract.getId(),1);
    }


    @Test
    public void getDate_contract() {
        Client client=new Client("Ivan",new GregorianCalendar(2017, 0 , 25),true,"3333333333");
        Contract contract = new MobilePhoneContract(new GregorianCalendar(2017, 0 , 25),new GregorianCalendar(2017, 0 , 25),client,23,34,23);
        //Assert.assertEquals(contract.getDate_contract(),new GregorianCalendar(2017, 0 , 25));
    }


    @Test
    public void getRemainingTime() {
    }
}