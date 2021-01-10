package Exceptions;

public class ValidatorException extends Exception {
    /**
     * @param message сообщение об ошибке
     */
    public ValidatorException(String message) {
        super(message);
    }

    /**
     * @param exception исключение, вызванное при парсинге
     */
    public ValidatorException(Exception exception) {
        super(exception);
    }
}