package it.epicode.w7d1t.repositories;

import it.epicode.w7d1t.models.objects.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {

    public Optional<User> findByUsername(String username);

}
