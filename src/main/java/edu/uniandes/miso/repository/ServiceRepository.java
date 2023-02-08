package edu.uniandes.miso.repository;

import edu.uniandes.miso.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service,Long> {
    @Query("select s from Service s where s.nameService = ?1")
    Optional<Service> findByNameService(String nameService);

}
