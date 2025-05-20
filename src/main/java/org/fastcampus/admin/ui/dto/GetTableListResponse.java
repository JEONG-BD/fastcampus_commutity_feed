package org.fastcampus.admin.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class GetTableListResponse<T> {

    private int totalCount;
    private List<T> tableDate;
}
