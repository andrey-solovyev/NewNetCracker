package WorkWithFiles.Validators;

public class ValidatorInformation {
    private String information;
    private ValidatorStatus status;

    /**
     * @param information выводит сообщение
     */
    public ValidatorInformation(String information, ValidatorStatus status) {
        this.information = information;
        this.status = status;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public ValidatorStatus getStatus() {
        return status;
    }

    public void setStatus(ValidatorStatus status) {
        this.status = status;
    }
}