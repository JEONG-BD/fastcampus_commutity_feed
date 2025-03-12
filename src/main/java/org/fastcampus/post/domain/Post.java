package org.fastcampus.post.domain;

import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;

public class Post {
    private final Long id;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    public Post(Long id, User author, Content content) {
        this(id, author, content, PostPublicationState.PUBLIC);
    }

    public static Post createPost(Long id, User author, String content, PostPublicationState state){
        return new Post(id, author, new PostContent(content), PostPublicationState.PUBLIC);
    }


    public static Post createDefaultPost(Long id, User author, String content){
        return new Post(id, author, new PostContent(content), PostPublicationState.PUBLIC);
    }

    public Post(Long id, User author, Content content, PostPublicationState state) {
        if(author == null){
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = PostPublicationState.PUBLIC;
    }

    public void like(User user){
        if(this.author.equals(user)){
            throw new IllegalArgumentException();
        }
        likeCount.increase();
    }

    public void unlike(){

        likeCount.decrease();
    }

    public void updatePost(User user, String updateContent, PostPublicationState state){
        if(!this.author.equals(user)){
            throw new IllegalArgumentException();
        }
        this.state = state;
        this.content.updateContent(updateContent);
    }

    public int getLikeCount() {
        return this.likeCount.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Content getContentObj(){
        return content;
    }


}
