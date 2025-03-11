package org.fastcampus.post.domain.content;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CommentContentTest {



    @Test
    @DisplayName("Null Comment 생성에 실패한다")
    public void givenCreated_whenNull_thenThrowsError() throws Exception{
        //given
        String contentText= null;
        //when

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CommentContent(contentText));
    }


    @Test
    @DisplayName("일반 Comment 생성에 성공한다")
    public void givenCreated_whenText_thenNothing() throws Exception{
        //given
        String contentText= "ABC";
        //when

        //then
        Assertions.assertDoesNotThrow(() -> new CommentContent(contentText));
    }

    @Test
    @DisplayName("긴 Comment 생성에 실패한다")
    public void givenCreated_whenOver_thenNothing() throws Exception{
        //given
        String contentText= "ABC".repeat(100);
        //when

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CommentContent(contentText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 꿱"})
    @DisplayName("한글 Comment 생성에 성공한다")
    public void givenCreated_whenKorean_thenNothing(String strings) throws Exception{
        //given
        String repeatString = strings.repeat(1);
        //when

        //then
        Assertions.assertDoesNotThrow(() -> new CommentContent(repeatString));
    }
}
