package Bank.Bank.Controllers;

import Bank.Bank.DTO.AccountDTO;
import Bank.Bank.DTO.ResponseDTO;
import Bank.Bank.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<AccountDTO> selectAll() {
        return accountService.selectAll();
    }

    @GetMapping("/account")
    public AccountDTO selectById(@RequestParam long id) {
        return accountService.selectById(id);
    }

    @PostMapping("/account")
    public ResponseEntity<String> insert(@RequestBody AccountDTO accountDTO) {
        ResponseDTO responseDTO = accountService.insert(accountDTO);

        if (!responseDTO.isError()) {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/accounts")
    public ResponseEntity<String> deleteAll() {
        ResponseDTO responseDTO = accountService.deleteAll();

        if (!responseDTO.isError()) {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/account")
    public ResponseEntity<String> deleteById(@RequestParam long id) {
        ResponseDTO responseDTO = accountService.deleteById(id);

        if (!responseDTO.isError()) {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
