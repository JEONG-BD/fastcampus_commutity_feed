package org.fastcampus.post.domain.common;

import org.fastcampus.post.common.DatetimeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class DatetimeInfoTest {

    @Test
    @DisplayName("Datetime을 저장하고 조회한다.")
    public void givenCreated_whenUpdated_thenTimeIsUpdatedAndState () throws Exception{
        //given
        DatetimeInfo datetimeInfo = new DatetimeInfo();
        LocalDateTime localDateTime = datetimeInfo.getDateTime();

        //when
        datetimeInfo.updateEditDatetime();

        //then
        Assertions.assertTrue(datetimeInfo.isEdited());
        Assertions.assertNotEquals(localDateTime, datetimeInfo.getDateTime());
    }
}
