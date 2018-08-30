package com.qa.SpringBoot.JJ.Database.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.SpringBoot.JJ.Database.exception.ResourceNotFoundException;
import com.qa.SpringBoot.JJ.Database.model.SpringBootDataModel;
import com.qa.SpringBoot.JJ.Database.repo.SpringBootRepo;

@RestController
@RequestMapping("/api")
public class SpringBootDataController {

@Autowired
SpringBootRepo myRepo;

@PostMapping("/person")
public SpringBootDataModel createPerson(@Valid @RequestBody SpringBootDataModel mSDM) {
	return myRepo.save(mSDM);
}

@GetMapping("/person/{id}")
public SpringBootDataModel getPersonbyID(@PathVariable(value = "id") Long personID) {
		return myRepo.findById(personID).orElseThrow(()-> new ResourceNotFoundException("SpringBootDataModel", "id", personID));
}

@GetMapping("/person")
public List<SpringBootDataModel> getAllPeople(){
	return myRepo.findAll();
}

@PutMapping("/person/{id}")
public SpringBootDataModel updatePerson(@PathVariable(value = "id") Long personID,
		@Valid @RequestBody SpringBootDataModel personDetails) {

	SpringBootDataModel nSDM = myRepo.findById(personID).orElseThrow(()-> new ResourceNotFoundException("Person", "id", personID));

	nSDM.setName(personDetails.getName());
	nSDM.setAddress(personDetails.getAddress());
	nSDM.setAge(personDetails.getAge());
	
	SpringBootDataModel updateData = myRepo.save(nSDM);
	return updateData;
}

@DeleteMapping("/person/{id}")
public ResponseEntity<?> deletePerson(@PathVariable(value = "id")Long personID){
	
	SpringBootDataModel nSDM = myRepo.findById(personID).orElseThrow(()-> new ResourceNotFoundException("Person", "id", personID));
	
	myRepo.delete(nSDM);
	return ResponseEntity.ok().build();
}


}





