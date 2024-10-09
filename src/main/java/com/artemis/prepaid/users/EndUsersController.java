package com.artemis.prepaid.users;

import com.artemis.prepaid.shared.RabbitMqPublisher;
import com.artemis.prepaid.users.models.EndUser;
import com.artemis.prepaid.users.services.EndUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class EndUsersController {

    private final EndUserService endUserService;
    private final RabbitMqPublisher rabbitMqPublisher;

    public EndUsersController(EndUserService userService, RabbitMqPublisher rabbitMqPublisher) {
        this.endUserService = userService;
        this.rabbitMqPublisher = rabbitMqPublisher;
    }


    @GetMapping
    public ResponseEntity<List<EndUser>> getAllEndUsers(){
        return new ResponseEntity<>(endUserService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EndUser> createUser(@RequestBody EndUser user) {

        var endUser = endUserService.saveUser(user);
        rabbitMqPublisher.sendMessage("endUser-created-queue", endUser);
        return new ResponseEntity<>(endUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EndUser> findEnduserById(@PathVariable UUID id){
        return new ResponseEntity<>(endUserService.findById(id),HttpStatus.OK);
    }
}
