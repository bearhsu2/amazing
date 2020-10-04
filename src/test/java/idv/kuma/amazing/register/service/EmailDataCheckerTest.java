package idv.kuma.amazing.register.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class EmailDataCheckerTest {

    @Test
    void When_Password_Too_Short_Then_Throw_Exception() {

        EmailDataChecker checker = new EmailDataChecker();

        try {
            checker.check(new RegisterData("AnyName", "AnyEmail", "SHORT"));
            fail("Should throw exception.");
        } catch (CheckerException e) {
            Assertions.assertThat(e)
                    .hasMessage("Password too short.");
        }

    }
}