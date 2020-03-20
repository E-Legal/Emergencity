package epitech.emergencity.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokensRequest {
    private UUID id;
    private UUID user_id;

    private String token;
    private OffsetDateTime updateToken;
    private OffsetDateTime endToken;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
