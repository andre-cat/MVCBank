package Bank.Bank.Services;

import Bank.Bank.DAO.TransactionServiceDAO;
import Bank.Bank.DTO.ResponseDTO;
import Bank.Bank.DTO.TransactionDTO;
import Bank.Bank.Entities.Account;
import Bank.Bank.Entities.Transaction;
import Bank.Bank.Repository.TransactionRepository;
import Bank.Bank.Utils.BillerClient;
import Bank.Bank.Utils.ExceptionBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements TransactionServiceDAO {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionDTO> selectAll() {
        try {
            Iterable<Transaction> transactions = transactionRepository.findAll();

            List<TransactionDTO> transactionDTOList = new ArrayList<>();

            for (Transaction transaction : transactions) {
                TransactionDTO transactionDTO = new TransactionDTO(transaction);
                transactionDTOList.add(transactionDTO);
            }

            return transactionDTOList;
        } catch (EmptyResultDataAccessException e) {
            System.out.println(ExceptionBuilder.BuildSelectAllException("transacction"));
            return new ArrayList<TransactionDTO>() {{
            }};
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            return new ArrayList<TransactionDTO>() {{
            }};
        }
    }

    public List<TransactionDTO> selectAllByAccountId(long accountId) {
        try {
            Iterable<Transaction> transactions = transactionRepository.selectAllByAccountId(accountId);

            List<TransactionDTO> transactionDTOList = new ArrayList<>();

            for (Transaction transaction : transactions) {
                TransactionDTO transactionDTO = new TransactionDTO(transaction);
                transactionDTOList.add(transactionDTO);
            }

            return transactionDTOList;
        } catch (EmptyResultDataAccessException e) {
            System.out.println(ExceptionBuilder.BuildSelectAllException("transacction") + " with that account ID");
            return new ArrayList<TransactionDTO>() {{
            }};
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            return new ArrayList<TransactionDTO>() {{
            }};
        }
    }

    public TransactionDTO selectById(long id) {
        try {
            Optional<Transaction> possibleTransaction = transactionRepository.findById(id);

            if (possibleTransaction.isPresent()) {
                return new TransactionDTO(possibleTransaction.get());
            } else {
                throw new EmptyResultDataAccessException(0);
            }
        } catch (EmptyResultDataAccessException e) {
            System.out.println(ExceptionBuilder.BuildSelectByIdException("transacction"));
            return new TransactionDTO();
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            return new TransactionDTO();
        }
    }

    @Transactional
    public ResponseDTO insert(TransactionDTO transactionDTO) {
        try {
            Account account = transactionRepository.selectAccountById(transactionDTO.getAccountId());

            if (account != null) {
                Transaction transaction = new Transaction(transactionDTO);
                transaction.setDate(Date.from(java.time.ZonedDateTime.now().toInstant()));

                BillerClient billerClient = new BillerClient(account);
                billerClient.insertTransaction(transaction);
                double newBalance = billerClient.getCurrentBalance();

                if (newBalance >= 0) {

                    transactionRepository.save(transaction);
                    transactionRepository.updateAccountBalanceById(account.getId(), newBalance);

                    return new ResponseDTO();
                } else {
                    return new ResponseDTO(true, "You don't have enough money to make this transaction");
                }
            } else {
                throw new EmptyResultDataAccessException(0);
            }
        } catch (EmptyResultDataAccessException e) {
            return new ResponseDTO(true, ExceptionBuilder.BuildSelectByIdException("account") + ". Transaction cancelled");
        }
    }

    @Transactional
    public ResponseDTO update(TransactionDTO transactionDTO) {
        try {
            Optional<Transaction> possibleTransaction = transactionRepository.findById(transactionDTO.getId());

            if (possibleTransaction.isPresent()) {

                Transaction currentTransaction = possibleTransaction.get();

                Transaction newTransaction = new Transaction(transactionDTO);
                newTransaction.setAccountId(currentTransaction.getAccountId());
                newTransaction.setDate(Date.from(java.time.ZonedDateTime.now().toInstant()));

                Account account = transactionRepository.selectAccountById(currentTransaction.getAccountId());

                BillerClient billerClient = new BillerClient(account);

                billerClient.updateTransaction(currentTransaction, newTransaction);

                double newBalance = billerClient.getCurrentBalance();

                if (newBalance >= 0) {

                    transactionRepository.save(newTransaction);
                    transactionRepository.updateAccountBalanceById(account.getId(), newBalance);

                    return new ResponseDTO();
                } else {
                    return new ResponseDTO(true, "You don't have enough money to make this transaction");
                }
            } else {
                throw new EmptyResultDataAccessException(0);
            }
        } catch (EmptyResultDataAccessException e) {
            return new ResponseDTO(true, ExceptionBuilder.BuildUpdateException("transacction"));
        } catch (Exception e) {
            return new ResponseDTO(true, "EXCEPTION: " + e);
        }
    }

    public ResponseDTO deleteAll() {
        try {
            transactionRepository.deleteAll();
        } catch (Exception e) {
            return new ResponseDTO(false, e.getMessage());
        }
        return new ResponseDTO();
    }

    @Transactional
    public ResponseDTO deleteAllByAccountId(long accountId) {
        try {
            Account account = transactionRepository.selectAccountById(accountId);

            if (account != null) {
                Iterable<Transaction> transactions = transactionRepository.selectAllByAccountId(accountId);

                for (Transaction transaction : transactions) {
                    transactionRepository.deleteById(transaction.getId());
                }

                transactionRepository.updateAccountBalanceById(accountId, 0);
            } else {
                throw new EmptyResultDataAccessException(0);
            }
        } catch (EmptyResultDataAccessException e) {
            return new ResponseDTO(true, ExceptionBuilder.BuildSelectByIdException("account"));
        } catch (Exception e) {
            return new ResponseDTO(false, e.getMessage());
        }
        return new ResponseDTO();
    }

    @Transactional
    public ResponseDTO deleteById(long id) {
        try {
            Optional<Transaction> possibleTransaction = transactionRepository.findById(id);

            if (possibleTransaction.isPresent()) {

                Transaction transaction = possibleTransaction.get();
                Account account = transactionRepository.selectAccountById(transaction.getAccountId());

                BillerClient billerClient = new BillerClient(account);
                billerClient.deleteTransaction(transaction);
                double newBalance = billerClient.getCurrentBalance();

                if (newBalance >= 0) {

                    transactionRepository.deleteById(id);
                    transactionRepository.updateAccountBalanceById(account.getId(), newBalance);

                    return new ResponseDTO();
                } else {
                    return new ResponseDTO(true, "You don't have enough money to make this transaction");
                }
            } else {
                throw new EmptyResultDataAccessException(0);
            }
        } catch (EmptyResultDataAccessException e) {
            return new ResponseDTO(true, ExceptionBuilder.BuildDeleteException("transacction"));
        } catch (Exception e) {
            return new ResponseDTO(true, "EXCEPTION: " + e);
        }
    }
}
