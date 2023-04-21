package pt.isep.arqsoft.GorgeousSandwich.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.Sandwich;
import pt.isep.arqsoft.GorgeousSandwich.dto.sandwich.SandwichConverter;
import pt.isep.arqsoft.GorgeousSandwich.dto.sandwich.SandwichDTO;
import pt.isep.arqsoft.GorgeousSandwich.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.NoSuchElementException;
import pt.isep.arqsoft.GorgeousSandwich.repository.sandwich.wrapper.SandwichRepositoryWrapperJPA;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/gorgeous-sandwich")
public class SandwichController {

    private SandwichRepositoryWrapperJPA sandwichRepository;


    public SandwichController(SandwichRepositoryWrapperJPA sandwichRepository){
        this.sandwichRepository = sandwichRepository;
    }

    @GetMapping("/sandwiches")
    public List<SandwichDTO> listAll() {
        return SandwichConverter.convertListToDTO(sandwichRepository.findAll());
    }

    @GetMapping("/sandwiches/{id}")
    public ResponseEntity<SandwichDTO> getById(@PathVariable(value = "id") Long sandwichId) throws ResourceNotFoundException{
        try {
            return ResponseEntity.ok().body(SandwichConverter.convertToDTO(sandwichRepository.getById(sandwichId)));
        }catch (NoSuchElementException e){
            throw new ResourceNotFoundException("Sandwich not found with id " + sandwichId);
        }
    }

    @PostMapping("/sandwiches")
    public SandwichDTO createSandwich(@RequestBody SandwichDTO sandwichDTO) {
        Sandwich sandwich = SandwichConverter.convertFromDTO(sandwichDTO);
        if(sandwich == null){
            throw new IllegalArgumentException("One or more of the input values are wrong");
        }
        return SandwichConverter.convertToDTO(sandwichRepository.save(sandwich));
    }
    
    @PutMapping("/sandwiches/{id}")
    public ResponseEntity<SandwichDTO> addUnitsSandwich(@PathVariable(value = "id") Long sandwichId, @RequestBody SandwichDTO sandwichDTO) throws ResourceNotFoundException {
        try {
            Sandwich sandwich = sandwichRepository.getById(sandwichId);
            sandwich.changeStock(sandwichDTO.stock);
            return ResponseEntity.ok().body(SandwichConverter.convertToDTO(this.sandwichRepository.update(sandwich)));
        }catch (NoSuchElementException e){
            throw new ResourceNotFoundException("Sandwich not found with id " + sandwichId);
        }
    }
   
}


