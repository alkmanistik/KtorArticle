package ru.alkmanistik.spring_article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alkmanistik.spring_article.model.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByName(String name);

    List<Client> findByNameContainingIgnoreCase(String name);

    @Query("SELECT c FROM Client c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Client> searchByName(@Param("name") String name);

    boolean existsById(Integer id);
}