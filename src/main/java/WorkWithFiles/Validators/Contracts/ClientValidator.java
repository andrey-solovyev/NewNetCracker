package WorkWithFiles.Validators.Contracts;

import Exceptions.ValidatorException;
import People.Client;
import WorkWithFiles.Validators.Validator;
import WorkWithFiles.Validators.ValidatorInformation;
import WorkWithFiles.Validators.ValidatorStatus;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class ClientValidator implements Validator<Client> {
    private static final int MAX_FULLNAME_LENGTH = 25;
    private static final int MIN_FULLNAME_LENGTH = 2;
    private static final int PASSPORT_LENGTH = 10;
    private static final int MIN_AGE = 16;


    public List<ValidatorInformation> validateFCS(String fullName) {
        List<ValidatorInformation> result = new LinkedList<>();

        if (checkFullNameIsTooLong(fullName)) {
            result.add(new ValidatorInformation("Name: " + fullName + " is too long", ValidatorStatus.RED_RISK));
        }

        if (checkFullNameIsTooSmall(fullName)) {
            result.add(new ValidatorInformation("Name: " + fullName + " is too small", ValidatorStatus.RED_RISK));
        }

        if (checkFullNameContainsInvalidCharacters(fullName)) {
            result.add(new ValidatorInformation("Name: " + fullName + " contains invalid characters", ValidatorStatus.ERROR));
        }
        return result;
    }

    public boolean checkFullNameIsTooLong(String fullName) {
        return fullName.length() > MAX_FULLNAME_LENGTH;
    }


    public boolean checkFullNameIsTooSmall(String fullName) {
        return fullName.length() < MIN_FULLNAME_LENGTH;
    }


    public boolean checkFullNameContainsInvalidCharacters(String fullName) {
        Pattern r = Pattern.compile("[^a-zA-Z ]");
        return r.matcher(fullName).find();
    }



    public List<ValidatorInformation> validatePassport(String passport) {
        List<ValidatorInformation> result = new LinkedList<>();

        if (checkPassportLengthIsNotCorrect(passport)) {
            result.add(new ValidatorInformation("Invalid length of passport " + passport, ValidatorStatus.ERROR));
        }

        if (checkPassportContainsNoIntCharacters(passport)) {
            result.add(new ValidatorInformation("Invalid passport without int " + passport, ValidatorStatus.ERROR));
        }
        return result;
    }


    public boolean checkPassportLengthIsNotCorrect(String passport) {
        return passport.length() != PASSPORT_LENGTH;
    }

    public boolean checkPassportContainsNoIntCharacters(String passport) {
        Pattern r = Pattern.compile("[^0-9]");
        return r.matcher(passport).find();
    }


    public List<ValidatorInformation> validateBirthDate(Calendar birthDate) {
        List<ValidatorInformation> result = new LinkedList<>();
        Calendar currentDate = Calendar.getInstance();

        if (birthDate.get(Calendar.YEAR) >= LocalDate.now().getYear()) {
            result.add(new ValidatorInformation("Birthday is invalid: " + birthDate , ValidatorStatus.ERROR));
        }

        if (Math.abs(birthDate.get(Calendar.YEAR)-LocalDate.now().getYear()) >= MIN_AGE ) {
            result.add(new ValidatorInformation("Invalid age", ValidatorStatus.ERROR));
        }
        return result;
    }


    /**
     * @param client контракт для валидации
     * @return {@link java.util.List} результат валидации {@link ValidatorInformation}
     */
    @Override
    public List<ValidatorInformation> validate(Client client) {
        List<ValidatorInformation> result = new LinkedList<>();

        result.addAll(validateFCS(client.getFcs()));
        result.addAll(validatePassport(client.getpassportData()));
        result.addAll(validateBirthDate(client.getdateBirths()));
        return result;
    }
}
