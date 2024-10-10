package com.artemis.prepaid.users;

import com.artemis.prepaid.users.exceptions.EntityNotFoundException;
import com.artemis.prepaid.users.models.EndUser;
import com.artemis.prepaid.users.repositories.EndUserRepository;
import com.artemis.prepaid.users.services.EndUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EndUserServiceTest {
    @Mock
    private EndUserRepository endUserRepository;
    @InjectMocks
    private EndUserService endUserService;

    @Test
    public void  findById_ShouldThrown_EntityNotFoundException(){

        when(endUserRepository.findById(any(UUID.class))).thenThrow(new EntityNotFoundException(""));

        assertThrows(EntityNotFoundException.class, () -> {
            endUserService.findById(UUID.randomUUID());
        });

    }

    @Test
    public void  findById_ShouldReturn_ValidEndUser(){

        var endUser = new EndUser();
        endUser.setName("Beko");

        // Mock repository to return the EndUser wrapped in an Optional
        when(endUserRepository.findById(any(UUID.class))).thenReturn(Optional.of(endUser));

        // Act: Call the service to find the user
        EndUser result = endUserService.findById(UUID.randomUUID());

        // Assert: Verify the EndUser is returned correctly
        assertNotNull(result);
        assertEquals("Beko", result.getName());

    }
}
