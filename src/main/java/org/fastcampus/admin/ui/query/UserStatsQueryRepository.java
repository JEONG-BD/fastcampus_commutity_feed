package org.fastcampus.admin.ui.query;

import org.fastcampus.admin.ui.dto.GetDailyRegisterUserResponseDto;

import java.util.List;

public interface UserStatsQueryRepository {
    List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStates(int beforeDays);
}
