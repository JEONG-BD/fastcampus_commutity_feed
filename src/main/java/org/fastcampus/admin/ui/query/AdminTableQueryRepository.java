package org.fastcampus.admin.ui.query;

import org.fastcampus.admin.ui.dto.GetTableListResponse;
import org.fastcampus.admin.ui.dto.posts.GetPostTableRequestDto;
import org.fastcampus.admin.ui.dto.posts.GetPostTableResponseDto;
import org.fastcampus.admin.ui.dto.users.GetUserTableRequestDto;
import org.fastcampus.admin.ui.dto.users.GetUserTableResponseDto;

public interface AdminTableQueryRepository {
    GetTableListResponse<GetUserTableResponseDto> getUserTableDate(GetUserTableRequestDto dto);
    GetTableListResponse<GetPostTableResponseDto> getPostTableDate(GetPostTableRequestDto dto);

}
