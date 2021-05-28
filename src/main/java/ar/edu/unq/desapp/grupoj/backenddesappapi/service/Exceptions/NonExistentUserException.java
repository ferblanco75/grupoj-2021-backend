package ar.edu.unq.desapp.grupoj.backenddesappapi.service.Exceptions;

import ar.edu.unq.desapp.grupoj.backenddesappapi.service.DTOs.UserDTO;

public class NonExistentUserException extends Exception {
    public NonExistentUserException(UserDTO user) {
        super("There is no user with UserID " + user.userId);
    }
}
