package com.hyunjung.finalproject.core.service;

import com.hyunjung.finalproject.core.domain.entity.User;
import com.hyunjung.finalproject.core.domain.entity.repository.UserRepository;
import com.hyunjung.finalproject.core.dto.UserCreatReq;
import com.hyunjung.finalproject.core.util.Encryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Encryptor encryptor;
    private final UserRepository userRepository;

    @Transactional
    public User creat(UserCreatReq userCreatReq) {
        userRepository.findByEmail(userCreatReq.getEmail())
                .ifPresent(u-> {
                    throw new RuntimeException("이미 존재하는 유저입니다.");
                });

        return userRepository.save(new User(
                userCreatReq.getName(),
                userCreatReq.getEmail(),
                encryptor.encrypt(userCreatReq.getPassword()),
                userCreatReq.getBirthday()
        ));
    }

    @Transactional
    public Optional<User> findPwMatchUser(String email, String password) {

        return userRepository.findByEmail(email)
                .map(user -> user.isMatch(encryptor, password) ? user : null);
    }
    @Transactional
    public User findByUserId(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("no user by id."));
    }
}
