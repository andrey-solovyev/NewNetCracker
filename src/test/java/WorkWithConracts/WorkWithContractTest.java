package WorkWithConracts;

import Contracts.Contract;
import Contracts.DigitalTVContract;
import Contracts.EthernetContract;
import Contracts.MobilePhoneContract;
import Packages.Package;
import People.Client;
import Sorts.ISorter;
import Sorts.QuickSort;
import WorkWithFiles.WorkWithFile;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.GregorianCalendar;

public class WorkWithContractTest {

    @Test
    public void getContractById() {
    }

    @Test
    public void addNewContract() {
        Client client = new Client("Ivan", new GregorianCalendar(2017, 0, 25), true, "3333333333");
        Contract contract = new MobilePhoneContract(new GregorianCalendar(2017, 0, 25), new GregorianCalendar(2017, 0, 25), client, 23, 34, 23);
        Contract contract1 = new MobilePhoneContract(new GregorianCalendar(2017, 0, 25), new GregorianCalendar(2017, 0, 25), client, 23, 34, 23);

        Contract contract2 = new DigitalTVContract(new GregorianCalendar(2017, 0, 25), new GregorianCalendar(2017, 0, 25), client, Package.SMALL);

        Contract contract3 = new EthernetContract(new GregorianCalendar(2017, 0, 25), new GregorianCalendar(2017, 0, 25), client, 23);

        Contract contract4 = new MobilePhoneContract(new GregorianCalendar(2017, 0, 25), new GregorianCalendar(2017, 0, 25), client, 23, 34, 23);
        WorkWithContract workWithContract = new WorkWithContract();
        workWithContract.addNewContract(contract3);
        workWithContract.addNewContract(contract);
        workWithContract.addNewContract(contract4);
        workWithContract.addNewContract(contract1);
        workWithContract.addNewContract(contract2);
        ISorter<Contract> sorter = new QuickSort<>();
        Comparator<Contract> comparator = (o1, o2) -> {
            if (o1.getId() == o1.getId()) return 0;
            return o1.getId() > o2.getId() ? 1 : -1;
        };

        WorkWithContract workWithContract1 = new WorkWithContract();
        workWithContract1.addNewContract(contract);
        workWithContract1.addNewContract(contract1);
        workWithContract1.addNewContract(contract2);
        workWithContract1.addNewContract(contract3);
        workWithContract1.addNewContract(contract4);
        Assert.assertArrayEquals(workWithContract.getContracts(), workWithContract1.getContracts());
    }

    @Test
    public void getContracts() {
    }

    @Test
    public void addContractFromFile() {
        String path= "C:\\Users\\andru\\IdeaProjects\\NetCracker\\src\\main\\java\\addContract.csv";
        WorkWithContract workWithContract = new WorkWithContract();
        WorkWithFile workWithFile=new WorkWithFile();
        workWithFile.readFileCsv(path,workWithContract);
        WorkWithContract workWithContract1 = new WorkWithContract();
        Contract contract1 = new EthernetContract(new GregorianCalendar(2017, 6, 25), new GregorianCalendar(2019, 7, 25), new Client("Кошелев Сергей Павлович",new GregorianCalendar(1987, 6, 25),true,"2104 542464"), 200);
        Contract contract2 = new DigitalTVContract(new GregorianCalendar(2014, 6, 25), new GregorianCalendar(2020, 7, 25), new Client("Иванов Иван Павлович",new GregorianCalendar(1985, 6, 25),true,"2104 542464"), Package.SMALL);
        Contract contract3 = new MobilePhoneContract(new GregorianCalendar(2015, 6, 25), new GregorianCalendar(2021, 7, 25), new Client("Иванов Сергей Павлович",new GregorianCalendar(1967, 6, 25),true,"2104 542464"), 200,15,69);
        workWithContract1.addNewContract(contract1);
        workWithContract1.addNewContract(contract2);
        workWithContract1.addNewContract(contract3);
        Assert.assertEquals(workWithContract.getContracts()[0].getdateContract(), workWithContract1.getContracts()[0].getdateContract());
    }
}