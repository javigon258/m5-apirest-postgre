package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Car;
import com.example.demo.repository.CarRepository;

@RestController
@RequestMapping("/api")
public class CarController {
	
	private static Logger log = LoggerFactory.getLogger(CarController.class);

	private CarRepository carRepository;
	
	public CarController(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	/**
	 * http://localhost:8080/api/cars/1
	 * @return
	 */
	@GetMapping("/cars/{id}")
	public ResponseEntity<Car> findOne(@PathVariable Long id){
		log.info("REST request to find car");
		//Car car1 = new Car(id,"Seat", "Leon",1.2d,5);
		Optional<Car> carOpt = this.carRepository.findById(id);
		if(carOpt.isPresent()) {
			return ResponseEntity.ok(carOpt.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/cars")
	/**
	 * http://localhost:8080/api/cars
	 * @return
	 */
	public List<Car> findAll(){
		log.info("REST request to find all cars");
		//Car car1 = new Car(1L,"Seat", "Leon",1.2d,5);
		//Car car2 = new Car(2L,"Renault", "Megane",1.8d,5);
		
		return carRepository.findAll();
		
	}
	
	@PostMapping("/cars")
	public ResponseEntity<Car> create(@RequestBody Car car){
		log.info("REST request to create new car");
		if(car.getId() != null) {//Existe el id
			log.warn("Trying to create new car with existent id");
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(this.carRepository.save(car));

	}
	
	@PutMapping("/cars")
	public ResponseEntity<Car> update(@RequestBody Car car){
		log.info("REST request to update an existing car");
		if(car.getId() == null) {// No hay id, no existe el coche a actualizar
			log.warn("Trying to update car an existing car with id");
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(this.carRepository.save(car));

	}
	
	@DeleteMapping("/cars/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		log.info("REST request to delete an existing car");
		
		this.carRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();

	}
	
	@DeleteMapping("/cars")
	public ResponseEntity<Void> deleteAll() {
		log.info("REST request to delete all cars");
		this.carRepository.deleteAll();
		return ResponseEntity.noContent().build();

	}
}
