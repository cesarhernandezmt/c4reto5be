/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.usa.ciclo4.retos.icrudrepository;

import co.usa.ciclo4.retos.dmodel.Clone;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * 
 * Interface CloneCrudRepository hereda los metodos y atributos de la clase
 * MongoRepository
 */
public interface CloneCrudRepository extends MongoRepository<Clone, Integer>{
    
    /**
     * Metodo Query para seleccionar el registro de documento de producto, con 
     * el valor mayor en el atributo 'id'
     * @return 
     */
    public Optional<Clone> findTopByOrderByIdDesc();
    
    /**
     * Metodo Query para encontrar los registros de documentos de productos, con
     * un valor de atributo 'price' menor o igual al solicitado.
     * @param price
     * @return 
     */
    public List<Clone> findByPriceLessThanEqual(Double price);
    
    /**
     * Metodo Query para encontrar los registros de documentos de proeuctos, con
     * un valor combinado de cadenas de caracteres, que definen el atributo 
     * 'description'.
     * @param description
     * @return 
     */
    @Query("{'description':{'$regex':'?0','$options':'i'}}")
    public List<Clone> findByDescriptionLike(String description);
   
}
