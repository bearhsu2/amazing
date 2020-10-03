package idv.kuma.amazing.register;


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

        Response response = registerService.register(registerForm);

        return response;

    }

}
