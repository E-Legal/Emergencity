package epitech.emergencity.users;

import lombok.Builder;
import lombok.Data;
import net.jcip.annotations.NotThreadSafe;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class User {
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String password;
    private String token;
    private Boolean admin;
    private Boolean superuser;
    private Boolean algorithm;

    private OffsetDateTime updateToken;
    private OffsetDateTime endToken;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
