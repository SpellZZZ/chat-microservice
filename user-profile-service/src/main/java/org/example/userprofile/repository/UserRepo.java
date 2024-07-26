package org.example.userprofile.repository;


import org.example.userprofile.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserProfile, Long> {
}
