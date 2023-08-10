import exceptions.CustomError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomErrorTest {

    private Date mockTimestamp;
    private int mockHttpCode;
    private String mockMensaje;

    private CustomError customError;

    @BeforeEach
    public void setUp() {
        mockTimestamp = new Date();
        mockHttpCode = 404;
        mockMensaje = "Not Found";

        customError = new CustomError(mockTimestamp, mockHttpCode, mockMensaje);
    }

    @Test
    public void testGetTimestamp() {
        assertEquals(mockTimestamp, customError.getTimestamp());
    }

    @Test
    public void testGetHttpCode() {
        assertEquals(mockHttpCode, customError.getHttpCode());
    }

    @Test
    public void testGetMensaje() {
        assertEquals(mockMensaje, customError.getMensaje());
    }

    @Test
    public void testConstructor() {
        assertEquals(mockTimestamp, customError.getTimestamp());
        assertEquals(mockHttpCode, customError.getHttpCode());
        assertEquals(mockMensaje, customError.getMensaje());
    }

}

