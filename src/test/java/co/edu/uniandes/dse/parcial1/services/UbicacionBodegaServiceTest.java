package co.edu.uniandes.dse.parcial1.services;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import jakarta.transaction.Transactional;

@DataJpaTest
@Transactional
@Import(UbicacionBodegaService.class)
public class UbicacionBodegaServiceTest {

    // TODO: Escriba las pruebas para la clase UbicacionBodegaService
    
}
