import exceptions.CustomError;
import exceptions.UnprocessableEntityException;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnprocessableEntityExceptionTest {

    @Test
    public void testConstructorAndGetError() {
        String errorMessage = "Unprocessable Entity";
        UnprocessableEntityException exception = new UnprocessableEntityException(errorMessage);

        CustomError expectedError = new CustomError(new Date(), 422, errorMessage);

        assertEquals(errorMessage, exception.getMessage());
        assertEquals(expectedError.getHttpCode(), exception.getError().getHttpCode());
        assertEquals(expectedError.getMensaje(), exception.getError().getMensaje());

    }
}
