package org.fastcampus.admin.ui.dto.users;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.fastcampus.common.utils.TimeCalculator;

import java.time.LocalDateTime;

@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class GetUserTableResponseDto {

    @Getter
    private Long id;
    @Getter
    private String email;
    @Getter
    private String name;
    @Getter
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;

    public String getCreatedAt(){
        return TimeCalculator.getFormatterDate(createdAt);
    }
    public String getUpdatedAt(){
        return TimeCalculator.getFormatterDate(updatedAt);
    }
    public String getLastLoginAt(){
        return TimeCalculator.getFormatterDate(lastLoginAt);
    }
}
