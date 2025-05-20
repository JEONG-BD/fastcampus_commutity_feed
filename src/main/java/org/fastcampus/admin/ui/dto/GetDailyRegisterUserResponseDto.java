package org.fastcampus.admin.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class GetDailyRegisterUserResponseDto {

    private LocalDate date;
    private Long count;


}
