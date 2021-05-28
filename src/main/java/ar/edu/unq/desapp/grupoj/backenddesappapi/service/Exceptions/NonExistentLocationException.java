package ar.edu.unq.desapp.grupoj.backenddesappapi.service.Exceptions;

public class NonExistentLocationException extends Exception{
    public NonExistentLocationException(Integer locationId) {
        super("There is no Location with ID " + locationId.toString());
    }
}
