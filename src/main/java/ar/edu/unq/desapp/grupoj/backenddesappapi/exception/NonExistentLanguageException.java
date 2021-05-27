package ar.edu.unq.desapp.grupoj.backenddesappapi.exception;


public class NonExistentLanguageException extends Exception {
    public NonExistentLanguageException(Integer languageId) {
        super("There is no Language with ID " + languageId.toString());
    }





}
