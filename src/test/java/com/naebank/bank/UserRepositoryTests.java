package com.naebank.bank;

import com.naebank.bank.repository.UserRepository;
import com.naebank.bank.repository.entity.UserEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        UserEntity newUser = new UserEntity();
        newUser.setEmail("test@email.com");
        newUser.setPassword("1234");
        newUser.setName("name");
        newUser.setRole("role");
        UserEntity userEntity = userRepository.save(newUser);

        UserEntity existUser = entityManager.find(UserEntity.class, userEntity.getId());

        assertThat(userEntity.getEmail()).isEqualTo(existUser.getEmail());
    }

    @Test
    public void testFindByEmail() {
        Optional<UserEntity> userEntity = userRepository.findByEmail("test@email.com");

        assertThat(userEntity.isPresent()).isEqualTo(true);

    }
}
