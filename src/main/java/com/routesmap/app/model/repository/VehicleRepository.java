package com.routesmap.app.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.routesmap.app.model.entity.Vehicle;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {

}
