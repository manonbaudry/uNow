package com.uNow.controller;


import com.uNow.repositories.SpotifyApiRepository;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/callback")
public class SpotifyApiController {


    @Autowired
    private SpotifyApiRepository spotifyApiRepository;

    @RequestMapping
    @GetMapping
    public void getAuth(@RequestParam("code") String code, @RequestParam("state") String state) {

    }

    @RequestMapping
    @PostMapping
    public void spotifyAuth(@RequestParam("code") String code, @RequestParam("state") String state) throws IOException {
        if (state != null) {
            HttpPost post = new HttpPost("https://accounts.spotify.com/api/token?client_id=d3dafa68bb434601b56cb999de50ce59&client_secret=57af408d7b3f43cc9fc3728a9101cb89&grant_type=authorization_code&code=" + code + "&redirect_uri=https%3A%2F%2Funow-api.herokuapp.com/%2Fcallback/%2FrefreshToken");
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            closeableHttpClient.execute(post);
        }
    }
}
