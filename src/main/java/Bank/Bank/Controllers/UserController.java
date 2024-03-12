package Bank.Bank.Controllers;

import Bank.Bank.DTO.ResponseDTO;
import Bank.Bank.DTO.UserDTO;
import Bank.Bank.Services.UserService;
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
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDTO> selectAll() {
        return userService.selectAll();
    }

    @GetMapping("/user")
    public UserDTO selectById(@RequestParam long id) {
        return userService.selectById(id);
    }

    @PostMapping("/user")
    public ResponseEntity<String> insert(@RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO = userService.insert(userDTO);

        if (!responseDTO.isError()) {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/user")
    public ResponseEntity<String> update(@RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO = userService.update(userDTO);

        if (!responseDTO.isError()) {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<String> deleteAll() {
        ResponseDTO responseDTO = userService.deleteAll();

        if (!responseDTO.isError()) {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteById(@RequestParam long id) {
        ResponseDTO responseDTO = userService.deleteById(id);

        if (!responseDTO.isError()) {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
