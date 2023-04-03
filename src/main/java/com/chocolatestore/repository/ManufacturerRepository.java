package com.chocolatestore.repository;

import com.chocolatestore.domain.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    @Query(nativeQuery = true, value = "insert into manufacturers values(default, :#{#manufacturer.name}, default, default) returning *")
    Manufacturer save(@Param("manufacturer") Manufacturer manufacturer);
}
