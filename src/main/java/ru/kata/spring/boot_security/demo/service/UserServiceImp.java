package ru.kata.spring.boot_security.demo.service;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;
import java.util.Optional;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Data
@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService{

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

//    @Contract(pure = true)
    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
   public List<User> findAll() {
        return userRepository.findAll();
   }

   public User findOne(long id) {
       Optional<User> foundUser = userRepository.findById(id);
       return foundUser.orElse(null);
   }

   @Transactional
    public boolean save(@NotNull User user){
        User userMyDb = userRepository.findByEmail(user.getEmail());
        if (userMyDb != null) {
            return false;
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }
   }

   @Transactional
    public void update(User user) {
        userRepository.save(user);
   }

   @Transactional
    public boolean delete(long id) {
        if (userRepository.findById(id).isPresent()){
        userRepository.deleteById(id);
        return true;
        }
        return false;
   }
}
