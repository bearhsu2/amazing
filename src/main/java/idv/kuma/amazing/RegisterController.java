package idv.kuma.amazing;


import com.google.gson.Gson;
import com.google.gson.stream.JsonToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    private static final java.lang.String template = "Hello, %s!";
    private RegisterService registerService;

    private Gson gson = new Gson();


    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @PostMapping("/v1/user/register")
    public Response greeting(@ModelAttribute RegisterForm registerForm) {

        System.out.println(registerForm.getName());



        return registerService.register();

    }

//    public Echo setterMessage2(@ModelAttribute Message message) {
//        return new Echo(counter.incrementAndGet(), String.format(echoTemplate2, message.getFrom(), message.getTo(), message.getContent()));
//    }
}
