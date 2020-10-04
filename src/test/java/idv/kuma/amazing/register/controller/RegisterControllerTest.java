package idv.kuma.amazing.register.controller;

import idv.kuma.amazing.ServiceException;
import idv.kuma.amazing.register.service.RegisterData;
import idv.kuma.amazing.register.service.RegisterService;
import idv.kuma.amazing.register.service.RegisterServiceFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class RegisterControllerTest {


    @Test
    public void When_All_OK_Then_Throws_Exception_Then_Return_Response() throws ServiceException {

        RegisterForm form = prepareRegisterForm();

        RegisterServiceFactory mockedFactory = prepareRegisterServiceReturn(form.toRegisterData(), "FAKE_TOKEN");

        Response actual = new RegisterController(mockedFactory).register(form);

        check(actual, new SuccessResponse("FAKE_TOKEN"));


    }


    private RegisterForm prepareRegisterForm() {
        return new RegisterForm("AnyName", "AnyEmail", "AnyPassword", "AnyConfirmPassword");
    }


    private RegisterServiceFactory prepareRegisterServiceReturn(RegisterData data, String token) throws ServiceException {

        RegisterService mockedService = Mockito.mock(RegisterService.class);
        when(mockedService.register(eq(data))).thenReturn(token);

        RegisterServiceFactory factory = Mockito.mock(RegisterServiceFactory.class);
        when(factory.create()).thenReturn(mockedService);

        return factory;

    }


    private void check(Response actual, Response expected) {
        Assertions.assertThat(actual)
                .isEqualToComparingFieldByField(expected);
    }


    @Test
    public void When_RegisterService_Throws_Exception_Then_Return_ErrorResponse() throws ServiceException {

        RegisterForm form = prepareRegisterForm();

        RegisterServiceFactory mockedFactory = prepareRegisterServiceThrow(form.toRegisterData(), "FAKE_MESSAGE");

        Response actual = new RegisterController(mockedFactory).register(form);

        check(actual, new ErrorResponse("FAKE_MESSAGE"));


    }


    private RegisterServiceFactory prepareRegisterServiceThrow(RegisterData data, String message) throws ServiceException {
        RegisterService mockedService = Mockito.mock(RegisterService.class);
        when(mockedService.register(eq(data))).thenThrow(new ServiceException(message));

        RegisterServiceFactory factory = Mockito.mock(RegisterServiceFactory.class);
        when(factory.create()).thenReturn(mockedService);

        return factory;
    }
}