package idv.kuma.amazing.register.controller;


import idv.kuma.amazing.RegisterException;
import idv.kuma.amazing.register.*;
import idv.kuma.amazing.register.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private static final java.lang.String template = "Hello, %s!";

    private RegisterServiceFactory factory;


    @Autowired
    public RegisterController(RegisterServiceFactory factory) {
        this.factory = factory;
    }


    @PostMapping("/v1/user/register")
    public Response greeting(@ModelAttribute RegisterForm registerForm) {

        try {

            RegisterService registerService = factory.create(registerForm);
            return new SuccessResponse(registerService.register(registerForm));

        } catch (RegisterException e) {
            return new ErrorResponse(e.getMessage());
        }

    }

}
