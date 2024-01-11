package com.ind.sevice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ind.entity.UserEntity;
import com.ind.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService , UserDetailsService{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepo userRepo;

	@Override
	public Integer saveUser(UserEntity user) {

		String encPwd = passwordEncoder.encode(user.getPassword());
		user.setPassword(encPwd);
		return userRepo.save(user).getUid();
	}

	@Override
	public Optional<UserEntity> getOneUser(Integer id) {
		return userRepo.findById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

		Optional<UserEntity> opt = userRepo.findByUserEmail(userEmail);
		if(opt.isEmpty()) {
			throw new UsernameNotFoundException(userEmail + " not exist");
		}
		UserEntity userEntity = opt.get();
		
		List<SimpleGrantedAuthority> authorities = userEntity.getUserRoles().stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
		return new User(userEmail, userEntity.getPassword(), authorities);
	}

}
