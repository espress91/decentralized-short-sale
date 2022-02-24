package com.github.espress91.decentralizedShortSale.repository;

import com.github.espress91.decentralizedShortSale.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
