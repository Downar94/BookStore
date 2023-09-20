package com.bookshop.bookshop;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bookshop.bookshop.dao.UserDao;
import com.bookshop.bookshop.service.UserService;
import com.bookshop.bookshop.entity.User;
import com.bookshop.bookshop.entity.guest.WebGuest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(SpringExtension.class)
public class UserServiceTests {
    
    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

 /*   @Test
    public void findAllFromDaoTest(){
        when(userDao.findAll()).thenReturn(Arrays.asList(
            new User("harrypotter123", "haslo123", "Harry","Potter","harry_potter@email.com"),
            new User("littleprince563", "password1234", "Little", "Prince", "little_prince@email.com")
        ));

        List<User> users = userService.listUsers();

        assertEquals("Harry", users.get(0).getFirstName());
        assertEquals("littleprince563", users.get(1).getUserName());
    }
 
    @Test
    public void getByUserIdTest(){
        User user = new User("harrypotter123", "haslo123", "Harry","Potter","harry_potter@email.com");
        when(userDao.findAll()).thenReturn(Arrays.asList(user));
        when(userDao.findById(1L)).thenReturn(user);

        User userServiceResult = userService.getByUserId(1L);
        assertEquals(user.getLastName(), userServiceResult.getLastName());
    }

    @Test
    public void saveTest(){
        User user = new User("harrypotter123", "3haslo123", "Harry","Potter","harry_potter@email.com");
        when(userDao.findAll()).thenReturn(Arrays.asList(user));
        when(userDao.findById(1L)).thenReturn(user);

        WebGuest newUser = new WebGuest("littleprince563", "password1234", "Little", "Prince", "little_prince@email.com");
        userService.save(newUser);
        verify(userDao,times(1)).save(user);
    }*/ 
 /* 
    @Test
    public void updateUserTest(){
        User user = new User("harrypotter123", "haslo123", "Harry","Potter","harry_potter@email.com");
        when(userDao.findAll()).thenReturn(Arrays.asList(user));
        when(userDao.findById(1L)).thenReturn(user);

        User newUser = new User("littleprince563", "password1234", "Little", "Prince", "little_prince@email.com");
        userService.updateUser(newUser, 1L);

        assertEquals(userDao.findById(1L).getEmail(), newUser.getEmail());
    }
 */
    
}
