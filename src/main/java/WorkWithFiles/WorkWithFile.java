package WorkWithFiles;


import Contracts.Contract;
import Contracts.DigitalTVContract;
import Contracts.EthernetContract;
import Contracts.MobilePhoneContract;
import Exceptions.ValidatorException;
import Packages.Package;
import People.Client;
import WorkWithConracts.WorkWithContract;
import WorkWithFiles.Validators.Contracts.ClientValidator;
import WorkWithFiles.Validators.Contracts.ContractTVValidator;
import WorkWithFiles.Validators.Contracts.EthernetContractValidator;
import WorkWithFiles.Validators.Contracts.MobileContractValidator;
import WorkWithFiles.Validators.Validator;
import WorkWithFiles.Validators.ValidatorInformation;
import WorkWithFiles.Validators.ValidatorStatus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.*;
/**
 * Формат линии в файле
 */

/**
 * ДатаНачалаКонтракта; ДатаКонцаКонтракта; ФИОКлинета;ПолКлиента;ДатаРождения;ДанныеПаспорта;ТипКонтракта;ДопИнформацияПоКонтракту;
 */

public class WorkWithFile {
    private static final List<Validator<Contract>> VALIDATORS = new LinkedList<>();

    static {
        VALIDATORS.add(new ContractTVValidator());
        VALIDATORS.add(new EthernetContractValidator());
        VALIDATORS.add(new MobileContractValidator());
    }

    public void readFileCsv(String pathToFile, WorkWithContract workWithContract) {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {

            while ((line = br.readLine()) != null) {
                //за разделение берем знак ;
                Contract contract = parseLine(line.split(";"));
                if (contract != null && !workWithContract.isPersonInContracts(contract.getClient().getpassportData())) {
                    workWithContract.addNewContract(contract);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Contract parseLine(String[] elements) {
        Contract contract = null;

        String[] dateStartContract = elements[0].split("-");
        String[] dateEndContract = elements[1].split("-");
        Calendar dataStart = new GregorianCalendar(parseInt(dateStartContract[0]), parseInt(dateStartContract[1]), parseInt(dateStartContract[2]));
        Calendar dataEnd = new GregorianCalendar(parseInt(dateEndContract[0]), parseInt(dateEndContract[1]), parseInt(dateEndContract[2]));
        String fcs = elements[2];
        Boolean isMale = Boolean.parseBoolean(elements[3]);
        String[] dateBirthsString = elements[4].split("-");
        Calendar dateBirths = new GregorianCalendar(parseInt(dateBirthsString[0]), parseInt(dateBirthsString[1]), parseInt(dateBirthsString[2]));
        Client client = new Client(fcs, dateBirths, isMale, elements[5]);
        for (ValidatorInformation v : new ClientValidator().validate(client)) {
            if (!v.getStatus().equals(ValidatorStatus.OKEY)) {
                System.out.println(v.getInformation());
            }
        }

        try {
            if (parseInt(elements[6]) == 1) {
                Package p = null;
                if (elements[7].equals("SMALL")) {
                    p = Package.SMALL;
                } else if (elements[7].equals("STANDART")) {
                    p = Package.STANDART;
                } else if (elements[7].equals("EXTRA")) {
                    p = Package.EXTRA;
                }
                contract = new DigitalTVContract(dataStart, dataEnd, client, p);
            }
            if (parseInt(elements[6]) == 2) {

                contract = new EthernetContract(dataStart, dataEnd, client, parseInt(elements[7]));

            }
            if (parseInt(elements[6]) == 3) {

                contract = new MobilePhoneContract(dataStart, dataEnd, client, parseInt(elements[7]), parseInt(elements[8]), parseInt(elements[9]));

            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        if (!validateContract(contract)) {
            return null;
        }
        return contract;
    }


    private boolean validateContract(Contract contract) {
        List<ValidatorInformation> results = new LinkedList<>();

        VALIDATORS.stream()
                .map(validator -> validator.validate(contract))
                .filter(currentResult -> currentResult.size() != 0)
                .forEach(results::addAll);

        for (ValidatorInformation info : results) {
            if (info.getStatus().equals(ValidatorStatus.ERROR)) {
                return false;
            }
        }
        return true;
    }
}
