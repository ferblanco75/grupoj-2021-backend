package ar.edu.unq.desapp.grupoj.backenddesappapi.model;


public class NonExistentLanguageException extends Exception {
    public NonExistentLanguageException(Integer languageId) {
        super("There is no Language with ID " + languageId.toString());
    }

}
