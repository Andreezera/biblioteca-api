package com.unesp.bibliotecaapi.controller;

import com.unesp.bibliotecaapi.dto.Auth.AuthenticationDto;
import com.unesp.bibliotecaapi.dto.Auth.RegisterDto;
import com.unesp.bibliotecaapi.dto.LoginResponseDto;
import com.unesp.bibliotecaapi.model.Usuario;
import com.unesp.bibliotecaapi.model.UsuarioRole;
import com.unesp.bibliotecaapi.repository.UsuarioRepository;
import com.unesp.bibliotecaapi.service.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("auth")
@Tag(name = "Auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

        try {
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((Usuario) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDto(token));
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto data) {

        Usuario usuario = new Usuario();
        usuario.setEmail(data.email());
        usuario.setPassword(passwordEncoder.encode(data.password()));
        usuario.setRole(UsuarioRole.valueOf(data.role()));

        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
