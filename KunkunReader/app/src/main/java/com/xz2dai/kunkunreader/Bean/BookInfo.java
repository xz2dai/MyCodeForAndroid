package com.xz2dai.kunkunreader.Bean;

public class BookInfo {
    private String bookNum; //书名
    private String Count;   //总字数
    private String Author;  //作者
    private String Style;   //分类
    private String chapterNum;  //章节号
    private String Content; //内容

    public String getBookNum() {
        return bookNum;
    }

    public void setBookNum(String bookNum) {
        this.bookNum = bookNum;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getStyle() {
        return Style;
    }

    public void setStyle(String style) {
        Style = style;
    }

    public String getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(String chapterNum) {
        this.chapterNum = chapterNum;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
