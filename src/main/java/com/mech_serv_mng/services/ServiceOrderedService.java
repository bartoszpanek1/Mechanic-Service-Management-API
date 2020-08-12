package com.mech_serv_mng.services;


import com.mech_serv_mng.models.ServiceOrdered;
import com.mech_serv_mng.repositories.ServiceOrderedRepository;
import com.mech_serv_mng.services.specifications.ServiceOrderedSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceOrderedService {
    private final ServiceOrderedRepository serviceOrderedRepository;

    public ServiceOrderedService(ServiceOrderedRepository serviceOrderedRepository) {
        this.serviceOrderedRepository = serviceOrderedRepository;
    }

    public Optional<ServiceOrdered> findServiceOrdered(Integer id) {
        return serviceOrderedRepository.findById(id);
    }

    public List<ServiceOrdered> findMatchingServicesOrdered(Double priceLow, Double priceHigh, String description,
                                                            String acceptedDateLow, String acceptedDateHigh,
                                                            String deadlineLow, String deadlineHigh, Boolean completed, Integer customerId) {
        Specification<ServiceOrdered> spec = Specification
                .where(
                        priceLow == null && priceHigh == null ? null :
                                priceHigh == null ? ServiceOrderedSpecifications.priceBetween(priceLow, Double.MAX_VALUE) :
                                        priceLow == null ? ServiceOrderedSpecifications.priceBetween(Double.MIN_VALUE, priceHigh) :
                                                ServiceOrderedSpecifications.priceBetween(priceLow, priceHigh)
                )
                .and(description == null ? null : ServiceOrderedSpecifications.descriptionContains(description.toUpperCase()))
                .and(completed == null ? null : ServiceOrderedSpecifications.completedEquals(completed));

        List<ServiceOrdered> ret = serviceOrderedRepository.findAll(spec);

        if (acceptedDateLow != null || acceptedDateHigh != null || deadlineLow != null || deadlineHigh != null || customerId != null) {
            List<ServiceOrdered> services = serviceOrderedRepository.findAll();
            List<ServiceOrdered> servs = services.stream().filter(e ->
                    e.getDeadline().isBefore(acceptedDateHigh == null ? LocalDate.MAX : LocalDate.parse(acceptedDateHigh)) &&
                            e.getDeadline().isAfter(acceptedDateLow == null ? LocalDate.MIN : LocalDate.parse(acceptedDateLow)) &&
                            e.getAccepted().isBefore(deadlineHigh == null ? LocalDate.MAX : LocalDate.parse(deadlineHigh)) &&
                            e.getAccepted().isAfter(deadlineLow == null ? LocalDate.MIN : LocalDate.parse(deadlineLow)) &&
                            e.getCar().getCustomer().getId().equals(customerId)
            ).collect(Collectors.toList());
            ret.retainAll(servs);
        }

        return ret;
    }

    public void addService(ServiceOrdered serviceOrdered) {
        toUpperServiceOrdered(serviceOrdered);
        serviceOrderedRepository.save(serviceOrdered);
    }

    public void deleteServiceOrdered(Integer id) {
        serviceOrderedRepository.deleteById(id);
    }

    private void toUpperServiceOrdered(ServiceOrdered serviceOrdered) {
        serviceOrdered.setDescription(serviceOrdered.getDescription().toUpperCase());
    }

}