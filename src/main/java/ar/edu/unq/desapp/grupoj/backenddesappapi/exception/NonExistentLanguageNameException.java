package ar.edu.unq.desapp.grupoj.backenddesappapi.exception;

public class NonExistentLanguageNameException extends Exception {

    public NonExistentLanguageNameException(String languageName) {
        super("There is no Language  " + languageName);
    }
}
