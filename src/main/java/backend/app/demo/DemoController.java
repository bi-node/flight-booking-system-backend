package backend.app.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DemoController {

    @GetMapping("/public")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello World from Security Demo");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> sayHelloToAdmin() {

        return ResponseEntity.ok("Hello World from Security Demo Admin");
    }

    @GetMapping("/user")
    public ResponseEntity<String> sayHellotoUser() {

        return ResponseEntity.ok("Hello World from Security Demo Admin and user");
    }
}
