package org.fastcampus.post.domain.content;

import org.fastcampus.post.common.DatetimeInfo;

public abstract class Content {
    String contentText;
    final DatetimeInfo datetimeInfo;

    public Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        this.datetimeInfo = new DatetimeInfo();
    }

    public void updateContent(String contentText){
        checkText(contentText);
        this.contentText = contentText;
        this.datetimeInfo.updateEditDatetime();
    }

    public abstract void checkText(String contentText);

    public String getContentText() {
        return contentText;
    }
}
