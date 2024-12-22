package uzb.lab.imlolab.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzb.lab.imlolab.dto.ResultDTO;
import uzb.lab.imlolab.payload.UserPayload;
import uzb.lab.imlolab.service.implement.UserService;


@CrossOrigin
@RestController
@RequestMapping("/imlo/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<ResultDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/save")
    public ResponseEntity<ResultDTO> saveUser(@RequestBody @Valid UserPayload userPayload) {
        return userService.saveUser(userPayload);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ResultDTO> findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<ResultDTO> findUserByName(@PathVariable String name) {
        return userService.findUserByName(name);
    }

    @GetMapping("/find-users-by-name/{name}")
    public ResponseEntity<ResultDTO> findUsersByName(@PathVariable String name) {
        return userService.findUsersByName(name);
    }

    @GetMapping("/find-user-by-email/{email}")
    public ResponseEntity<ResultDTO> findUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

    @GetMapping("/find-users-by-email/{email}")
    public ResponseEntity<ResultDTO> findUsersByEmail(@PathVariable String email) {
        return userService.findUsersByEmail(email);
    }

    @PutMapping("/edit-by-id/{id}")
    public ResponseEntity<ResultDTO> editUsersById(@PathVariable Long id, @RequestBody @Valid UserPayload userPayload) {
        return userService.editUserById(id, userPayload);
    }

    @PutMapping("/edit-by-email/{email}")
    public ResponseEntity<ResultDTO> editUsersByEmail(@PathVariable String email, @RequestBody @Valid UserPayload userPayload) {
        return userService.editUserByEmail(email, userPayload);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<ResultDTO> deleteUserById(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }

    @DeleteMapping("/delete-by-email/{email}")
    public ResponseEntity<ResultDTO> deleteUserByEmail(@PathVariable String email) {
        return userService.deleteUserByEmail(email);
    }

}