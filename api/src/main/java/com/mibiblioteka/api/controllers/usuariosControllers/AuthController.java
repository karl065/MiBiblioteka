package com.mibiblioteka.api.controllers.usuariosControllers;

import com.mibiblioteka.api.services.usuariosService.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
     @Autowired
    private LoginService loginServices;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        try {
            
            String correo = loginData.get("correo");
            String password = loginData.get("password");
    
            String token = loginServices.LoginServices(correo, password);
    
            if (token != null) {
                return ResponseEntity.ok(Map.of("token", token));
            } else {
                return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
            }
            
        } catch (Exception e) {
           e.printStackTrace();
            System.err.println("Error de Login: " + e.getMessage());
            return null;
        }
    }

}
