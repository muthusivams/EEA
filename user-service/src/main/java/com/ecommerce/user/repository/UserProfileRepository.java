package com.ecommerce.user.repository;

import com.ecommerce.user.domain.UserProfile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
  Optional<UserProfile> findByExternalAuthId(String externalAuthId);
}
