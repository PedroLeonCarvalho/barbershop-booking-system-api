package com.secured_template.service;

import com.secured_template.domain.BarberService;
import com.secured_template.dto.BarberServiceDto;
import com.secured_template.dto.ServiceResponseDto;
import com.secured_template.repository.BarberServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarberServiceService {

     private final BarberServiceRepository serviceRepository;

    public BarberServiceService(BarberServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public void createService(BarberServiceDto serviceDto) {
        BarberService barberService = new BarberService(
                serviceDto.getDescription(),
                serviceDto.getName(),
                serviceDto.getPrice(),
                serviceDto.getIsActive(),
                serviceDto.getDurationMinutes()
        ) ;

        serviceRepository.save(barberService);

    }

    public ServiceResponseDto getServiceById(Long id) {

        var barberService = serviceRepository.findServiceById(id);
        return convertToResponseDto(barberService);

    }

    private ServiceResponseDto convertToResponseDto(BarberService barberService) {
    return  ServiceResponseDto.builder()
            .price(barberService.getPrice())
            .description(barberService.getDescription())
            .name(barberService.getName())
            .durationMinutes(barberService.getDurationMinutes()).build();

    }

    public BarberServiceDto updateService (Long id, BarberServiceDto serviceDto) {
        var barberService = serviceRepository.findServiceById(id);
        barberService.setDescription(serviceDto.getDescription());
        barberService.setName(serviceDto.getName());
        barberService.setPrice(serviceDto.getPrice());
        barberService.setDurationMinutes(serviceDto.getDurationMinutes());

        return serviceDto;



    }

//    public List<ServiceResponseDto> getAllServices() {
//         var lista = serviceRepository.findAll();


    }

