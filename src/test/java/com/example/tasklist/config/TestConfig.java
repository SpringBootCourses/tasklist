package com.example.tasklist.config;

import com.example.tasklist.repository.TaskRepository;
import com.example.tasklist.repository.UserRepository;
import com.example.tasklist.service.ImageService;
import com.example.tasklist.service.impl.AuthServiceImpl;
import com.example.tasklist.service.impl.ImageServiceImpl;
import com.example.tasklist.service.impl.TaskServiceImpl;
import com.example.tasklist.service.impl.UserServiceImpl;
import com.example.tasklist.service.props.JwtProperties;
import com.example.tasklist.service.props.MinioProperties;
import com.example.tasklist.web.security.JwtTokenProvider;
import com.example.tasklist.web.security.JwtUserDetailsService;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@TestConfiguration
@RequiredArgsConstructor
public class TestConfig {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final AuthenticationManager authenticationManager;

    @Bean
    @Primary
    public BCryptPasswordEncoder testPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public JwtProperties jwtProperties() {
        JwtProperties jwtProperties = new JwtProperties();
        jwtProperties.setSecret(
                "dmdqYmhqbmttYmNhamNjZWhxa25hd2puY2xhZWtic3ZlaGtzYmJ1dg=="
        );
        return jwtProperties;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new JwtUserDetailsService(userService());
    }

    @Bean
    public MinioClient minioClient() {
        return Mockito.mock(MinioClient.class);
    }

    @Bean
    public MinioProperties minioProperties() {
        MinioProperties properties = new MinioProperties();
        properties.setBucket("images");
        return properties;
    }

    @Bean
    @Primary
    public ImageService imageService() {
        return new ImageServiceImpl(minioClient(), minioProperties());
    }

    @Bean
    public JwtTokenProvider tokenProvider() {
        return new JwtTokenProvider(jwtProperties(),
                userDetailsService(),
                userService());
    }

    @Bean
    @Primary
    public UserServiceImpl userService() {
        return new UserServiceImpl(userRepository, testPasswordEncoder());
    }

    @Bean
    @Primary
    public TaskServiceImpl taskService() {
        return new TaskServiceImpl(taskRepository, imageService());
    }

    @Bean
    @Primary
    public AuthServiceImpl authService() {
        return new AuthServiceImpl(authenticationManager,
                userService(),
                tokenProvider());
    }

}
