package com.bookshop.bookshop.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bookshop.bookshop.dao.UserDaoInterface;
import com.bookshop.bookshop.entity.User;
import com.bookshop.bookshop.exception.InvalidAccountStatusException;
import com.bookshop.bookshop.payload.LoginDto;
import com.bookshop.bookshop.security.JwtTokenProvider;

@Service
public class LoginService implements LoginServiceInterface {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserDaoInterface userDao;


    public LoginService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserDaoInterface userDao) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDao = userDao;
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
        User user = userDao.findByUserName(username);
        String accountStatus = user.getAccountStatus();

        if (accountStatus == null)
        {   
            user.setAccountStatus("active");
            userDao.save(user);
        }
        else if (accountStatus.contains("suspended") || accountStatus.contains("blocked"))
        {
            throw new InvalidAccountStatusException(accountStatus);
        } 

        String jwtToken = jwtTokenProvider.generateJwtToken(authentication);

        return jwtToken;
    }

}
