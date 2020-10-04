package idv.kuma.amazing.register.controller;


import idv.kuma.amazing.ServiceException;
import idv.kuma.amazing.register.service.RegisterData;
import idv.kuma.amazing.register.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class RegisterController {


    private RegisterServiceFactory factory;
    private MessageSource messageSource;


    @Autowired
    public RegisterController(RegisterServiceFactory factory, MessageSource messageSource) {
        this.factory = factory;
        this.messageSource = messageSource;
    }


    @PostMapping("/v1/user/register")
    public Response register(
            @RequestHeader(name = "Accept-Language", required = false) Locale locale,
            @ModelAttribute RegisterForm registerForm) {

        try {
            RegisterService registerService = factory.create(registerForm.getType());
            RegisterData registerData = registerForm.toRegisterData();
            return new SuccessResponse(registerService.register(registerData));
        } catch (ServiceException | RegisterServiceFactoryException e) {

            String message = messageSource.getMessage(e.getMessage(), null, locale);
            return new ErrorResponse(message);
        }

    }

}
