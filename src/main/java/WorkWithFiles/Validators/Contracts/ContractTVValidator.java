package WorkWithFiles.Validators.Contracts;

import Contracts.Contract;
import Contracts.DigitalTVContract;
import Contracts.MobilePhoneContract;
import Exceptions.ValidatorException;
import Packages.Package;
import WorkWithFiles.Validators.Validator;
import WorkWithFiles.Validators.ValidatorInformation;
import WorkWithFiles.Validators.ValidatorStatus;

import java.util.LinkedList;
import java.util.List;

import static Packages.Package.*;


public class ContractTVValidator implements Validator<Contract> {
    public List<ValidatorInformation> checkPackage(Package packageTV) {
        List<ValidatorInformation> result = new LinkedList<>();

        switch (packageTV) {
            case SMALL:
                break;
            case STANDART:
                break;
            case EXTRA:
                break;
            default:
                result.add(new ValidatorInformation("Invalid tv package", ValidatorStatus.ERROR));
        }
        return result;
    }

    /**
     * @param contract контракт для валидации
     * @return {@link java.util.List} результат валидации {@link ValidatorInformation}
     */
    @Override
    public List<ValidatorInformation> validate(Contract contract) {
        DigitalTVContract digitalTVContract = (DigitalTVContract) contract;
        List<ValidatorInformation> result = new LinkedList<>();
        result.addAll(checkPackage(digitalTVContract.getpackageTv()));
        return result;
    }
}
