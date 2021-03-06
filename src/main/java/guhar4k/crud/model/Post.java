package guhar4k.crud.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post implements Storable{
    private long id;
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Post(long id) {
        this.id = id;
    }

    public Post(long id, String content) {
        this(id);
        this.content = content;
    }

    public Post(String content) {
        this.content = content;
        created = LocalDateTime.now();
        updated = LocalDateTime.now();
    }

    public Post(long id, String content, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void cloneFrom(Storable storable) {
        this.content = ((Post)storable).getContent();
        setNewUpdatedTime();
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

    @Override
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
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("|");
        if (created != null) sb.append(created.format(formatter)).append("|");
        if (updated != null) sb.append(updated.format(formatter)).append("|");
        sb.append("\n").append(content);

        return sb.toString();
    }
}
