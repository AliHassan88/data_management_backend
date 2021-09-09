package com.datamanagement.api;

import com.datamanagement.dto.BookingDto;
import com.datamanagement.login.Login;
import com.datamanagement.login.LoginRepository;
import com.datamanagement.register.Register;
import com.datamanagement.register.RegisterRepository;
import com.datamanagement.user.User;
import com.datamanagement.user.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DataMangementService {
    Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    UserRepo userRepo;


    Register registerUser(Register register) {
        createLogin(register.getEmail(), register.getPassword());
        return registerRepository.save(register);
    }

    void createLogin(String username, String password) {
        Login login = new Login(username, password);
        createUser(username, login.getUuid());
        loginRepository.save(login);
    }

    void createUser(String username, String uuid) {
        User user = new User(username, uuid);
        userRepo.save(user);
    }

    String getAuthenticUser(Login login) {
        Login login1 = loginRepository.getLoginByUsernameAndPassword(login.getUsername(), login.getPassword());
        return login1.getUuid();
    }

    ResponseEntity createBooking(BookingDto bookingDto) {
        ResponseEntity responseEntity;

        try {
            logger.info("Creating booking ...");
            User user = userRepo.findUserByUuid(bookingDto.getUuid());
            user.getUserBookings().add(bookingDto.getBooking());
            responseEntity = new ResponseEntity(user, HttpStatus.OK);
            logger.info("Created booking against user "+user.getUsername());
        } catch (Exception e) {
            logger.error("Failed to create booking due to exception ",e);
            responseEntity = new ResponseEntity("Failed to create booking due to exception "+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    ResponseEntity getBookings(String uuid) {
        ResponseEntity responseEntity;

        try{
            User user = userRepo.findUserByUuid(uuid);
            responseEntity = new ResponseEntity(user.getUserBookings(), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Failed to get User Bookings due to exception "+e, HttpStatus.OK);
        }
        return responseEntity;
    }
}
