package com.uNow.controller;

import com.uNow.entities.User;
import com.uNow.exceptions.AlreadyExistEmailException;
import com.uNow.exceptions.UserNotFoundException;
import com.uNow.repositories.UserRepository;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public User findById(@PathVariable("id") long id) throws UserNotFoundException {
        if (userRepository.findById(id) == null) {
            throw new UserNotFoundException();
        }
        return userRepository.findById(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void userNotFoundHandler(UserNotFoundException e){}

    @CrossOrigin
    @PostMapping
    public User createUser(@RequestBody User user) throws AlreadyExistEmailException {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
            return user;
        }
        throw new AlreadyExistEmailException("The given email is already in use");
    }

    @CrossOrigin
    @PutMapping
    public User updateUserInformation(@RequestBody User userUpdated) {
        User userToUpdate = userRepository.findById(userUpdated.getId());
        userToUpdate.updateWith(userUpdated);
        userRepository.save(userToUpdate);
        return userRepository.findById(userUpdated.getId());
    }

    @CrossOrigin
    @PostMapping("/callBack/{id}")
    public void spotifyAuth(@PathVariable("id") long id, @RequestParam("code") String code, @RequestParam("state") String state) throws IOException {
        if (state != null) {
            HttpPost post = new HttpPost("https://accounts.spotify.com/api/token?grant_type=authorization_code&code=" + code + "&redirect_uri=https%3A%2F%2Funow-api.herokuapp.com/%2FrefreshToken/%2F" + id);
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
            post.setHeader("Authorization", "Basic" + new Base64().encode("d3dafa68bb434601b56cb999de50ce59".getBytes()) + ":" + new Base64().encode("57af408d7b3f43cc9fc3728a9101cb89".getBytes()));
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            closeableHttpClient.execute(post);
        }
    }

    @CrossOrigin
    @GetMapping("/refreshToken/{id}")
    public void getAccessToken(@PathVariable("id") long id, @RequestParam("access_token") String accessToken, @RequestParam("token_type") String tokenType, @RequestParam("scope") String scope, @RequestParam("expires_in") int expiresIn, @RequestParam("refresh_token") String refreshToken) {
        userRepository.findById(id).setRefreshToken(refreshToken);
    }

}
