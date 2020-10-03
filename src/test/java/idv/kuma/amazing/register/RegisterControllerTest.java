package idv.kuma.amazing.register;

import idv.kuma.amazing.RegisterException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RegisterControllerTest {

    @Test
    public void When_RegisterService_Throws_Exception_Then_Return_ErrorResponse() throws RegisterException {

        RegisterService registerService = Mockito.mock(RegisterService.class);
        when(registerService.register(any(RegisterForm.class))).thenThrow(new RegisterException("FAKE_MESSAGE"));

        RegisterController controller = new RegisterController(registerService);
        RegisterForm registerForm = new RegisterForm();
        Response actual = controller.greeting(registerForm);

        Assertions.assertThat(actual)
                .isEqualToComparingFieldByField(new ErrorResponse("FAKE_MESSAGE"));


    }
}