package com.assignment.service;

import com.assignment.dto.Data;
import com.assignment.dto.Order;
import com.assignment.dto.UserDataResponse;
import com.assignment.entity.Users;
import com.assignment.exceptions.OrderNotValidException;
import com.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users getUser(Order order) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "https://reqres.in/api/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<UserDataResponse> result =
                restTemplate.exchange(uri, HttpMethod.GET, entity, UserDataResponse.class);
        UserDataResponse userDataResponse = result.getBody();
        Users users = new Users();
        if (userDataResponse != null && userDataResponse.getData() != null) {
            Optional<Data> userDataOptional =
                    userDataResponse.getData().stream().filter(each -> each.getEmail().equals(order.getEmail())).findFirst();
            if (!userDataOptional.isPresent()) {
                throw new OrderNotValidException("Email Id is invalid");
            }
            Data userData = userDataOptional.get();
            users.setFirstname(userData.getFirstName());
            users.setLastName(userData.getLastName());
            users.setUserId(userData.getId());
            users.setEmail(userData.getEmail());
        }
        return users;
    }

    @Override
    public List<Users> getAllUsers(List<String> emails) {
        return userRepository.findAllByEmailIn(emails);
    }

    @Override
    public void save(Users users) {
        userRepository.save(users);
    }


}
