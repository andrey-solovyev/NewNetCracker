package WorkWithFiles.Validators.Contracts;

import Contracts.Contract;
import Contracts.EthernetContract;
import Contracts.MobilePhoneContract;
import Exceptions.ValidatorException;
import WorkWithFiles.Validators.Validator;
import WorkWithFiles.Validators.ValidatorInformation;
import WorkWithFiles.Validators.ValidatorStatus;

import java.util.LinkedList;
import java.util.List;

public class EthernetContractValidator implements Validator<Contract> {
    private static final int MIN_SPEED = 1;
    private static final int MAX_SPEED = 1024;
    /**
     * @param speed допустимая  скорость
     * @return List<ValidatorInformation> - возвращает лист с ошибками, если есть
     */
    public List<ValidatorInformation> amountMaxSpeedCheck(int speed) {
        List<ValidatorInformation> result = new LinkedList<>();
        if (speed < MIN_SPEED || speed > MAX_SPEED) {
            result.add(new ValidatorInformation("Speed is invalid ", ValidatorStatus.ERROR));

        } else {
            result.add(new ValidatorInformation("okey ", ValidatorStatus.OKEY));
        }
        return result;
    }

    /**
     * @param contract контракт для валидации
     * @return {@link java.util.List} результат валидации {@link ValidatorInformation}
     */
    @Override
    public List<ValidatorInformation> validate(Contract contract) {
        EthernetContract ethernetContract = (EthernetContract) contract;
        List<ValidatorInformation> result = new LinkedList<>();

        result.addAll(amountMaxSpeedCheck(ethernetContract.getmaxSpeed()));
        return result;
    }


}
