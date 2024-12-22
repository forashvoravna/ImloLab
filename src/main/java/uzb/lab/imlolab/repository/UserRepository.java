package uzb.lab.imlolab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uzb.lab.imlolab.entity.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM  account  WHERE LOWER(name) LIKE LOWER(CONCAT('%', :name, '%'))", nativeQuery = true)
    List<User> findUsersByName(@Param(value = "name") String name);

    @Query(value = "SELECT * FROM  account  WHERE name LIKE LOWER(:name)", nativeQuery = true)
    List<User> findUserByName(@Param(value = "name") String name);

    @Query(value = "SELECT * FROM  account  WHERE LOWER(email) LIKE LOWER(CONCAT('%', :email, '%'))", nativeQuery = true)
    List<User> findUsersByEmail(@Param(value = "email") String email);

    User findUserByEmail(String email);
}
