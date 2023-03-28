package com.example.ecommerceonhtml.service;

import com.example.ecommerceonhtml.DTO.CostumerDTO;
import com.example.ecommerceonhtml.entity.Costumer;
import com.example.ecommerceonhtml.exception.RecordNotFoundException;
import com.example.ecommerceonhtml.repository.CostumerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CostumerService {
    @Autowired
    CostumerRepository costumer;
    @Autowired
    ModelMapper modelMapper;
    private Costumer DtoToEntity(CostumerDTO dto){
        return modelMapper.map(dto,Costumer.class);
    }
    private CostumerDTO EntityToDto(Costumer entity){
        return modelMapper.map(entity,CostumerDTO.class);
    }




    public CostumerDTO save(CostumerDTO userDTO) {


        userDTO.setIsActive(true);
        return this.EntityToDto(costumer.save(this.DtoToEntity(userDTO)));

//        throw new DuplicateRecordException(String.format("This record is already exist on => %d",isFound.getId()));
    }

    public List<CostumerDTO> findAll() {
        List<Costumer> categories= costumer.findAll();
        List<CostumerDTO> userDTOS=new ArrayList<>();
        categories.stream().forEach(entity-> userDTOS.add(this.EntityToDto(entity)));
        return userDTOS;
    }

    public void delete(Long id) {
        Optional<Costumer> isFound=costumer.findById(id);
        if(!ObjectUtils.isEmpty(isFound)) {
            costumer.deleteById(id);
        }
        else {
            throw new RecordNotFoundException(String.format("Costumer not found on => %d",id));
        }
    }

    public CostumerDTO update(CostumerDTO userDTO,Long id) {
//        Costumer isFound=costumer.findByCostumerName(this.DtoToEntity(userDTO).getCostumerName());
        Optional<Costumer> forUpdate=costumer.findById(id);

        if(ObjectUtils.isEmpty(forUpdate))
        {
            throw new RecordNotFoundException(String.format("Costumer not found on => %d",id));

        }
        else {
            forUpdate.get().setName(userDTO.getName());
            return this.EntityToDto(costumer.save(forUpdate.get()));
        }
    }

    public CostumerDTO setActive(Long id) {
        Optional<Costumer> isFound=costumer.findById(id);
        if(!ObjectUtils.isEmpty(isFound)) {
            isFound.get().setIsActive(true);
            return this.EntityToDto(costumer.save(isFound.get()));
        }
        throw new RecordNotFoundException(String.format("Costumer not found on => %d",id));
    }

    public CostumerDTO setDeactivate(Long id) {
        Optional<Costumer> isFound=costumer.findById(id);
        if(!ObjectUtils.isEmpty(isFound)) {
            isFound.get().setIsActive(false);
            return this.EntityToDto(costumer.save(isFound.get()));
        }
        throw new RecordNotFoundException(String.format("Costumer not found on => %d",id));
    }

    public CostumerDTO findById(Long id) {
        Optional<Costumer> isFound=costumer.findById(id);
        if(!ObjectUtils.isEmpty(isFound)) {
            return this.EntityToDto(isFound.get());
        }
        throw new RecordNotFoundException(String.format("Costumer not found on => %d",id));
    }
}
