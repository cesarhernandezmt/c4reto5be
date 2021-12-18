/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.usa.ciclo4.retos.icrudrepository;


import co.usa.ciclo4.retos.dmodel.Order;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


/**
 *
 * Interface OrderCrudRepository hereda los metodos y atributos de la clase
 * MongoRepository
 */
public interface OrderCrudRepository extends MongoRepository<Order, Integer> {

        /**
     * Metodo Query para seleccionar el registro de documento de la orden, 
     * con el valor mayor en el atributo 'id'
     * @return 
     */
    public Optional<Order> findTopByOrderByIdDesc();
    
    /**
     * Metodo Query para encontrar y retornar los registros de documentos de 
     * ordenes por el valor de su atributo 'salesMan.zone', establecido como parámetro. 
     * Se pasa el valor del atributo 'salesMan.zone' en la posición '0'
     * @param zone
     * @return 
     */
    @Query("{'salesMan.zone':?0}")
    public List<Order> findBySalesManZone(String zone);
    
    
    /**
     * Metodo Query para encontrar y retornar los registros de documentos de 
     * ordenes por el valor de su atributo 'status', establecido como parámetro.
     * @param status
     * @return 
     */
    @Query("{'status':?0}")
    public List<Order> findByStatus(String status);

    /**
     * Metodo Query para encontrar y retornar los registros de documentoa des 
     * ordenes por el valor de su atributo 'salesMan.id', establecido como 
     * parámetro.
     * Se pasa el valor del atributo 'salesMan.id' en la posición '0'
     * @param id
     * @return 
     *//*
    @Query("{'salesMan.id':?0}")
    public List<Order> findBySalesManId(Integer id);
    */
    public List<Order> findBySalesMan_Id(Integer id);

    /**
     * Metodo Query para encontrar y retornar los registros de documentoa des 
     * ordenes por los valores de sus atributos 'registerDay' y 'salesMan.id', 
     * establecidos como parámetros.
     * Se pasan los valores de los atributos 'registerDay' y 'salesMan.id' en 
     * la posición '0'.
     * @param registerDay
     * @param id
     * @return 
     *//*
    @Query("{'registerDay':?0,'salesMan.id':?0}")
    public List<Order> findByRegisterDayAndSalesManId(Date registerDay, Integer id);
    */
    public List<Order> findByRegisterDayAndSalesMan_Id(Date registerDay, Integer id);    
    
    /**
     * Metodo Query para encontrar y retornar los registros de documentoa des 
     * ordenes por los valores de sus atributos 'status' y 'salesMan.id', 
     * establecidos como parámetros.
     * Se pasan los valores de los atributos 'status' y 'salesMan.id' en 
     * la posición '0'.
     * @param status
     * @param id
     * @return 
     *//*
    @Query("{'status':?0,'salesMan.id':?0}")    
    public List<Order> findByStatusAndSalesManId(String status, Integer id);
    */
    public List<Order> findByStatusAndSalesMan_Id(String status, Integer id);
        
}
