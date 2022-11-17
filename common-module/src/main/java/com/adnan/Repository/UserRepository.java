package com.adnan.Repository;

import com.adnan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String userName);

    boolean existsByEmail(String userName);

    User findByEmail(String username);

//    User findByPhoneNo(String username);
//
//    boolean existsByPhoneNo(String phoneNo);

    boolean existsByUsername(String userName);
}
