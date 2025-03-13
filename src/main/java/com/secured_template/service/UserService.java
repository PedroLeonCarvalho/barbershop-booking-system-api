package com.secured_template.service;

import com.secured_template.dto.UserDto;
import com.secured_template.domain.User;
import com.secured_template.repository.RoleRepository;
import com.secured_template.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto data) {
        User newUser = new User(data);
        newUser.setName(data.name());
        newUser.setEmail(data.email());
        newUser.setEnabled(true);
        newUser.setBirthDate(data.birthDate());
        newUser.setPhoneNumber(data.phoneNumber());
        newUser.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        newUser.setPassword(passwordEncoder.encode(data.password()));

        userRepository.save(newUser);
        return convertToUserDto(newUser);
    }
    public UserDto createStaffUser(UserDto data) {
        User newUser = new User(data);
        newUser.setName(data.name());
        newUser.setEmail(data.email());
        newUser.setEnabled(true);
        newUser.setBirthDate(data.birthDate());
        newUser.setPhoneNumber(data.phoneNumber());
        newUser.setRoles(Arrays.asList(roleRepository.findByName("ROLE_STAFF")));
        newUser.setPassword(passwordEncoder.encode(data.password()));

        userRepository.save(newUser);
        return convertToUserDto(newUser);
    }

public  UserDto convertToUserDto (User user) {
        UserDto dto = new UserDto(user.getId(), user.getName(), user.getEmail(),user.getPhoneNumber(), user.getBirthDate(), user.getPassword(), user.getProfilePicture());
        return dto;
}

    public List<UserDto> getBarbersList() {

         List<User> barbers =userRepository.findUserByRoleId(1l,2l);
            var dtoList =  barbers.stream().
                map(this::convertToUserDto).collect(Collectors.toUnmodifiableList());

         return dtoList;


    }



}
