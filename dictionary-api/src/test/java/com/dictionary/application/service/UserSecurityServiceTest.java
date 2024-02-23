package com.dictionary.application.service;

import com.dictionary.domain.model.User;
import com.dictionary.domain.repository.IUserRepository;
import com.dictionary.infrastructure.persistence.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserSecurityServiceTest {

  @Mock
  private UserRepositoryImpl userRepository;

  @InjectMocks
  private UserSecurityService userSecurityService;

  @Test
  void loadUserByUsername_whenUserExists_thenReturnsUser() {
    // Arrange
    String username = "testUser";
    User mockUser = new User();
    mockUser.setUsername(username);
    mockUser.setPassword("password123");
    given(userRepository.findByUsername(username)).willReturn(java.util.Optional.of(mockUser));

    // Act
    UserDetails userDetails = userSecurityService.loadUserByUsername(username);

    // Assert
    assertEquals(username, userDetails.getUsername());
  }

  @Test
  void loadUserByUsername_whenUserNotExists_thenThrowsUsernameNotFoundException() {
    // Arrange
    given(userRepository.findByUsername(anyString())).willReturn(java.util.Optional.empty());

    // Act & Assert
    assertThrows(UsernameNotFoundException.class, () -> {
      userSecurityService.loadUserByUsername("nonExistentUser");
    });
  }
}