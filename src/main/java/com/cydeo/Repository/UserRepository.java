package com.cydeo.Repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUserName(String username);
    @Transactional //driver query
    // @Modifying  JPQL or Native query
    void deleteByUserName(String username);
}
