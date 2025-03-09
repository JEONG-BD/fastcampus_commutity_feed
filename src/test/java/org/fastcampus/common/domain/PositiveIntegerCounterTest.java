package org.fastcampus.common.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositiveIntegerCounterTest {

    @Test
    @DisplayName("사용자의 카운트를 1 올린다")
    public void givenCreated_whenIncrease_thenCountIsOne () throws Exception{
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        //when
        counter.increase();
        //then
        Assertions.assertEquals(counter.getCount(), 1);
    }

    @Test
    @DisplayName("사용자의 카운트를 0 줄인다.")
    public void givenCreated_wheDecrease_thenCountIsZero() throws Exception{
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        counter.increase();
        //when
        counter.decrease();
        //then
        Assertions.assertEquals(counter.getCount(), 0);
    }
}
