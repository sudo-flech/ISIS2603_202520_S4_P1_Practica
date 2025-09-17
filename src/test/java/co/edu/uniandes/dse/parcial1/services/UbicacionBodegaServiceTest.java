package co.edu.uniandes.dse.parcial1.services;

import java.lang.instrument.IllegalClassFormatException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(UbicacionBodegaService.class)
public class UbicacionBodegaServiceTest {

    @Autowired
    private UbicacionBodegaService ubicacionBodegaService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    @BeforeEach
    public void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
		entityManager.getEntityManager().createQuery("delete from UbicacionBodegaEntity").executeUpdate();
	}

    private void insertData() {
		UbicacionBodegaEntity entity = factory.manufacturePojo(UbicacionBodegaEntity.class);
		entityManager.persist(entity);
	}

    @Test
    public void testCreateUbicacion() throws IllegalClassFormatException { 
        UbicacionBodegaEntity newUbiacion = factory.manufacturePojo(UbicacionBodegaEntity.class);
        newUbiacion.setNumEstante(41);
        newUbiacion.setCanasta("Esquina superior");
        newUbiacion.setPesoMax(38);

        UbicacionBodegaEntity createdUbicacion = UbicacionBodegaService.createUbicacionBodega(newUbiacion);
        Assertions.assertNotNull(createdUbicacion);

        UbicacionBodegaEntity testedUbicacion = entityManager.find(UbicacionBodegaEntity.class, createdUbicacion.getId());
        Assertions.assertNotNull(testedUbicacion);
        Assertions.assertEquals(createdUbicacion.getNumEstante(), testedUbicacion.getNumEstante());
        Assertions.assertEquals(createdUbicacion.getCanasta(), testedUbicacion.getCanasta());
        Assertions.assertEquals(createdUbicacion.getPesoMax(), testedUbicacion.getPesoMax());
        

    }

    public void testCreateUbicacionNumEstanteNegativo() {
        UbicacionBodegaEntity newUbiacion = factory.manufacturePojo(UbicacionBodegaEntity.class);
        newUbiacion.setPesoMax(12);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            newUbiacion.setNumEstante(-2);
            ubicacionBodegaService.createUbicacionBodega(newUbiacion);
        });
    }
    
}
