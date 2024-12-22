package uzb.lab.imlolab.service.implement;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uzb.lab.imlolab.dto.ResultDTO;
import uzb.lab.imlolab.entity.User;
import uzb.lab.imlolab.exception.AppBadRequestException;
import uzb.lab.imlolab.exception.ItemNotFoundException;
import uzb.lab.imlolab.payload.UserPayload;
import uzb.lab.imlolab.repository.UserRepository;
import uzb.lab.imlolab.service.IUserService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final ResultDTO dto = new ResultDTO();

    @Override
    public ResponseEntity<ResultDTO> getAllUsers() {
        List<User> users = userRepository.findAll(Sort.by("createdAt"));
        if (users.isEmpty()) {
            log.error("UserService.getAllUsers => {}", "account does not exist");
            throw new ItemNotFoundException("account does not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(dto.success(users));
    }

    @Override
    public ResponseEntity<ResultDTO> saveUser(UserPayload userPayload) {
        User user = userRepository.findUserByEmail(userPayload.getEmail());
        if (user != null) {
            log.error("UserService.saveUser => {}", "there is an account with this email");
            throw new AppBadRequestException("there is an account with this email");
        }
        return ResponseEntity.status(HttpStatus.OK).body(dto.success(userRepository.save(User.builder()
                .name(userPayload.getName())
                .email(userPayload.getEmail())
                .build())));
    }

    @Override
    public ResponseEntity<ResultDTO> findUserById(Long id) {
        User user = userRepository.findById(id).orElse(new User());
        if (user.getName() == null) {
            log.error("UserService.findUserById => {}", "user with this id was not found");
            throw new ItemNotFoundException("user with this id was not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(dto.success(user));
    }

    @Override
    public ResponseEntity<ResultDTO> findUsersByName(String name) {
        List<User> users = userRepository.findUsersByName(name);
        if (users.isEmpty()) {
            log.error("UserService.findUsersByName => {}", "user with this name was not found");
            throw new ItemNotFoundException("user with this name was not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(dto.success(users));
    }

    @Override
    public ResponseEntity<ResultDTO> findUserByName(String name) {
        List<User> users = userRepository.findUserByName(name);
        if (users.isEmpty()) {
            log.error("UserService.findUsersByName => {}", "user with this name was not found");
            throw new ItemNotFoundException("user with this name was not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(dto.success(users));
    }

    @Override
    public ResponseEntity<ResultDTO> findUsersByEmail(String email) {
        List<User> users = userRepository.findUsersByEmail(email);
        if (users.isEmpty()) {
            log.error("UserService.findUsersByEmail => {}", "user with this email was not found");
            throw new ItemNotFoundException("user with this email was not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(dto.success(users));
    }

    @Override
    public ResponseEntity<ResultDTO> findUserByEmail(String emial) {
        User user = userRepository.findUserByEmail(emial);
        if (user == null) {
            log.error("UserService.findUserByEmail => {}", "user with this email was not found");
            throw new ItemNotFoundException("user with this email was not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(dto.success(user));
    }

    @Override
    public ResponseEntity<ResultDTO> editUserById(Long id, UserPayload userPayload) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            log.error("UserService.editUserById => {}", "user with this id was not found");
            return new ItemNotFoundException("user with this id was not found");
        });
        user.setName(userPayload.getName());
        user.setEmail(userPayload.getEmail());
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(dto.success(user));
    }

    @Override
    public ResponseEntity<ResultDTO> editUserByEmail(String email, UserPayload userPayload) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            log.error("UserService.editUserByEmail => {}", "user with this email was not found");
            throw new ItemNotFoundException("user with this email was not found");
        }
        user.setName(userPayload.getName());
        user.setEmail(userPayload.getEmail());
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(dto.success(user));
    }

    @Override
    public ResponseEntity<ResultDTO> deleteUserById(Long id) {
        User user = userRepository.findById(id).orElse(new User());
        if (user.getName() == null) {
            log.error("UserService.deleteUserById => {}", "user with this id was not found");
            throw new ItemNotFoundException("user with this id was not found");
        }
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto.success("user deleted successfully"));
    }

    @Override
    public ResponseEntity<ResultDTO> deleteUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            log.error("UserService.deleteUserByEmail => {}", "user with this email was not found");
            throw new ItemNotFoundException("user with this email was not found");
        }
        userRepository.delete(user);
        return ResponseEntity.status(HttpStatus.OK).body(dto.success("user deleted successfully"));
    }

}