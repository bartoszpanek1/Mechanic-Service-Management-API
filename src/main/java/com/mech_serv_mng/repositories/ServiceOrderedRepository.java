package com.mech_serv_mng.repositories;

import com.mech_serv_mng.models.ServiceOrdered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOrderedRepository extends JpaRepository<ServiceOrdered, Integer>, JpaSpecificationExecutor<ServiceOrdered> {
}
