package jumia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jumia.domain.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
