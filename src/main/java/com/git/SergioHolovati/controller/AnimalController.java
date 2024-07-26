package com.git.SergioHolovati.controller;

import com.git.SergioHolovati.dto.AnimalDTO;
import com.git.SergioHolovati.dto.AnimalRequestDTO;
import com.git.SergioHolovati.dto.AnimalStatusDTO;
import com.git.SergioHolovati.dto.FilterDTO;
import com.git.SergioHolovati.enums.AnimalCategoryEnum;
import com.git.SergioHolovati.enums.AnimalStatusEnum;
import com.git.SergioHolovati.service.AnimalService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService service;
    private static final String PAGE = "0";
    private static final String SIZE = "10";

    @GetMapping("/avaiable")
    public Page<AnimalDTO> getAllAvaiable(@RequestParam(value = "page",required = false,defaultValue = PAGE) int page,
                                          @RequestParam(value = "size",required = false,defaultValue = SIZE) int size,
                                          @RequestParam(value = "category", required = false)AnimalCategoryEnum categoryEnum) throws BadRequestException {
        FilterDTO filter = FilterDTO.createFilter(AnimalStatusEnum.AVAIABLE,categoryEnum,null,null,page,size);;
        return  service.findAllAvaiable(filter);
    }

    @GetMapping("/adopted")
    public Page<AnimalDTO> getAllAdopted(@RequestParam(value = "page",required = false,defaultValue = PAGE) int page,
                                         @RequestParam(value = "size",required = false,defaultValue = SIZE) int size,
                                         @RequestParam(value = "category", required = false)AnimalCategoryEnum categoryEnum,
                                         @RequestParam(value = "startAdoptionDate",required = false) LocalDate startAdoptionDate,
                                         @RequestParam(value = "finalAdoptionDate",required = false) LocalDate finalAdoptionDate) throws BadRequestException {
        FilterDTO filter = FilterDTO.createFilter(AnimalStatusEnum.ADOPTED,categoryEnum,startAdoptionDate,finalAdoptionDate,page,size);
        return  service.findAllAdopted(filter);
    }

    @GetMapping("/{id}")
    public AnimalDTO getById(@PathVariable String id) throws ChangeSetPersister.NotFoundException {
        return service.findById(id);
    }

    @GetMapping
    public Page<AnimalDTO> getAll(@RequestParam(value = "page",required = false,defaultValue = PAGE) int page,
                                  @RequestParam(value = "size",required = false,defaultValue = SIZE) int size,
                                  @RequestParam(value = "status",required = false) AnimalStatusEnum statusEnum,
                                  @RequestParam(value = "category", required = false)AnimalCategoryEnum categoryEnum,
                                  @RequestParam(value = "startAdoptionDate",required = false) LocalDate startAdoptionDate,
                                  @RequestParam(value = "finalAdoptionDate",required = false) LocalDate finalAdoptionDate) throws BadRequestException {
        FilterDTO filter = FilterDTO.createFilter(statusEnum,categoryEnum,startAdoptionDate,finalAdoptionDate,page,size);
        return  service.findAll(filter);
    }

    @PostMapping
    public AnimalDTO save(@RequestBody AnimalRequestDTO requestDTO){
        return service.save(requestDTO);
    }

    @PutMapping("/{id}/animal")
    public AnimalDTO updateAnimal(@PathVariable String id,
                                  @RequestBody AnimalRequestDTO requestDTO) throws ChangeSetPersister.NotFoundException {
        return service.updateAnimal(id,requestDTO);
    }

    @PutMapping("/{id}/status")
    public AnimalDTO updateStatus(@PathVariable String id,
                                  @RequestBody AnimalStatusDTO animalStatus) throws ChangeSetPersister.NotFoundException {
        return service.updateStatus(id,animalStatus);
    }

    @GetMapping("/status")
    public List<Map<String, String>> status(){
        return service.status();
    }

    @GetMapping("/category")
    public List<Map<String, String>> category(){
        return service.category();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
