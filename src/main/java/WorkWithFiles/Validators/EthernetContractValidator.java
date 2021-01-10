package WorkWithFiles.Validators;

import Contracts.DigitalTVContract;
import Contracts.EthernetContract;
import Exceptions.ValidatorException;

public class EthernetContractValidator {
    public String check(EthernetContract ethernetContract) throws ValidatorException {
        try {
            if (ethernetContract.getmaxSpeed()>1024){
                return "Red risk. Check max speed";
            }
            return "Ok";
        } catch (Exception e) {
            throw new ValidatorException(e);
        }
    }
}
