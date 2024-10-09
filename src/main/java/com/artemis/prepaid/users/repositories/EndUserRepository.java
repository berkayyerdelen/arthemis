package com.artemis.prepaid.users.repositories;

import com.artemis.prepaid.users.models.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EndUserRepository extends JpaRepository<EndUser, UUID> {
}
