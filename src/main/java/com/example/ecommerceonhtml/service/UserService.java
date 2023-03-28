package com.example.ecommerceonhtml.service;

import com.example.ecommerceonhtml.DTO.UserDTO;
import com.example.ecommerceonhtml.entity.User;
import com.example.ecommerceonhtml.exception.DuplicateRecordException;
import com.example.ecommerceonhtml.exception.RecordNotFoundException;
import com.example.ecommerceonhtml.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    private User DtoToEntity(UserDTO dto){
        return modelMapper.map(dto,User.class);
    }
    private UserDTO EntityToDto(User entity){
        return modelMapper.map(entity,UserDTO.class);
    }




    public UserDTO save(UserDTO userDTO) {


            userDTO.setIsActive(true);
            return this.EntityToDto(userRepository.save(this.DtoToEntity(userDTO)));

//        throw new DuplicateRecordException(String.format("This record is already exist on => %d",isFound.getId()));
    }

    public List<UserDTO> findAll() {
        List<User> categories= userRepository.findAll();
        List<UserDTO> userDTOS=new ArrayList<>();
        categories.stream().forEach(entity-> userDTOS.add(this.EntityToDto(entity)));
        return userDTOS;
    }

    public void delete(Long id) {
        Optional<User> isFound=userRepository.findById(id);
        if(!ObjectUtils.isEmpty(isFound)) {
            userRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException(String.format("User not found on => %d",id));
        }
    }

    public UserDTO update(UserDTO userDTO,Long id) {
//        User isFound=userRepository.findByUserName(this.DtoToEntity(userDTO).getUserName());
        Optional<User> forUpdate=userRepository.findById(id);

         if(ObjectUtils.isEmpty(forUpdate))
        {
            throw new RecordNotFoundException(String.format("User not found on => %d",id));

        }
        else {
            forUpdate.get().setName(userDTO.getName());
            return this.EntityToDto(userRepository.save(forUpdate.get()));
        }
    }

    public UserDTO setActive(Long id) {
        Optional<User> isFound=userRepository.findById(id);
        if(!ObjectUtils.isEmpty(isFound)) {
            isFound.get().setIsActive(true);
            return this.EntityToDto(userRepository.save(isFound.get()));
        }
        throw new RecordNotFoundException(String.format("User not found on => %d",id));
    }

    public UserDTO setDeactivate(Long id) {
        Optional<User> isFound=userRepository.findById(id);
        if(!ObjectUtils.isEmpty(isFound)) {
            isFound.get().setIsActive(false);
            return this.EntityToDto(userRepository.save(isFound.get()));
        }
        throw new RecordNotFoundException(String.format("User not found on => %d",id));
    }

    public UserDTO findById(Long id) {
        Optional<User> isFound=userRepository.findById(id);
        if(!ObjectUtils.isEmpty(isFound)) {
            return this.EntityToDto(isFound.get());
        }
        throw new RecordNotFoundException(String.format("User not found on => %d",id));
    }
}
