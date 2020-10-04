package idv.kuma.amazing.register.controller;


import idv.kuma.amazing.ServiceException;
import idv.kuma.amazing.register.service.RegisterData;
import idv.kuma.amazing.register.service.RegisterService;
import idv.kuma.amazing.register.service.RegisterServiceFactory;
import idv.kuma.amazing.register.service.RegisterServiceFactoryException;
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
    public Response register(@ModelAttribute RegisterForm registerForm) {

        try {
            RegisterService registerService = factory.create(registerForm.getType());
            RegisterData registerData = registerForm.toRegisterData();
            return new SuccessResponse(registerService.register(registerData));
        } catch (ServiceException | RegisterServiceFactoryException e) {
            return new ErrorResponse(e.getMessage());
        }

    }

}
