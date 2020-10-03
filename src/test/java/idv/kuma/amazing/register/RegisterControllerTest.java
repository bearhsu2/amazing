package idv.kuma.amazing.register;

import idv.kuma.amazing.RegisterException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class RegisterControllerTest {


    public RegisterForm form;
    public RegisterService mockedService;


    @Test
    public void When_All_OK_Then_Throws_Exception_Then_Return_Response() throws RegisterException {

        form = prepareForm();

        mockedService = prepareRegisterServiceReturn("FAKE_TOKEN");

        Response actual = new RegisterController(mockedService).greeting(form);

        check(actual, new SuccessResponse("FAKE_TOKEN"));


    }


    private RegisterForm prepareForm() {
        return new RegisterForm();
    }


    private RegisterService prepareRegisterServiceReturn(String token) throws RegisterException {
        RegisterService mockedService = Mockito.mock(RegisterService.class);
        when(mockedService.register(this.form)).thenReturn(new SuccessResponse(token));
        return mockedService;
    }


    private void check(Response actual, Response expected) {
        Assertions.assertThat(actual)
                .isEqualToComparingFieldByField(expected);
    }


    @Test
    public void When_RegisterService_Throws_Exception_Then_Return_ErrorResponse() throws RegisterException {

        form = new RegisterForm();

        mockedService = prepareRegisterServiceThrow("FAKE_MESSAGE");

        Response actual = new RegisterController(mockedService).greeting(form);

        check(actual, new ErrorResponse("FAKE_MESSAGE"));


    }


    private RegisterService prepareRegisterServiceThrow(String message) throws RegisterException {
        RegisterService mockedService = Mockito.mock(RegisterService.class);
        when(mockedService.register(form)).thenThrow(new RegisterException(message));
        return mockedService;
    }
}