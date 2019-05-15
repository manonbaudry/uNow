package com.uNow.controller;


import com.uNow.repositories.MusicSpotApiRepository;
import com.uNow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/callback")
public class MusicSpotApiController {


    @Autowired
    private MusicSpotApiRepository musicSpotApiRepository;

    @Autowired
    private UserRepository userRepository;


/*
    @RequestMapping
    @PostMapping
    public void spotifyAuth(@RequestParam("code") String code, @RequestParam("state") String state) throws IOException {
        if (state != null) {
            HttpPost post = new HttpPost("https://accounts.spotify.com/api/token?grant_type=authorization_code&code=" + code + "&redirect_uri=https%3A%2F%2Funow-api.herokuapp.com/%2Fcallback/%2FrefreshToken");
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
            post.setHeader("Authorization", "Basic" + new Base64().encode("d3dafa68bb434601b56cb999de50ce59".getBytes()) + ":" + new Base64().encode("57af408d7b3f43cc9fc3728a9101cb89".getBytes()));
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            closeableHttpClient.execute(post);
        }
    }

    @RequestMapping
    @GetMapping("/refreshToken")
    public void getAccessToken(@PathVariable("id") long id, @RequestParam("access_token") String accessToken, @RequestParam("token_type") String tokenType, @RequestParam("scope") String scope, @RequestParam("expires_in") int expiresIn, @RequestParam("refresh_token") String refreshToken ){
        userRepository.findById(id).setRefreshToken(refreshToken);
    }
    */
}
