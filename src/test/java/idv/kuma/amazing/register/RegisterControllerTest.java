package idv.kuma.amazing.register;

import idv.kuma.amazing.RegisterException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class RegisterControllerTest {

    @Test
    public void When_All_OK_Then_Throws_Exception_Then_Return_Response() throws RegisterException {

        RegisterForm registerForm = new RegisterForm();
        RegisterService registerService = Mockito.mock(RegisterService.class);
        when(registerService.register(registerForm)).thenReturn(new SuccessResponse("FAKE_TOKEN"));

        Response actual = new RegisterController(registerService).greeting(registerForm);

        Assertions.assertThat(actual)
                .isEqualToComparingFieldByField(new SuccessResponse("FAKE_TOKEN"));


    }


    @Test
    public void When_RegisterService_Throws_Exception_Then_Return_ErrorResponse() throws RegisterException {

        RegisterForm registerForm = new RegisterForm();
        RegisterService registerService = Mockito.mock(RegisterService.class);
        when(registerService.register(registerForm)).thenThrow(new RegisterException("FAKE_MESSAGE"));

        Response actual = new RegisterController(registerService).greeting(registerForm);

        Assertions.assertThat(actual)
                .isEqualToComparingFieldByField(new ErrorResponse("FAKE_MESSAGE"));


    }
}