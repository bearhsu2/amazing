package idv.kuma.amazing.register;

import idv.kuma.amazing.RegisterException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class RegisterServiceTest {

    @Test
    public void When_All_Ok_Then_Return_Token() throws RegisterException {

        RegisterForm form = new RegisterForm();


        FormChecker mockedChecker = Mockito.mock(FormChecker.class);
        Registerer mockedRegisterer = Mockito.mock(Registerer.class);
        TokenGenerator mockedTokenGenerator = Mockito.mock(TokenGenerator.class);
        when(mockedTokenGenerator.generate()).thenReturn("FAKE_TOKEN");
        Messenger mockedMessenger = Mockito.mock(Messenger.class);
        RegisterService service = new RegisterService(
                mockedChecker,
                mockedRegisterer,
                mockedTokenGenerator,
                mockedMessenger
        );

        SuccessResponse actual = service.register(form);

        Assertions.assertThat(actual)
                .isEqualToComparingFieldByField(new SuccessResponse("FAKE_TOKEN"));

    }
}