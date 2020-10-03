package idv.kuma.amazing;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private static final String template = "Hello, %s!";


    @GetMapping("/v1/user/register")
    public Response greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Response("aaaabbbb");
    }
}
