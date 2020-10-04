package idv.kuma.amazing.register.controller;

import idv.kuma.amazing.ServiceException;
import idv.kuma.amazing.register.service.RegisterData;
import idv.kuma.amazing.register.service.RegisterService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class RegisterControllerTest {


    @Test
    public void When_All_OK_Then_Throws_Exception_Then_Return_Response() throws ServiceException, RegisterServiceFactoryException {

        RegisterForm form = prepareRegisterForm();

        RegisterServiceFactory mockedFactory = prepareRegisterServiceReturn(form.toRegisterData(), "FAKE_TOKEN");

        MessageSource mockedMessageSource = prepareMessageSource("FAKE_MESSAGE", Locale.US, "LOCALED_FAKE_MESSAGE");

        Response actual = new RegisterController(mockedFactory, mockedMessageSource)
                .register(Locale.US, form);

        check(actual, new SuccessResponse("FAKE_TOKEN"));


    }


    private RegisterForm prepareRegisterForm() {
        return new RegisterForm("AnyName", "AnyEmail", "AnyPassword", "AnyConfirmPassword", Type.EMAIL);
    }


    private RegisterServiceFactory prepareRegisterServiceReturn(RegisterData data, String token) throws ServiceException, RegisterServiceFactoryException {

        RegisterService mockedService = Mockito.mock(RegisterService.class);
        when(mockedService.register(eq(data))).thenReturn(token);

        RegisterServiceFactory factory = Mockito.mock(RegisterServiceFactory.class);
        when(factory.create(any(Type.class))).thenReturn(mockedService);

        return factory;

    }


    private MessageSource prepareMessageSource(String key, Locale locale, String localedMessage) {
        MessageSource mockedMessageSource = Mockito.mock(MessageSource.class);
        when(mockedMessageSource.getMessage(key, null, locale))
                .thenReturn(localedMessage);

        return mockedMessageSource;
    }


    private void check(Response actual, Response expected) {
        Assertions.assertThat(actual)
                .isEqualToComparingFieldByField(expected);
    }


    @Test
    public void When_RegisterService_Throws_Exception_Then_Return_ErrorResponse() throws ServiceException, RegisterServiceFactoryException {

        RegisterForm form = prepareRegisterForm();

        RegisterServiceFactory mockedFactory = prepareRegisterServiceThrow(form.toRegisterData(), "FAKE_MESSAGE");
        MessageSource mockedMessageSource = prepareMessageSource("FAKE_MESSAGE", Locale.US, "LOCALED_FAKE_MESSAGE");

        Response actual = new RegisterController(mockedFactory, mockedMessageSource)
                .register(Locale.US, form);

        check(actual, new ErrorResponse("LOCALED_FAKE_MESSAGE"));


    }


    private RegisterServiceFactory prepareRegisterServiceThrow(RegisterData data, String message) throws ServiceException, RegisterServiceFactoryException {
        RegisterService mockedService = Mockito.mock(RegisterService.class);
        when(mockedService.register(eq(data))).thenThrow(new ServiceException(message));

        RegisterServiceFactory factory = Mockito.mock(RegisterServiceFactory.class);
        when(factory.create(any(Type.class))).thenReturn(mockedService);

        return factory;
    }
}