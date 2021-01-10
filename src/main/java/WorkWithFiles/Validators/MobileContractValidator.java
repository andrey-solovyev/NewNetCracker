package WorkWithFiles.Validators;

import Contracts.DigitalTVContract;
import Contracts.MobilePhoneContract;
import Exceptions.ValidatorException;

public class MobileContractValidator {
    public String check(MobilePhoneContract ethernetContract) throws ValidatorException {
        try {
        if (ethernetContract.getSms() <0 || ethernetContract.getMegabytes() <0 || ethernetContract.getMinites() <0){
            return "Red risk. Check max speed";
        }
            return "Ok";
        } catch (Exception e) {
            throw new ValidatorException(e);
        }
    }
}
