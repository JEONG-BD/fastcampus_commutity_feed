package org.fastcampus.post.domain.comment;

import org.fastcampus.post.domain.content.Content;

public class CommentContent extends Content {

    private static final int MAX_POST_LENGTH = 100;
    public CommentContent(String contentText) {
        super(contentText);
    }

    @Override
    public void checkText(String contentText) {

        if(contentText == null){
            throw new IllegalArgumentException();
        }
        if(contentText.length() > MAX_POST_LENGTH){
            throw new IllegalArgumentException();
        }
    }
}
