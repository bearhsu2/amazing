package idv.kuma.amazing.register.controller;

import idv.kuma.amazing.ServiceException;
import idv.kuma.amazing.register.service.RegisterService;
import idv.kuma.amazing.register.service.RegisterServiceFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class RegisterControllerTest {


    @Test
    public void When_All_OK_Then_Throws_Exception_Then_Return_Response() throws ServiceException {

        RegisterForm form = prepareForm();

        RegisterServiceFactory mockedFactory = prepareRegisterServiceReturn(form, "FAKE_TOKEN");

        Response actual = new RegisterController(mockedFactory).greeting(form);

        check(actual, new SuccessResponse("FAKE_TOKEN"));


    }


    private RegisterForm prepareForm() {
        return new RegisterForm();
    }


    private RegisterServiceFactory prepareRegisterServiceReturn(RegisterForm form, String token) throws ServiceException {

        RegisterService mockedService = Mockito.mock(RegisterService.class);
        when(mockedService.register(form)).thenReturn(token);

        RegisterServiceFactory factory = Mockito.mock(RegisterServiceFactory.class);
        when(factory.create(form)).thenReturn(mockedService);

        return factory;

    }


    private void check(Response actual, Response expected) {
        Assertions.assertThat(actual)
                .isEqualToComparingFieldByField(expected);
    }


    @Test
    public void When_RegisterService_Throws_Exception_Then_Return_ErrorResponse() throws ServiceException {

        RegisterForm form = prepareForm();

        RegisterServiceFactory mockedFactory = prepareRegisterServiceThrow(form, "FAKE_MESSAGE");

        Response actual = new RegisterController(mockedFactory).greeting(form);

        check(actual, new ErrorResponse("FAKE_MESSAGE"));


    }


    private RegisterServiceFactory prepareRegisterServiceThrow(RegisterForm form, String message) throws ServiceException {
        RegisterService mockedService = Mockito.mock(RegisterService.class);
        when(mockedService.register(form)).thenThrow(new ServiceException(message));

        RegisterServiceFactory factory = Mockito.mock(RegisterServiceFactory.class);
        when(factory.create(form)).thenReturn(mockedService);

        return factory;
    }
}