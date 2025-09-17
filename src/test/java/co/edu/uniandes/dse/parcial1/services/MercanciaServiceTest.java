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
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(MercanciaService.class)
public class MercanciaServiceTest {
    
    @Autowired
    private MercanciaService mercanciaService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    @BeforeEach
    public void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
		entityManager.getEntityManager().createQuery("delete from MercanciaEntity").executeUpdate();
	}

    private void insertData() {
		MercanciaEntity entity = factory.manufacturePojo(MercanciaEntity.class);
		entityManager.persist(entity);
	}

    @Test
    public void testCreateMercancia() throws IllegalClassFormatException { 
        MercanciaEntity newMercancia = factory.manufacturePojo(MercanciaEntity.class);
        newMercancia.setNombre("Jabon");
        newMercancia.setCodigo_barras("1000101011");
        newMercancia.setFecha("2025-01-01");
        newMercancia.setCantidadDisp(5);

        MercanciaEntity createdMercancia = mercanciaService.createMercanciaEntity(newMercancia);
        Assertions.assertNotNull(createdMercancia);

        MercanciaEntity testedMercancia = entityManager.find(MercanciaEntity.class, createdMercancia.getId());
        Assertions.assertNotNull(testedMercancia);
        Assertions.assertEquals(createdMercancia.getNombre(), testedMercancia.getNombre());
        Assertions.assertEquals(createdMercancia.getCodigo_barras(), testedMercancia.getCodigo_barras());
        Assertions.assertEquals(createdMercancia.getFecha(), testedMercancia.getFecha());
        Assertions.assertEquals(createdMercancia.getCantidadDisp(), testedMercancia.getCantidadDisp());

    }

    public void testCreateMercanciaNullName() {
        MercanciaEntity newMercancia = factory.manufacturePojo(MercanciaEntity.class);
        newMercancia.setCantidadDisp(5);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            newMercancia.setNombre(null);
            mercanciaService.createMercanciaEntity(newMercancia);
        });
    }
    
}
