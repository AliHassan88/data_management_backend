package com.datamanagement.api;

import com.datamanagement.booking.Booking;
import com.datamanagement.dto.BookingDto;
import com.datamanagement.login.Login;
import com.datamanagement.register.Register;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class Controller {
    Logger logger = LoggerFactory.getLogger(Controller.class);
    @Autowired
    DataMangementService dataMangementService;

    //for registering a new user
    @PostMapping("/register")
    @ResponseBody
    ResponseEntity registerUser(@RequestBody Register register) {
        ResponseEntity responseEntity;
        try {
            logger.info("Received Registration request against username " + register.getName());
            responseEntity = new ResponseEntity(dataMangementService.registerUser(register), HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Received Registeration request against username" + register.getName() + " but failed due to exception "+e);
            responseEntity = new ResponseEntity("Failed to register user because of exception "+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //Getting a users login details
    @PostMapping("/login")
    @ResponseBody
    ResponseEntity getAuthenticatedUser(@RequestBody Login login) {
        ResponseEntity responseEntity;
        try {
            logger.info("Getting User authentication details against username" + login.getUsername());
            responseEntity = new ResponseEntity(dataMangementService.getAuthenticUser(login), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Failed to autheticate user because of exception "+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //Create a booking
    @PostMapping("/booking")
    @ResponseBody
    ResponseEntity createBoking(@RequestBody BookingDto booking) {
        ResponseEntity responseEntity;
        try {
            logger.info("Boking request received ");
            responseEntity = new ResponseEntity(dataMangementService.createBooking(booking), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Failed to create a booking because of exception "+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //Getting Booking schedules
    @PostMapping("/schedule")
    @ResponseBody
    ResponseEntity getBokings(@RequestBody String uuid) {
        ResponseEntity responseEntity;
        try {
            logger.info("Get Booking Request received ");
            responseEntity = new ResponseEntity(dataMangementService.getBookings(uuid), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Failed to create a booking because of exception "+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
