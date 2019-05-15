package com.uNow.controller;


import com.uNow.repositories.MusicSpotApiRepository;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;



@RestController
@RequestMapping("/callback")
public class MusicSpotApiController {


    @Autowired
    private MusicSpotApiRepository musicSpotApiRepository;



    @RequestMapping
    @PostMapping
    public void spotifyAuth(@RequestParam("code") String code, @RequestParam("state") String state) throws IOException, EncoderException {
        if (state != null) {
            HttpPost post = new HttpPost("https://accounts.spotify.com/api/token?grant_type=authorization_code&code=" + code + "&redirect_uri=https%3A%2F%2Funow-api.herokuapp.com/%2Fcallback/%2FrefreshToken");
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
            post.setHeader("Authorization", "Basic" + new Base64().encode("d3dafa68bb434601b56cb999de50ce59".getBytes()) + ":" + new Base64().encode("57af408d7b3f43cc9fc3728a9101cb89".getBytes()));
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            closeableHttpClient.execute(post);
        }
    }
}
