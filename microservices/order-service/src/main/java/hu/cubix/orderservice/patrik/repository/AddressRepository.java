package hu.cubix.orderservice.patrik.repository;

import hu.cubix.orderservice.patrik.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
