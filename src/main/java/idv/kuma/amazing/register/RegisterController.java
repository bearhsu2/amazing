package idv.kuma.amazing.register;


import idv.kuma.amazing.RegisterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private static final java.lang.String template = "Hello, %s!";
    private RegisterService registerService;


    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @PostMapping("/v1/user/register")
    public Response greeting(@ModelAttribute RegisterForm registerForm) {

        try {
            return registerService.register(registerForm);
        } catch (RegisterException e) {
            return new ErrorResponse(e.getMessage());
        }

    }

}
