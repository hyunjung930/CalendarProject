package com.hyunjung.finalproject.api.service;

import com.hyunjung.finalproject.api.dto.LoginReq;
import com.hyunjung.finalproject.api.dto.SignUpReq;
import com.hyunjung.finalproject.core.domain.entity.User;
import com.hyunjung.finalproject.core.dto.UserCreatReq;
import com.hyunjung.finalproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    public final static String LOGIN_SESSION_KEY = "USER_ID";
    private final UserService userService;

    @Transactional
    public void signUp(SignUpReq signUpReq, HttpSession session){
        /**
         * UserService에 Create을 담당한다. ( 이미 존재하는 경우의 유저 검증은 userService의 몫)
         * 생성이 되면 sesssion에 담고 리턴
         */
        final User user = userService.creat(new UserCreatReq(
                signUpReq.getName(),
                signUpReq.getEmail(),
                signUpReq.getPassword(),
                signUpReq.getBirthday()
        ));
        session.setAttribute(LOGIN_SESSION_KEY, user.getId());

    }

    @Transactional
    public void login(LoginReq loginReq, HttpSession session){
        /**
         * 세션 값이 있으면 리턴
         * 세션 값이 없으면 비밀번호 체크 후에 로그인 & 세션에 담고 리턴
         */
        final Long userId = (Long) session.getAttribute(LOGIN_SESSION_KEY);
        if(userId != null){
            return;
        }
        final Optional<User> user =
                userService.findPwMatchUser(loginReq.getEmail(),loginReq.getPassword());
        if(user.isPresent()){
            session.setAttribute(LOGIN_SESSION_KEY,user.get().getId());
        }else{
            throw new RuntimeException("비밀번호 또는 이메일이 맞지 않습니다.");
        }

    }


    public void logout(HttpSession session){
        /**
         * 세션 제거 하고 끝
         */
        session.removeAttribute(LOGIN_SESSION_KEY);
    }

}
