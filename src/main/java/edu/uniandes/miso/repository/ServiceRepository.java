package edu.uniandes.miso.repository;

import edu.uniandes.miso.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service,Long> {
	List<Service> findByIdUserCreator(Long idUserCreator);

	@Override
	Optional<Service> findById(Long aLong);
}
