package epitech.emergencity.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class DomainError {
    private int status;
    private JSONObject data;

    public static DomainError ServerError(JSONObject data) {
        return new DomainError(HttpStatus.INTERNAL_SERVER_ERROR.value(), data);
    }

    public static DomainError NotFound(JSONObject data) {
      return new DomainError(HttpStatus.NOT_FOUND.value(), data);
    }


    public static DomainError Unauthorized(JSONObject data) {
        return new DomainError(HttpStatus.UNAUTHORIZED.value(), data);
    }

    public static DomainError BadRequest(JSONObject data) {
        return new DomainError(HttpStatus.BAD_REQUEST.value(), data);
    }


    public static DomainError Conflict(JSONObject data) {
        return new DomainError(HttpStatus.CONFLICT.value(), data);
    }
}
