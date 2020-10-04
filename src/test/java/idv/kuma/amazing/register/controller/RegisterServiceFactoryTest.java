package idv.kuma.amazing.register.controller;

import idv.kuma.amazing.register.service.*;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.fail;


class RegisterServiceFactoryTest {


    public RegisterServiceFactory factory;


    @Test
    void When_User_Selects_Email_Then_Return_Email_Service() throws RegisterServiceFactoryException {

        factory = new RegisterServiceFactory();

        runAndCheck(
                Type.EMAIL,
                EmailRegisterer.class,
                EmailMessenger.class
        );

    }


    private void runAndCheck(Type type, Class registererClass, Class messengerClass) throws RegisterServiceFactoryException {
        RegisterService service = factory.create(type);

        Assertions.assertThat(service.getChecker())
                .isNotNull()
                .isInstanceOf(DataChecker.class);
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
                PhoneRegisterer.class,
                PhoneMessenger.class
        );

    }


    @Test
    void When_Type_Unsupported_Then_Throw_Exception() {

        factory = new RegisterServiceFactory();

        try {
            RegisterService service = factory.create(null);
            fail("Should throw exception.");
        } catch (RegisterServiceFactoryException e) {
            Assert.assertEquals("type.unprovided", e.getMessage());
        }

    }
}