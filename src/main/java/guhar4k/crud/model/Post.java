package guhar4k.crud.model;

import java.time.LocalDateTime;

public class Post {
    private long id;
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;

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
}
