package com.routesmap.app.model.repository;

import java.util.List;
import com.routesmap.app.model.entity.Route;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RouteRepository extends MongoRepository<Route, String> {

}