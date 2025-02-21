package com.example.crudspringboot.services.v1;

import com.example.crudspringboot.base.exceptions.NotFoundException;
import com.example.crudspringboot.base.message.MessageLib;
import com.example.crudspringboot.base.validation.Validate;
import com.example.crudspringboot.repositories.UserRepository;
import com.example.crudspringboot.repositories.entities.UserEntity;
import com.example.crudspringboot.request.v1.UserRequestV1;
import com.example.crudspringboot.response.v1.UserResponseV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImplV1 implements UserServiceV1 {

    private final UserRepository userRepository;
    private final MessageLib messageLib;

    @Override
    public List<UserResponseV1> index() {
        List<UserEntity> users = userRepository.findAllByOrderByCreatedDateDesc();
        List<UserResponseV1> responses = new ArrayList<>();
        for (UserEntity user : users) {
            responses.add(responses(user));
        }
        return responses;
    }

    @Override
    public UserResponseV1 store(UserRequestV1 req) {
        Validate.c(req, Map.of(
                messageLib.getUserNameCantNull(), UserRequestV1::getUser_name,
                messageLib.getUserEmailCantNull(), UserRequestV1::getUser_email,
                messageLib.getUserPasswordCantNull(), UserRequestV1::getUser_password
        ));
        UserEntity user = new UserEntity();
        user.setUser_name(req.getUser_name());
        user.setUser_email(req.getUser_email());
        user.setUser_password(req.getUser_password());
        user.setUser_phone(req.getUser_phone());
        user.setCreatedBy(getCurentUser());
        user.setCreatedDate(getCreatedDate());

        UserEntity created = userRepository.save(user);
        return responses(created);
    }

    private UserResponseV1 responses(UserEntity entity) {
        return UserResponseV1.builder()
                .id(entity.getId())
                .user_name(entity.getUser_name())
                .user_email(entity.getUser_email())
                .user_password(entity.getUser_password())
                .user_phone(entity.getUser_phone())
                .active(entity.getActive())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .deletedDate(entity.getDeletedDate())
                .deletedBy(entity.getDeletedBy())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    private UserEntity user(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageLib.getUserIdNotFound()));
    }

    private String getCurentUser() {
        return "SYSTEM";
    }

    private String getModifiedByDelete() {
        return "SOFT DELETE";
    }

    private String getModifiedByUpdate() {
        return "UPDATE";
    }

    private Date getModifiedDate() {
        return new Date();
    }

    private Date getCreatedDate() {
        return new Date();
    }
}
