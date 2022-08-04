package co.com.movii.repository;

import co.com.movii.entities.AccountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<AccountEntity, String> {

    AccountEntity findByPhoneNumberOrderByIdAccountEntityDesc(String phoneNumber);

    AccountEntity findByIdAccountEntity(String id);

}
