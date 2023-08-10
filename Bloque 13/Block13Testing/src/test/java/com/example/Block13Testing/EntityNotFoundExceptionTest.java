package com.example.Block13Testing;

import exceptions.CustomError;
import exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class EntityNotFoundExceptionTest {

    @Test
    public void testConstructorAndGetError() {
        String errorMessage = "Entity not found";
        EntityNotFoundException exception = new EntityNotFoundException(errorMessage);

        CustomError expectedError = new CustomError(new Date(), 404, errorMessage);

        assertEquals(errorMessage, exception.getMessage());

    }

    @Test
    public void testGetError() {
        String errorMessage = "Another entity not found";
        EntityNotFoundException exception = new EntityNotFoundException(errorMessage);

        CustomError mockError = mock(CustomError.class);
        exception.getMessage();

    }

}
