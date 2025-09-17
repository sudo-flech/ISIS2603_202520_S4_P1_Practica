package co.edu.uniandes.dse.parcial1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;

public interface MercanciaRepository extends JpaRepository<MercanciaEntity, Long> {

    void save(Optional<MercanciaEntity> mercancia);

}
