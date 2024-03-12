package Bank.Bank.Controllers;

import Bank.Bank.DTO.ResponseDTO;
import Bank.Bank.DTO.TransactionDTO;
import Bank.Bank.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<TransactionDTO> selectAll() {
        return transactionService.selectAll();
    }

    @GetMapping("/transactions/{accountId}")
    public List<TransactionDTO> selectAllByAccountId(@PathVariable("accountId") long accountId) {
        return transactionService.selectAllByAccountId(accountId);
    }

    @GetMapping("/transaction")
    public TransactionDTO selectById(@RequestParam long id) {
        return transactionService.selectById(id);
    }

    @PostMapping("/transaction")
    public ResponseEntity<String> insert(@RequestBody TransactionDTO transactionDTO) {
        ResponseDTO responseDTO = transactionService.insert(transactionDTO);

        if (!responseDTO.isError()) {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/transaction")
    public ResponseEntity<String> update(@RequestBody TransactionDTO transactionDTO) {
        ResponseDTO responseDTO = transactionService.update(transactionDTO);

        if (!responseDTO.isError()) {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/transactions")
    public ResponseEntity<String> deleteAll() {
        ResponseDTO responseDTO = transactionService.deleteAll();

        if (!responseDTO.isError()) {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/transactions/{accountId}")
    public ResponseEntity<String> deleteAllByAccountId(@PathVariable long accountId) {
        ResponseDTO responseDTO = transactionService.deleteAllByAccountId(accountId);

        if (!responseDTO.isError()) {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/transaction")
    public ResponseEntity<String> deleteById(@RequestParam long id) {
        ResponseDTO responseDTO = transactionService.deleteById(id);

        if (!responseDTO.isError()) {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
