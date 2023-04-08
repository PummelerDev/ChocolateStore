package com.chocolatestore.repository;

import com.chocolatestore.domain.DTO.ProductDTORequest;
import com.chocolatestore.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "insert into products values(default, :#{#product.kind.toString()}, :#{#product.topping.toString()}, :#{#product.manufacturerId}, :#{#product.name}, :#{#product.description}, :#{#product.weight}, :#{#product.price}) returning *")
    Product save(@Param("product") ProductDTORequest pdr);

    @Query(nativeQuery = true, value = "update products set kind =:#{#product.kind.toString()}, topping =:#{#product.topping.toString()}, manufacturer_id =:#{#product.manufacturerId}, name =:#{#product.name}, description =:#{#product.description}, weight =:#{#product.weight}, price =:#{#product.price} where id =:#{#id} returning *")
    Product saveAndFlushCustom(@Param("id") long id ,@Param("product") ProductDTORequest pdr);
}
