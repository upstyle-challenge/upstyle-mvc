package br.com.fiap.upstyle.repository;

import br.com.fiap.upstyle.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

    @Query("SELECT COUNT(u) FROM AppUser u")
    Integer totalAppUsersCount();
}