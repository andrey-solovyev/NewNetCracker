package WorkWithFiles.Validators.Contracts;

import Contracts.Contract;
import Contracts.DigitalTVContract;
import Contracts.MobilePhoneContract;
import Exceptions.ValidatorException;
import WorkWithFiles.Validators.Validator;
import WorkWithFiles.Validators.ValidatorInformation;
import WorkWithFiles.Validators.ValidatorStatus;

import java.util.LinkedList;
import java.util.List;

public class MobileContractValidator implements Validator<Contract> {
    private static final int MIN_SMS = 0;
    private static final int MAX_SMS = 10000;
    private static final int MIN_MINUTES = 0;
    private static final int MAX_MINUTES = 10000;
    private static final int MIN_MEGABYTES = 0;
    private static final int MAX_MEGABYTES = 1024 * 50;

    /**
     * @param sms всего смс в контракте
     * @return List<ValidatorInformation> - возвращает лист с ошибками, если есть
     */
    public List<ValidatorInformation> amountSmsCheck(int sms) {
        List<ValidatorInformation> result = new LinkedList<>();
        if (sms < MIN_SMS || sms > MAX_SMS) {
            result.add(new ValidatorInformation("sms is invalid ", ValidatorStatus.ERROR));

        } else {
            result.add(new ValidatorInformation("okey ", ValidatorStatus.OKEY));
        }
        return result;
    }

    /**
     * @param minutes количество количество минут у клиента
     * @return List<ValidatorInformation> - возвращает лист с ошибками, если есть
     */
    public List<ValidatorInformation> amountMinutesCheck(int minutes) {
        List<ValidatorInformation> result = new LinkedList<>();
        if (minutes < MIN_MINUTES || minutes > MAX_MINUTES) {
            result.add(new ValidatorInformation("minutes is invalid ", ValidatorStatus.ERROR));

        } else {
            result.add(new ValidatorInformation("okey ", ValidatorStatus.OKEY));
        }
        return result;
    }

    /**
     * @param megabytes количество мегабайт у клиента
     * @return List<ValidatorInformation> - возвращает лист с ошибками, если есть
     */
    public List<ValidatorInformation> amountMegabytesCheck(int megabytes) {
        List<ValidatorInformation> result = new LinkedList<>();
        if (megabytes < MIN_MEGABYTES || megabytes > MAX_MEGABYTES) {
            result.add(new ValidatorInformation("megabytes is invalid ", ValidatorStatus.ERROR));

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
        MobilePhoneContract mobileContract = (MobilePhoneContract) contract;
        List<ValidatorInformation> result = new LinkedList<>();

        result.addAll(amountSmsCheck(mobileContract.getSms()));
        result.addAll(amountMinutesCheck(mobileContract.getMinites()));
        result.addAll(amountMegabytesCheck(mobileContract.getMegabytes()));
        return result;
    }


}
