package org.isj.ing4.isi.music.service;


import org.isj.ing4.isi.music.dto.UserDto;
import org.isj.ing4.isi.music.mapper.UserMapper;
import org.isj.ing4.isi.music.model.Role;
import org.isj.ing4.isi.music.model.User;
import org.isj.ing4.isi.music.repository.RoleRepository;
import org.isj.ing4.isi.music.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;


@Service("userService")
public class UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserMapper userMapper;

	@Autowired
	public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public UserDto findUserByEmailDto(String email) {
		return userMapper.toDto(userRepository.findByEmail(email));
	}

	public User saveClient(UserDto userdto) {

		User user = new User();
		user = userMapper.toEntity(userdto);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);

		final Role userRole = roleRepository.findByRole("client_ddd_@dd");

		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));


		return userRepository.save(user);
	}

}