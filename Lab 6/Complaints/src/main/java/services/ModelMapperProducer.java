/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import org.modelmapper.ModelMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

/**
 *
 * @author kowal
 */
public class ModelMapperProducer {
    
    @Produces
    @ApplicationScoped
    public ModelMapper createModelMapper() {
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }
}
