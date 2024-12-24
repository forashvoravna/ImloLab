package uzb.lab.imlolab.service;


import org.springframework.http.ResponseEntity;
import uzb.lab.imlolab.dto.ResultDTO;
import uzb.lab.imlolab.payload.UserPayload;

public interface IUserService {

    ResponseEntity<ResultDTO> getAllUsers();

    ResponseEntity<ResultDTO> saveUser(UserPayload userPayload);

    ResponseEntity<ResultDTO> findUserById(Long id);

    ResponseEntity<ResultDTO> findUsersByName(String name);

    ResponseEntity<ResultDTO> findUserByName(String name);

    ResponseEntity<ResultDTO> findUserByEmail(String email);

    ResponseEntity<ResultDTO> findUsersByEmail(String email);

    ResponseEntity<ResultDTO> editUserById(Long id, UserPayload userPayload);

    ResponseEntity<ResultDTO> editUserByEmail(String email, UserPayload userPayload);

    ResponseEntity<ResultDTO> deleteUserById(Long id);

    ResponseEntity<ResultDTO> deleteUserByEmail(String email);

}
