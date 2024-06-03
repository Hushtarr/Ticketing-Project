package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserNameAndIsDeleted(String username,Boolean deleted);
    User findByUserNameAndIsDeleted(String userName, boolean deleted);

    @Transactional //driver query // @Modifying is for JPQL or Native query
    void deleteByUserName(String username);

    List<User>findAllByIsDeletedOrderByUserName(Boolean deleted);
    List<User> findByRoleDescriptionIgnoreCaseAndIsDeleted(String roleDescription,Boolean deleted);
}
