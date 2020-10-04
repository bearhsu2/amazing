package idv.kuma.amazing.register.service;

import idv.kuma.amazing.RegisterException;
import idv.kuma.amazing.register.controller.RegisterForm;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class RegisterServiceTest {


    private RegisterForm form;
    public FormChecker mockedChecker;
    public Registerer mockedRegisterer;
    public TokenGenerator mockedTokenGenerator;
    public Messenger mockedMessenger;


    @BeforeEach
    void setUp() {
        mockedChecker = Mockito.mock(FormChecker.class);
        mockedRegisterer = Mockito.mock(Registerer.class);
        mockedTokenGenerator = Mockito.mock(TokenGenerator.class);
        mockedMessenger = Mockito.mock(Messenger.class);
    }


    @Test
    public void When_Checker_Fails_Then_Throw_Exception() throws CheckerException {

        form = new RegisterForm();
        doThrow(new CheckerException("FAKE_MESSAGE")).when(mockedChecker).check(form);

        RegisterService service = new RegisterService(
                mockedChecker,
                mockedRegisterer,
                mockedTokenGenerator,
                mockedMessenger
        );

        try {
            service.register(form);
            Assert.fail("Should throw exception");
        } catch (RegisterException e) {
            Assertions.assertThat(e).hasMessage("FAKE_MESSAGE");
        }


    }


    @Test
    public void When_All_Ok_Then_Return_Token() throws RegisterException {

        form = new RegisterForm();
        when(mockedTokenGenerator.generate()).thenReturn("FAKE_TOKEN");

        RegisterService service = new RegisterService(
                mockedChecker,
                mockedRegisterer,
                mockedTokenGenerator,
                mockedMessenger
        );

        String actual = service.register(form);

        Assertions.assertThat(actual).isEqualTo("FAKE_TOKEN");

    }
}