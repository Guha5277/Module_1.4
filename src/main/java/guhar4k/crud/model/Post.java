package guhar4k.crud.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public Post(String content) {
        this.content = content;
        created = LocalDateTime.now();
        updated = LocalDateTime.now();
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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return id + "|" + created.format(formatter) + "|" + updated.format(formatter) + "\n" + content;
    }
}
