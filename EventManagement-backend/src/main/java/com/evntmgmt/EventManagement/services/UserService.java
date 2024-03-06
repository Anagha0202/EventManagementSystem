package com.evntmgmt.EventManagement.services;

import com.evntmgmt.EventManagement.models.User;
import com.evntmgmt.EventManagement.repository.UserRepository;
import com.evntmgmt.EventManagement.response.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.evntmgmt.EventManagement.utils.JwtUtil;
import java.util.regex.Pattern;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    //    @Override
    public Command signUp(String firstName, String lastName, String email, String password) {
        User existingUser = userRepository.findByEmail(email);
        if(existingUser!=null) {
            return new Command("User already exists", false);
        }
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAdmin(false);
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return new Command("User successfully registered", true);
    }
    public Command validateEmailAndPassword(String email, String password) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-z]{2,6}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        if(!emailPattern.matcher(email).matches()) {
            return new Command("Invalid email format", false);
        }
        if(!passwordPattern.matcher(password).matches()) {
            return new Command("Password must be at least 8 characters long, contain at least one digit, one lower case letter, one upper case letter, and one special character", false);
        }
        return new Command("Validation successful", true);
    }
    @Autowired
    private JwtUtil jwtUtil;
    public Command login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String jwtToken = jwtUtil.generateToken(email);
            return new Command("Login successful", true, jwtToken, user.isAdmin(), email);
        }
        return new Command("Invalid credentials", false);
    }
}
