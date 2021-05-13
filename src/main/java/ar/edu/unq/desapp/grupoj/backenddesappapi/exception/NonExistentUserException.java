package ar.edu.unq.desapp.grupoj.backenddesappapi.exception;

import ar.edu.unq.desapp.grupoj.backenddesappapi.service.UserDTO;

public class NonExistentUserException extends Exception {
    public NonExistentUserException(UserDTO user) {
        super("There is no user with UserID " + user.userId);
    }
}
