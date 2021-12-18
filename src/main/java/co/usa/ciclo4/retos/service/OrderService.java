/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.usa.ciclo4.retos.service;

import co.usa.ciclo4.retos.dmodel.Order;
import co.usa.ciclo4.retos.repository.OrderRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Clase Servicio 'OrderService'
 */
@Service
public class OrderService {
    
    
    /**
     * Atributo objeto 'orderRepository' instancia de la clase
     * 'OrderRepository'
     */
    @Autowired
    private OrderRepository orderRepository;
    
     /**
     * Metodo para obtener y retornar una lista de todos los registros 
     * de documentos de productos hacia el metodo 'getAll' del 
     * OrderRepository
     * @return
     */
    public List<Order> getAll() {
        return orderRepository.getAll();
    }
    
     /**
     * Metodo para obtener y retornar un registro de documento de producto 
     * por el valor de su atributo 'id', hacia el metodo 'getOrderById' del
     * OrderRepository
     * @param id
     * @return
     */
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.getOrderById(id);
    }
    
     /**
     * Metodo para guardar y retornar una registro de documento de producto 
     * hacia el metodo 'save' del OrderRepository
     * @param order
     * @return
     */
    public Order save(Order order) {
        
        Optional<Order> orderWithLastId = orderRepository.getOrderWithLastId();
        if(order.getId() == null) {
            if(orderWithLastId.isEmpty())
                order.setId(1);
            else
                order.setId(orderWithLastId.get().getId() + 1);
        }      
        
        if(order.getRegisterDay() == null || order.getStatus() == null 
                || order.getSalesMan() == null) {
            return order;
        }
        else{
            if (order.getId() == null) {
                return orderRepository.save(order);
            } 
            else {
                Optional<Order> orderOptional = orderRepository.getOrderById(order.getId());
                if (orderOptional.isEmpty()) {
                    return orderRepository.save(order);
                } 
                else {
                    return order;
                }
            }  
        }
    }

    /**
     * Metodo para actualizar y retornar un registro de documento de producto 
     * hacia el metodo 'update' del OrderRepository
     * @param order
     * @return
     */
    public Order update(Order order) {
        if (order.getId() != null) {
            Optional<Order> orderOptional = orderRepository.getOrderById(order.getId());
            if (!orderOptional.isEmpty()) {
                if (order.getRegisterDay() != null) {
                    orderOptional.get().setRegisterDay(order.getRegisterDay());
                }
                if (order.getSalesMan() != null) {
                    orderOptional.get().setSalesMan(order.getSalesMan());
                }
                if (order.getStatus() != null) {
                    orderOptional.get().setStatus(order.getStatus());
                }
              /*if (order.getProducts() != null) {
                    orderOptional.get().setProducts(order.getProducts());
                }
                if (order.getQuantities() != null) {
                    orderOptional.get().setQuantities(order.getQuantities());
                }*/
                orderRepository.update(orderOptional.get());
                return orderOptional.get();
            } else {
                return order;
            }
        } else {
            return order;
        }
    }
    
    /**
     * Metodo para eliminar y retornar un registro de documento de producto 
     * por el valor de su atributo 'id', hacia el metodo 'delete' del 
     * OrderRepository
     * @param id
     * @return
     */
    public boolean delete(Integer id) {
        Optional<Order> orderOptional = orderRepository.getOrderById(id);
        if (orderOptional.isPresent()) {
            orderRepository.delete(orderOptional.get());
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Metodo para obtener y retornar una lista de todos los registros de
     * documentos de ordenes, por el valor del atributo 'zone' 
     * hacia el metodo 'getOrderByZone' del OrderRepository
     * @param zone
     * @return
     */
    public List<Order> getOrderByZone(String zone){
        return orderRepository.getOrderByZone(zone);
    }

    /**
     * Metodo para obtener y retornar una lista de todos los registros de
     * documentos de ordenes, por el valor del atributo 'zone' 
     * hacia el metodo 'getOrderByStatus' del OrderRepository.
     * @param status
     * @return
     */
    public List<Order> getOrderByStatus(String status){
        return orderRepository.getOrderByStatus(status);
    }
    
    /**
     * Metodo para obtener y retornar una lista de todos los registros de
     * documentos de ordenes, por el valor del atributo 'salesman.id' 
     * hacia el metodo 'getOrderBySalesManId' del OrderRepository.
     * @param id
     * @return 
     */
    public List<Order> getOrderBySalesManId(Integer id) {
        return orderRepository.getOrderBySalesManId(id);
    }
    
    /**
     * Metodo para obtener y retornar una lista de todos los registros de
     * documentos de ordenes, por los valores de los atributos 'registerDay' y
     * 'salesman.id' hacia el metodo 'getOrderByRegisterDayAndSalesManId' del 
     * OrderRepository. Se añade conversión del formato del valor 
     * 'registerDay' a la forma 'aaaa-MM-dd'.
     * @param registerDay
     * @param id
     * @return 
     */
    public List<Order> getOrderByRegisterDayAndSalesManId(String registerDay, Integer id) {
        try {
            return orderRepository.getOrderByRegisterDayAndSalesManId
            (new SimpleDateFormat("yyyy-MM-dd").parse(registerDay), id);
        }
        catch (ParseException exception) {
            exception.printStackTrace();
            return null;
        }
    }
    
    /**
     * Metodo para obtener y retornar una lista de todos los registros de
     * documentos de ordenes, por los valores de los atributos 'status' y
     * 'salesman.id' hacia el metodo 'getOrderByStatusAndSalesManId' del 
     * OrderRepository.
     * @param status
     * @param id
     * @return 
     */
    public List<Order> getOrderByStatusAndSalesManId(String status, Integer id) {
        return orderRepository.getOrderByStatusAndSalesManId(status, id);
    }    
    
}
