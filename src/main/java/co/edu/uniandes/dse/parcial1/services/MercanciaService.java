package co.edu.uniandes.dse.parcial1.services;

import java.lang.instrument.IllegalClassFormatException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.util.ExceptionCollector;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.repositories.MercanciaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MercanciaService {
    private static final String NOMBRE_ENTIDAD = "Mercancia";

    @Autowired
    private MercanciaRepository mercanciaRepository;

    @Transactional
    public MercanciaEntity createMercanciaEntity(MercanciaEntity mercanciaEntity) {
        log.info("Empezando proceso de creacion de la entidad.");
        if (mercanciaEntity.getNombre() == null || mercanciaEntity.getNombre() == "" || mercanciaEntity.getNombre() == " ") {
            throw new IllegalArgumentException("El nombre esta vacio o es invalido");
        }

        if (Duration.between(LocalDateTime.now(), LocalDate.parse(mercanciaEntity.getFecha())) != null  ) { //TODO: Terminar
            throw new IllegalArgumentException("La fecha ya paso");
        }

        //TODO: Falta añadir lo del codigo de barras

        log.info("Mercancia creada correctamente.");
        return mercanciaRepository.save(mercanciaEntity);


    }
}
