//package com.codecool.neighbrotaxi.controller;
//
//import com.codecool.neighbrotaxi.model.SerializableSessionStorage;
//import com.codecool.neighbrotaxi.model.SessionStorage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//
//@Controller
//public class UserController {
//    @Autowired
//    private SessionStorage sessionStorage;
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    @ResponseBody public SerializableSessionStorage login(String logout) {
//        if (logout != null) {
//            sessionStorage.clearMessages();
//            sessionStorage.addInfoMessage("You have been logged out successfully.");
//            return new SerializableSessionStorage(sessionStorage);
//        }
//
//        return null;
//    }@RequestMapping(value = "/login", method = RequestMethod.GET)
//    @ResponseBody public SerializableSessionStorage login(String logout) {
//        if (logout != null) {
//            sessionStorage.clearMessages();
//            sessionStorage.addInfoMessage("You have been logged out successfully.");
//            return new SerializableSessionStorage(sessionStorage);
//        }
//
//        return null;
//    }
//
//}
