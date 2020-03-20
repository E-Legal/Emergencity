package epitech.emergencity.security;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class Token {
    @NotNull
    private UUID id;
    @NotNull
    private UUID user_id;

    private String token;
    private OffsetDateTime updateToken;
    private OffsetDateTime endToken;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
