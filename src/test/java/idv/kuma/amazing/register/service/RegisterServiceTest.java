package idv.kuma.amazing.register.service;

import idv.kuma.amazing.ServiceException;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class RegisterServiceTest {


    public DataChecker mockedChecker;
    public Registerer mockedRegisterer;
    public TokenGenerator mockedTokenGenerator;
    public Messenger mockedMessenger;
    private RegisterData data;


    @BeforeEach
    void setUp() {
        data = new RegisterData("AnyName", "AnyEmail", "AnyPassword", "AnyConfirmPassword");
        mockedChecker = Mockito.mock(DataChecker.class);
        mockedRegisterer = Mockito.mock(Registerer.class);
        mockedTokenGenerator = Mockito.mock(TokenGenerator.class);
        mockedMessenger = Mockito.mock(Messenger.class);
    }


    @Test
    public void When_PhoneNumber_Has_Been_Registered_Then_Throw_Exception() throws RegisterException {

        doThrow(new RegisterException("Phone number has been registered")).when(mockedRegisterer).doRegister(data);

        RegisterService service = new RegisterService(
                mockedChecker,
                mockedRegisterer,
                mockedTokenGenerator,
                mockedMessenger
        );

        try {
            service.register(data);
            Assert.fail("Should throw exception");
        } catch (ServiceException e) {
            Assertions.assertThat(e).hasMessage("Phone number has been registered");
        }


    }


    @Test
    public void When_Email_Has_Been_Registered_Then_Throw_Exception() throws RegisterException {

        doThrow(new RegisterException("Email has been registered")).when(mockedRegisterer).doRegister(data);

        RegisterService service = new RegisterService(
                mockedChecker,
                mockedRegisterer,
                mockedTokenGenerator,
                mockedMessenger
        );

        try {
            service.register(data);
            Assert.fail("Should throw exception");
        } catch (ServiceException e) {
            Assertions.assertThat(e).hasMessage("Email has been registered");
        }


    }


    @Test
    public void When_Checker_Fails_Then_Throw_Exception() throws CheckerException {

        doThrow(new CheckerException("FAKE_CHECKER_MESSAGE")).when(mockedChecker).check(data);

        RegisterService service = new RegisterService(
                mockedChecker,
                mockedRegisterer,
                mockedTokenGenerator,
                mockedMessenger
        );

        try {
            service.register(data);
            Assert.fail("Should throw exception");
        } catch (ServiceException e) {
            Assertions.assertThat(e).hasMessage("FAKE_CHECKER_MESSAGE");
        }


    }


    @Test
    public void When_All_Ok_Then_Return_Token() throws ServiceException {

        when(mockedTokenGenerator.generate()).thenReturn("FAKE_TOKEN");

        RegisterService service = new RegisterService(
                mockedChecker,
                mockedRegisterer,
                mockedTokenGenerator,
                mockedMessenger
        );

        String actual = service.register(data);

        Assertions.assertThat(actual).isEqualTo("FAKE_TOKEN");

    }
}