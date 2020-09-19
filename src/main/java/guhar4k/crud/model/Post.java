package guhar4k.crud.model;

import java.time.LocalDateTime;

public class Post {
    private long id;
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Post(long id, String content, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
        setNewUpdatedTime();
    }

    private void setNewUpdatedTime(){
        updated = LocalDateTime.now();
    }
}
