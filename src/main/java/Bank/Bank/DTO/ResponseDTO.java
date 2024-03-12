package Bank.Bank.DTO;

import java.io.Serializable;

public class ResponseDTO implements Serializable {

    private boolean error;
    private String message;

    public ResponseDTO(){
        error = false;
        message = "SUCESS";
    }
    public ResponseDTO(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
