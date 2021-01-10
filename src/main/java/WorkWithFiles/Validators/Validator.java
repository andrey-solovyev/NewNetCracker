package WorkWithFiles.Validators;

import java.util.List;

public interface Validator<T> {

    List<ValidatorInformation> validate(T t);
}
