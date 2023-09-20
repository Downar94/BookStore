package com.bookshop.bookshop.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookshop.bookshop.dao.RoleDaoInterface;
import com.bookshop.bookshop.dao.UserDaoInterface;
import com.bookshop.bookshop.entity.User;
import com.bookshop.bookshop.entity.guest.WebGuest;
import com.bookshop.bookshop.exception.NotAllowedResourcesException;
import com.bookshop.bookshop.exception.UserNotFoundException;
import com.bookshop.bookshop.entity.Contact;
import com.bookshop.bookshop.entity.Order;
import com.bookshop.bookshop.entity.Role;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {
	private UserDaoInterface userDao;

	private RoleDaoInterface roleDao;

    private PasswordEncoder passwordEncoder;    
	
	@Autowired
	public UserService(UserDaoInterface userDao, RoleDaoInterface roleDao, PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User findByUserName(String userName) {
		User user = userDao.findByUserName(userName);
		return user;
	}

	@Override
    public List<User> listUsers() {
		return userDao.findAll();
	}

	@Override
    public User getByUserId(Long id) {
		if(userDao.findById(id) == null){
			throw new UserNotFoundException(id);
		}
        return userDao.findById(id);
    }

	@Override
	public void save(WebGuest webGuest) {
		User user = new User();
		Contact contact = new Contact();
		Order order = new Order();
		order.setStatus("not_placed");

		user.setUserName(webGuest.getUserName());
		user.setPassword(passwordEncoder.encode(webGuest.getPassword()));
		user.setFirstName(webGuest.getFirstName());
		user.setLastName(webGuest.getLastName());
		user.setEmail(webGuest.getEmail());

		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_USER")));

		user.setContact(contact);
	
		userDao.save(user);

	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

        Set<GrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(),
                authorities);
	}

	private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Role tempRole : roles) {
			SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(tempRole.getName());
			authorities.add(tempAuthority);
		}

		return authorities;
	}

	@Override
    public User updateUser(User user) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User updated = userDao.findByUserName(username);

		if(user.getFirstName() != null) updated.setFirstName(user.getFirstName());
		if(user.getLastName() != null) updated.setLastName(user.getLastName());
		if(user.getEmail() != null) updated.setEmail(user.getEmail());
		if(user.getPassword() != null) updated.setPassword(passwordEncoder.encode(user.getPassword()));
		if(user.getUserName() != null) updated.setUserName(user.getUserName());
		userDao.save(updated);
		return updated;
	}

	@Override
	public void blockUser(Long id) {
		User blocked = userDao.findById(id);

		if(blocked == null){
			throw new UserNotFoundException(id);
		}

		Collection<Role> roles = blocked.getRoles();
		for (Role role : roles) 
		{	
			if (role.getName().contains("ROLE_ADMIN"))
			{
				throw new NotAllowedResourcesException();
			}
		}

		blocked.setAccountStatus("blocked");
		userDao.save(blocked);
	}

	@Override
	public void suspendAccount() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User suspended = userDao.findByUserName(username);

		Collection<Role> roles = suspended.getRoles();
		for (Role role : roles) 
		{	
			if (role.getName().contains("ROLE_ADMIN"))
			{
				throw new NotAllowedResourcesException();
			}
		}

		suspended.setAccountStatus("suspended");
		userDao.save(suspended);
	}

	@Override
    public User updateUserByAdmin(User user, Long id) {
		User updated = userDao.findById(id);
		if(updated == null){
			throw new UserNotFoundException(id);
		}
		if(user.getFirstName() != null) updated.setFirstName(user.getFirstName());
		if(user.getLastName() != null) updated.setLastName(user.getLastName());
		if(user.getEmail() != null) updated.setEmail(user.getEmail());
		if(user.getPassword() != null) updated.setPassword(passwordEncoder.encode(user.getPassword()));
		if(user.getUserName() != null) updated.setUserName(user.getUserName());
		userDao.save(updated);
		return updated;
	}
}
