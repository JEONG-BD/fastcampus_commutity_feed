package org.fastcampus.auth.repository.jpa;

import org.fastcampus.auth.repository.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserAuthRepository extends JpaRepository<UserAuthEntity, String> {



}
