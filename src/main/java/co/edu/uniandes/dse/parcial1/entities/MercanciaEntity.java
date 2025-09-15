package co.edu.uniandes.dse.parcial1.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class MercanciaEntity extends BaseEntity{
    private String nombre;
    private String descripcion;
    private Double peso;
    

}
