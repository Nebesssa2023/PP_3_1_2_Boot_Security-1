package ru.kata.spring.boot_security.demo.service;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceJpaImp implements UserServiceJpa{

    UserRepository userRepository;

    @Autowired
    public UserServiceJpaImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
   public List<User> findAll() {
        return userRepository.findAll();
   }
   @Override
   public User findOne(long id) {
       Optional<User> foundUser = userRepository.findById(id);
       return foundUser.orElse(null);
   }

   @Transactional
   @Override
    public boolean save(@NotNull User user){
        User userMyDb = userRepository.findByUsername(user.getUsername());
        if (userMyDb == null) {
            return false;
        }else {
            userRepository.save(userMyDb);
            return true;
        }
   }

   @Transactional
   @Override
    public void update(User user) {
        userRepository.save(user);
   }

   @Transactional
   @Override
    public boolean delete(long id) {
        if (userRepository.findById(id).isPresent()){
        userRepository.deleteById(id);
        return true;
        }
        return false;
   }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
