package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserServer {

    @Autowired
    private UserRepository userRepository;
}
