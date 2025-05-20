package org.fastcampus.admin.ui.dto.users;

import lombok.*;
import org.fastcampus.common.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
public class GetUserTableRequestDto extends Pageable {
    private String name;
}
