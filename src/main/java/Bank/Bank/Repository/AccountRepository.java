package Bank.Bank.Repository;

import Bank.Bank.Entities.Account;
import Bank.Bank.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.id = :userid", nativeQuery = false)
    public List<User> selectAllUserById(@Param("userid") long userId);
}