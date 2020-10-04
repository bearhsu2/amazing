package idv.kuma.amazing.register.service;

import idv.kuma.amazing.register.controller.Type;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class RegisterServiceFactoryTest {


    public RegisterServiceFactory factory;


    @Test
    void When_User_Selects_Email_Then_Return_Email_Service() throws RegisterServiceFactoryException {

        factory = new RegisterServiceFactory();

        runAndCheck(
                Type.EMAIL,
                EmailDataChecker.class,
                EmailRegisterer.class,
                EmailMessenger.class
        );

    }


    private void runAndCheck(Type type, Class checkerClass, Class registererClass, Class messengerClass) throws RegisterServiceFactoryException {
        RegisterService service = factory.create(type);

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


    @Test
    void When_User_Selects_Phone_Then_Return_Phone_Service() throws RegisterServiceFactoryException {

        factory = new RegisterServiceFactory();

        runAndCheck(
                Type.PHONE,
                PhoneDataChecker.class,
                PhoneRegisterer.class,
                PhoneMessenger.class
        );

    }
}