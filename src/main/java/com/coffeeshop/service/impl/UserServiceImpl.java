package com.coffeeshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffeeshop.dto.OrderDTO;
import com.coffeeshop.dto.UserDTO;
import com.coffeeshop.entity.Order;
import com.coffeeshop.entity.User;
import com.coffeeshop.exception.OrderNotFoundException;
import com.coffeeshop.exception.UserNotFoundException;
import com.coffeeshop.repository.UserRepository;
import com.coffeeshop.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO saveUpdateUser(UserDTO userDTO) {
        log.info("saveUpdateUser method started user name :: "+userDTO.getName());
        User user = modelMapper.map(userDTO,User.class);
          User savedUser = userRepository.saveAndFlush(user);
          UserDTO savedUserDTO = modelMapper.map(savedUser,UserDTO.class);
        log.info("User Saved Successfully userId :: "+savedUser.getId());
        return savedUserDTO;
    }

    @Override
    public List<UserDTO> getUsers() {
        log.info("getUser method started.");
        List<User> users = (List<User>) userRepository.findAll();
        List<UserDTO> userDTOS = users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        log.info("getUser method completed total users :: "+users.size());
        return userDTOS;
    }

    @Override
    public void removeUserDetailsById(Long id) {
        log.info("removeUser method started user id ::"+id);
        getUserDetailsById(id);
        userRepository.deleteById(id);
        log.info("removeUser method ended");
    }
    @Override
    public UserDTO getUserDetailsById(Long id) {
        log.info("getUser method started user id ::"+id);
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            log.error("getUser user not found with id ::"+id);
            throw new UserNotFoundException("user not found with id ::"+id);
        }
        User user = userOptional.get();
        UserDTO userDTO = modelMapper.map(user,UserDTO.class);
        log.info("getUser method ended");
        return userDTO;
    }

}
