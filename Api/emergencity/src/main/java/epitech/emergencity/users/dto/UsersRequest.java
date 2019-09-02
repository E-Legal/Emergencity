package epitech.emergencity.users.dto;


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
public class UsersRequest {
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String password;
    private Boolean admin;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
