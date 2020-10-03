package idv.kuma.amazing;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private static final java.lang.String template = "Hello, %s!";
    private RegisterService registerService;

    private Gson gson = new Gson();


    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @GetMapping("/v1/user/register")
    public Response greeting(@RequestParam(value = "name", defaultValue = "World") java.lang.String name) {

        return registerService.register();


    }
}
