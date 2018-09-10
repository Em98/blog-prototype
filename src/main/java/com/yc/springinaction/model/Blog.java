package com.yc.springinaction.model;

import com.github.rjeschke.txtmark.Processor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Blog implements Serializable {
    private static final long serialVersionUID =  2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "标题不能为空")
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String title;

    @NotEmpty(message = "摘要不能为空")
    @Size(min = 2, max = 200)
    @Column(nullable = false, length = 200)
    private String summary;

    @Lob //大字段，映射为Mysql Long Text类型
    @Basic(fetch = FetchType.LAZY)
    @NotEmpty(message = "内容不能为空")
    @Size(min = 2)
    @Column(nullable = false)
    private String content; //markdown content

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @NotEmpty(message = "内容不能为空")
    @Size(min = 2)
    @Column(nullable = false)
    private String htmlContent; //convert dm to html content

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "reading") //访问量
    private Long reading = 0L;

    @Column(name = "comments") //评论量
    private Long comments = 0L;

    @Column(name = "likes") //点赞量
    private Long likes = 0L;

    protected Blog(){

    }

    public Blog(String title, String summary, String content){
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.htmlContent = Processor.process(content);
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Long getReading() {
        return reading;
    }

    public void setReading(Long reading) {
        this.reading = reading;
    }

    public Long getComments() {
        return comments;
    }

    public void setComments(Long comments) {
        this.comments = comments;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }
}
