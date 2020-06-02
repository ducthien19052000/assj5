package com.assj5.thien.assj5.repository;

import com.assj5.thien.assj5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
