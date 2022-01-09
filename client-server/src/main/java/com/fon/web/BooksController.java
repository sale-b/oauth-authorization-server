package com.fon.web;

import com.fon.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Controller
public class BooksController {

    @Autowired
    private WebClient webClient;

    @GetMapping(value = "/books")
    public String getBooks(
            @RegisteredOAuth2AuthorizedClient("books-client-authorization-code") OAuth2AuthorizedClient authorizedClient,
            Model model) {
        List<Book> books = Arrays.asList(Objects.requireNonNull(this.webClient
                .get()
                .uri("https://resource-server:8090/books")
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(Book[].class)
                .block()));
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() != null) {
            Arrays.stream(request.getCookies())
                    .filter(cookie -> "JSESSIONID".equals(cookie.getName())).forEach(cookie -> {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            });
        }

        return "redirect:https://auth-server:9000/logout";
    }

    @GetMapping("/")
    public String showSignUpForm(Principal principal, Model model) {
        model.addAttribute("authenticated", false);
        if (principal instanceof OAuth2AuthenticationToken) {
            if (((OAuth2AuthenticationToken) principal).getAuthorizedClientRegistrationId().equals("books-client-oidc")) {
                model.addAttribute("authenticated", true);
            }
        }

        return "home";
    }

    @RequestMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@RegisteredOAuth2AuthorizedClient("books-client-authorization-code") OAuth2AuthorizedClient authorizedClient, @RequestParam(name = "image_url") String imgUrl) throws IOException {

        byte[] imageContent = this.webClient.get()
                .uri(imgUrl)
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(byte[].class)
                .block();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }
}