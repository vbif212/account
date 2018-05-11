package com.service.account.rest;

import com.service.account.database.User;
import com.service.account.serviceLayer.ServiceLayer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
public class requestController {

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ResponseEntity<User> getAccountData(@RequestParam(value = "login", required = false) String login
            , @RequestParam(value = "email", required = false) String email
            , @RequestParam(value = "id", required = false) String id){

        if (login != null){
            return new ResponseEntity<>(ServiceLayer.getUserByLogin(login), HttpStatus.FOUND);
        }
        if (email != null){
            return new ResponseEntity<>(ServiceLayer.getUserByEmail(email), HttpStatus.FOUND);
        }
        if (id != null) {
            return new ResponseEntity<>(ServiceLayer.getUserByID(id), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void postAccountData(@RequestParam(value = "login") String login
            , @RequestParam(value = "pwd") String password
            , @RequestParam(value = "email") String email
            , @RequestParam(value = "device", required = false) String[] devices){
        if (devices != null){
            ServiceLayer.inputNewUser(login, password, email, Arrays.asList(devices));
        } else {
            ServiceLayer.inputNewUserWithoutDevices(login, password, email);
        }
    }

    /*@RequestMapping(value = "/account/{id}", method = RequestMethod.PUT)
    public String putAccountData(@PathVariable("id") String id
            , @RequestParam(value = "login", required = false) String login
            , @RequestParam(value = "pwd", required = false) String password
            , @RequestParam(value = "email", required = false) String email
            , @RequestParam(value = "device", required = false) String[] devices){
        return getString(login, password);
    }*/

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public ResponseEntity<Void> deleteAccountData(@RequestParam(value = "login") String login){
        ServiceLayer.deleteUser(login);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
