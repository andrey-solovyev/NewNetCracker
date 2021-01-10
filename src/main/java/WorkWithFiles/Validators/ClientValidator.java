package WorkWithFiles.Validators;

import Exceptions.ValidatorException;
import People.Client;

public class ClientValidator {
    public String parse(Client client) throws ValidatorException {
        try {
            String[] dataPassport = client.getpassportData().split("\\s");
            if (!(client.getFcs().matches(".*\\d+.*"))){
                throw new ValidatorException("Invalid FCS");
            }
           if (!(dataPassport[0].matches("[-+]?\\d+") && dataPassport[0].matches("[-+]?\\d+"))) {
               throw new ValidatorException("Invalid passport date");
           }
            return "ok";
        } catch (ValidatorException e) {
            throw e;
        } catch (Exception e) {
            throw new ValidatorException(e);
        }
    }
}
