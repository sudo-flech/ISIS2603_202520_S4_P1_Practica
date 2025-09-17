package co.edu.uniandes.dse.parcial1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.MercanciaRepository;
import co.edu.uniandes.dse.parcial1.repositories.UbicacionBodegaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MercanciaUbicacionBodegaService {
   @Autowired
    private UbicacionBodegaRepository ubicacionBodegaRepository;

    @Autowired
    private MercanciaRepository mercanciaRepository;

    @Transactional
    public void asignarUbicacionAMercancia(Long ubicacionId, Long mercanciaId) throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicio de proceso de asignacion");
        Optional<UbicacionBodegaEntity> ubicacion = ubicacionBodegaRepository.findById(ubicacionId);
        Optional<MercanciaEntity> mercancia = mercanciaRepository.findById(mercanciaId);
        if (ubicacion.isEmpty()) {
            throw new EntityNotFoundException("No se encontro ninguna ubicacionBodega con ese id");
        }
        if (mercancia.isEmpty()) {
            throw new EntityNotFoundException("No se encontro ninguna mercancia con ese id");
        }

        if (mercancia.get().getUbicacion().equals(ubicacion) && mercancia.get().getUbicacion() != null) {
            throw new IllegalOperationException("La ubicacion ya esta asociada a esa mercancia");
        }

        mercancia.get().setUbicacion(ubicacion.get());
        mercanciaRepository.save(mercancia);

        log.info("Se finalizo el proceso de");
    }
}
