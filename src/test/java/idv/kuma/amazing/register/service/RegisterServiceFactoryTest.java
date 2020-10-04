package idv.kuma.amazing.register.service;

import idv.kuma.amazing.register.controller.Type;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class RegisterServiceFactoryTest {


    public RegisterServiceFactory factory;


    @Test
    void When_User_Selects_Email_Then_Return_Email_Service() {

        factory = new RegisterServiceFactory();

        runAndCheck(
                EmailDataChecker.class,
                EmailRegisterer.class,
                EmailMessenger.class
        );

    }


    private void runAndCheck(Class<EmailDataChecker> checkerClass, Class<EmailRegisterer> registererClass, Class<EmailMessenger> messengerClass) {
        RegisterService service = factory.create(Type.EMAIL);

        Assertions.assertThat(service.getChecker())
                .isNotNull()
                .isInstanceOf(checkerClass);
        Assertions.assertThat(service.getRegisterer())
                .isNotNull()
                .isInstanceOf(registererClass);
        Assertions.assertThat(service.getTokenGenerator())
                .isNotNull()
                .isInstanceOf(TokenGenerator.class);
        Assertions.assertThat(service.getMessenger())
                .isNotNull()
                .isInstanceOf(messengerClass);
    }
}