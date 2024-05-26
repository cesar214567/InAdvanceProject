package in.advance.backend.database.repositories;

import in.advance.backend.database.entitites.PhoneDao;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRespository extends CrudRepository<PhoneDao,Integer> {
}
