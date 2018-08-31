package com.qa.SpringBoot.JJ.Database.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.SpringBoot.JJ.Database.exception.ResourceNotFoundException;
import com.qa.SpringBoot.JJ.Database.model.Order;
import com.qa.SpringBoot.JJ.Database.repo.OrderRepo;
import com.qa.SpringBoot.JJ.Database.repo.SpringBootRepo;

@RestController
public class OrderController {

	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private SpringBootRepo springRepo;
	
	@GetMapping("/person/{personId}/order")
	public Page<Order> getAllOrderByPersonId(@PathVariable (value = "person_id") Long personId, Pageable pageable){
		return orderRepo.findByPersonId(personId, pageable);
	}
	
	@PostMapping("/person/{personId}/order")
	public Order createComment(@PathVariable (value = "personId") Long personId, @Valid @RequestBody Order order) {
		return springRepo.findById(personId).map(SpringBootDataModel -> {
			order.setPerson(SpringBootDataModel);
			return orderRepo.save(order);
		}).orElseThrow(() -> new ResourceNotFoundException("Person", "id", order));
	}
	
	@PutMapping("/person/{personId}/order/{orderId}")
	public Order updateOrder(@PathVariable (value = "personId") Long personId, 
			@PathVariable (value = "orderId") Long orderId, 
			@Valid @RequestBody Order orderRequest) {
		if(!springRepo.existsById(personId)) {
			throw new ResourceNotFoundException("OrderId", "id", orderRequest);
		}
		
		return orderRepo.findById(orderId).map(order -> {
			order.setTitle(orderRequest.getTitle());
			return orderRepo.save(order);
		}).orElseThrow(() -> new ResourceNotFoundException("OrderId", "id", orderRequest));
	}
	
	@DeleteMapping("/person/{personId}/order/{orderId}")
	public ResponseEntity<?> deleteComment(@PathVariable (value = "personId") Long personId,
										@PathVariable (value = "orderId") Long orderId) {
		if(!springRepo.existsById(personId)) {
			throw new ResourceNotFoundException("Person", "id", personId);
		}
		
		return orderRepo.findById(orderId).map(order -> {
			orderRepo.delete(order);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("OrderId", orderId.toString(), null));
	
		
	}
	
}
