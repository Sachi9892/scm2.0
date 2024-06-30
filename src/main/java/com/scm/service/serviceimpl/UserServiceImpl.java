package com.scm.service.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.entity.User;
import com.scm.helpers.AppConstants;
import com.scm.helpers.ResourceNotFound;
import com.scm.repository.UserRepository;
import com.scm.service.UserService;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        
        String id = UUID.randomUUID().toString();
        user.setUserId(id);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //Define role of the user
        user.setRoleList(List.of(AppConstants.ROLE_USER));

        return  userRepo.save(user);
        
    }

    @Override
    public Optional<User> getUserById(String id) {
        
         User user = userRepo.findById(id).orElseThrow(() -> 
        new ResourceNotFound("User Not Found Fot Th e Given Id "  + id));

        return Optional.of(user);

    }

    @Override
    public Optional<User> updateUser(User user) {
        

        User userToUpdate = userRepo.findById(user.getUserId()).orElseThrow(() ->
             new ResourceNotFound("No user with given id" + user.getUserId()));

        userToUpdate.setName(user.getName());
        userToUpdate.setAbout(user.getAbout());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPhoneNumber(user.getPhoneNumber());
        userToUpdate.setProfilePic(user.getProfilePic());

        return Optional.of(userToUpdate);

    }


    @Override
    public void deleteUser(String id) {
        
        User userToDelete = userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("No user with id : " + id));

        userRepo.delete(userToDelete);

    }


    @Override
    public boolean isUserExist(String userId) {
        
        User isUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("No user found with id : " + userId));

        return isUser == null;

    }


    @Override
    public boolean isUserExistByEmail(String email) {
        
        User emailUser = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFound("No user with email : " + email));

        return emailUser == null;

    }


    @Override
    public List<User> getAllUsers() {
        
        List<User> allUser = userRepo.findAll();
        return allUser;

    }



    @Override
    public User getUserByEmail(String email) {
        
        User emailUser = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFound("No user with email : " + email));

        return emailUser;
        
    }
    
}
