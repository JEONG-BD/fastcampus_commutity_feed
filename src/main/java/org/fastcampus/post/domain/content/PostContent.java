package org.fastcampus.post.domain.content;

import org.fastcampus.post.domain.content.Content;

public class PostContent extends Content {

    private static final int MIN_POST_LENGTH = 5;
    private static final int MAX_POST_LENGTH = 500;

    public PostContent(String contentText) {
        super(contentText);
    }

    @Override
    public void checkText(String contentText) {

        if(contentText == null){
            throw new IllegalArgumentException();
        }
        if(contentText.length() < MIN_POST_LENGTH){
            throw new IllegalArgumentException("MIN");
        }
        if(contentText.length() > MAX_POST_LENGTH){
            throw new IllegalArgumentException("MAX");
        }
    }





}
