package WorkWithFiles.Validators;

import Contracts.DigitalTVContract;
import Exceptions.ValidatorException;
import Packages.Package;

import static Packages.Package.EXTRA;


public class ContractTVValidator {
    public String check(DigitalTVContract digitalTVContract) throws ValidatorException {
        try {
            switch (digitalTVContract.getpackageTv()) {
                case SMALL:
                    break;
                case STANDART:
                    break;
                case EXTRA:
                    break;
                default:
                    throw new ValidatorException("Problem with package tv ");
            }
            return "Ok";
        } catch (ValidatorException e) {
            throw e;
        } catch (Exception e) {
            throw new ValidatorException(e);
        }
    }
}
