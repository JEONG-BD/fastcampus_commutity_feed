package org.fastcampus.post.domain.content;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

import static org.junit.jupiter.api.Assertions.*;

public class PostContentTest {

    @Test
    @DisplayName("콘텐츠를 작성하고 저장한다")
    public void givenContentLength_whenCreated_thenReturnTextContent() throws Exception {
        //given
        String text = "this is a test";
        //when
        PostContent content = new PostContent(text);
        //then
        assertEquals(text, content.getContentText());
    }

    @Test
    @DisplayName("콘텐츠를 작성하고 저장에 실패한다")
    public void givenContentLengthIsOver_whenCreated_thenThrowError() throws Exception {
        //given
        String text = "a".repeat(501);

        //then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵"})
    @DisplayName("긴 콘텐츠를 작성하고 저장에 실패한다")
    public void givenContentLengthIsOverKorean_whenCreated_thenThrowError(String koreanWord) throws Exception {
        //given
        String text = koreanWord.repeat(501);

        //then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @Test
    @DisplayName("짧은 콘텐츠를 작성하고 저장에 실패한다")
    public void givenContentLengthIsUnder_whenCreated_thenThrowError() throws Exception {
        //given
        String text = "a".repeat(4);

        //then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Null 콘텐츠를 작성하고 저장에 실패한다")
    public void givenContentNull_whenCreated_thenThrowError(String value) throws Exception {
        //given

        //then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));

    }
}