package co.edu.uniandes.dse.parcial1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import co.edu.uniandes.dse.parcial1.repositories.MercanciaRepository;
import co.edu.uniandes.dse.parcial1.repositories.UbicacionBodegaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UbicacionBodegaService {
    private static final String NOMBRE_ENTIDAD = "UbicacionBodega";

    @Autowired
    private UbicacionBodegaRepository ubicacionBodegaRepository;

    @Transactional
    public UbicacionBodegaEntity createUbicacionBodega(UbicacionBodegaEntity ubicacionBodEntity) {
    log.info("Empezando proceso de creacion de la entidad.");

    if (ubicacionBodEntity.getNumEstante() < 0) {
        throw new IllegalArgumentException("El numero de bodega debe ser mayor o igual a 0");
    }
    
    log.info("Ubicacion de la bodega correctamente creada");
    return ubicacionBodegaRepository.save(ubicacionBodEntity);
    }
}
