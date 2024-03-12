package Bank.Bank.Services;

import Bank.Bank.DAO.AccountServiceDAO;
import Bank.Bank.DTO.AccountDTO;
import Bank.Bank.DTO.ResponseDTO;
import Bank.Bank.Entities.Account;
import Bank.Bank.Entities.User;
import Bank.Bank.Repository.AccountRepository;
import Bank.Bank.Utils.ExceptionBuilder;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements AccountServiceDAO {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDTO> selectAll() {
        try {
            Iterable<Account> accounts = accountRepository.findAll();

            List<AccountDTO> accountDTOList = new ArrayList<>();

            for (Account account : accounts) {
                AccountDTO accountDTO = new AccountDTO(account);
                accountDTOList.add(accountDTO);
            }

            return accountDTOList;
        } catch (EmptyResultDataAccessException e) {
            System.out.println(ExceptionBuilder.BuildSelectAllException("account"));
            return new ArrayList<AccountDTO>() {{
            }};
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            return new ArrayList<AccountDTO>() {{
            }};
        }
    }

    public AccountDTO selectById(long id) {
        try {
            Optional<Account> account = accountRepository.findById(id);

            if (account.isPresent()) {
                return new AccountDTO(account.get());
            } else {
                throw new EmptyResultDataAccessException(0);
            }
        } catch (EmptyResultDataAccessException e) {
            System.out.println(ExceptionBuilder.BuildSelectByIdException("account"));
            return new AccountDTO();
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            return new AccountDTO();
        }
    }

    public ResponseDTO insert(AccountDTO accountDTO) {
        try {
            List<User> user = accountRepository.selectAllUserById(accountDTO.getUserId());

            if (!user.isEmpty()) {
                Account account = new Account(accountDTO);
                if (!accountRepository.existsById(accountDTO.getId())) {
                    account.setBalance(0);
                    accountRepository.save(account);
                } else {
                    throw new EntityExistsException(ExceptionBuilder.BuildInsertException("account"));
                }
            } else {
                throw new EmptyResultDataAccessException(0);
            }
            return new ResponseDTO();
        } catch (EmptyResultDataAccessException e) {
            return new ResponseDTO(true, ExceptionBuilder.BuildSelectByIdException("user") + ". Account insertion cancelled");
        } catch (EntityExistsException e) {
            return new ResponseDTO(true, e.getMessage());
        } catch (Exception e) {
            return new ResponseDTO(true, "EXCEPTION: " + e);
        }
    }

    public ResponseDTO update(AccountDTO accountDTO) {
        try {
            if (accountRepository.existsById(accountDTO.getId())) {
                accountRepository.save(new Account(accountDTO));
            } else {
                throw new EmptyResultDataAccessException(0);
            }
            return new ResponseDTO();
        } catch (EmptyResultDataAccessException e) {
            return new ResponseDTO(true, ExceptionBuilder.BuildUpdateException("account"));
        } catch (Exception e) {
            return new ResponseDTO(true, "EXCEPTION: " + e);
        }
    }

    public ResponseDTO deleteAll() {
        try {
            accountRepository.deleteAll();
        } catch (Exception e) {
            return new ResponseDTO(false, e.getMessage());
        } // DRY YANGN KISS
        return new ResponseDTO();
    }

    public ResponseDTO deleteById(long id) {
        try {
            if (accountRepository.existsById(id)) {
                accountRepository.deleteById(id);
            } else {
                throw new EmptyResultDataAccessException(0);
            }
            return new ResponseDTO();
        } catch (EmptyResultDataAccessException e) {
            return new ResponseDTO(true, ExceptionBuilder.BuildDeleteException("account"));
        } catch (Exception e) {
            return new ResponseDTO(true, "EXCEPTION: " + e);
        }
    }
}
