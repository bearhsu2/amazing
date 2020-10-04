package idv.kuma.amazing.register.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class DataCheckerTest {

    @Test
    void When_ReEnterPassword_Incorrect_Then_Throw_Exception() {

        try {
            runChecker("GOOD_PASSWORD", "DIFFERENT_PASSWORD");
            fail("Should throw exception.");
        } catch (CheckerException e) {
            Assertions.assertThat(e)
                    .hasMessage("Re-enter Password incorrect.");
        }

    }


    private void runChecker(String password, String confirmPassword) throws CheckerException {
        new DataChecker().check(new RegisterData("AnyName", "AnyEmail", password, confirmPassword));
    }


    @Test
    void When_Password_Empty_Then_Throw_Exception() {

        try {
            runChecker("", "");
            fail("Should throw exception.");
        } catch (CheckerException e) {
            Assertions.assertThat(e)
                    .hasMessage("Password too short.");
        }

    }


    @Test
    void When_Password_Too_Short_Then_Throw_Exception() {

        try {
            runChecker("SHORT", "SHORT");
            fail("Should throw exception.");
        } catch (CheckerException e) {
            Assertions.assertThat(e)
                    .hasMessage("Password too short.");
        }

    }
}