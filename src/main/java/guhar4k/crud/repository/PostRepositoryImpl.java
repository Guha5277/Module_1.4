package guhar4k.crud.repository;

import guhar4k.crud.model.Post;
import guhar4k.crud.utils.IOUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static guhar4k.crud.utils.Library.REC_END;
import static guhar4k.crud.utils.Library.REC_PART_DELIMITER;

public class PostRepositoryImpl implements PostRepository {
    private final String RES_DIR = "res/";
    private final String FILE_NAME = "posts.txt";
    private final File repositoryFile = new File(RES_DIR, FILE_NAME);

    public PostRepositoryImpl() {
        if (!repositoryFile.exists()) IOUtils.createNewFile(repositoryFile);
    }

    @Override
    public void save(Post post) {
        updatePostId(post);
        String record = post.getId() + REC_PART_DELIMITER
                + post.getCreated() + REC_PART_DELIMITER
                + post.getUpdated() + REC_PART_DELIMITER
                + post.getContent() + REC_END;
        IOUtils.writeRecord(repositoryFile, record);
    }

    @Override
    public Post getById(Long id) {
        List<Post> postsList = getAll();
        Optional<Post> region = postsList.stream().filter(r -> r.getId() == id).findFirst();
        region.orElseThrow(() -> new NoSuchElementException("Repository do not contains record with id " + id));
        return region.get();
    }

    @Override
    public void update(Post post) {
        List<Post> postsList = getAll();
        Optional<Post> resultPost = postsList.stream().filter(r -> r.getId() == post.getId()).findFirst();
        Post editedPost = resultPost.orElseThrow(() -> new NoSuchElementException("Repository do not contains updated item"));
        editedPost.setContent(post.getContent());
        editedPost.setUpdated(LocalDateTime.now());
        saveAll(postsList);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Post> getAll() {
        String fileContentString = IOUtils.fileToString(repositoryFile);
        if (fileContentString.length() == 0 || !fileContentString.contains(REC_END)) return new ArrayList<>();
        String[] encodedPosts = fileContentString.split(REC_END);
        return getPostsFromEncodedStrings(encodedPosts);
    }

    private void saveAll(List<Post> list) {
        StringBuilder sb = new StringBuilder();
        list.forEach(post -> sb.append(post.getId()).append(REC_PART_DELIMITER)
                .append(post.getCreated()).append(REC_PART_DELIMITER)
                .append(post.getUpdated()).append(REC_PART_DELIMITER)
                .append(post.getContent()).append(REC_END));

        IOUtils.rewriteAllRecords(repositoryFile, sb.toString());
    }

    private List<Post> getPostsFromEncodedStrings(String[] encodedPosts) {
        int ID_PART = 0;
        int CREATED_PART = 1;
        int UPDATED_PART = 2;
        int CONTENT_PART = 3;

        return Arrays.stream(encodedPosts).map(s -> {
            String[] parts = s.split(REC_PART_DELIMITER);
            return new Post(Long.valueOf(parts[ID_PART]), parts[CONTENT_PART], LocalDateTime.parse(parts[CREATED_PART]), LocalDateTime.parse(parts[UPDATED_PART]));
        }).collect(Collectors.toList());
    }

    private void updatePostId(Post post) {
        List<Post> postsList = getAll();
        long id = postsList.size() == 0 ? 1 : postsList.get(postsList.size() - 1).getId() + 1;
        post.setId(id);
    }
}
