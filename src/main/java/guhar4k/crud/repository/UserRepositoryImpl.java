package guhar4k.crud.repository;

import guhar4k.crud.model.Post;
import guhar4k.crud.model.Region;
import guhar4k.crud.model.User;
import guhar4k.crud.utils.IOUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static guhar4k.crud.utils.Library.*;

public class UserRepositoryImpl implements UserRepository {
    private final String RES_DIR = "res/";
    private final String FILE_NAME = "users.txt";
    private final File repositoryFile = new File(RES_DIR, FILE_NAME);
    private PostRepository postRepository;
    private RegionRepository regionRepository;

    public UserRepositoryImpl() {
        this.postRepository = new PostRepositoryImpl();
        this.regionRepository = new RegionRepositoryImpl();
        if (!repositoryFile.exists()) IOUtils.createNewFile(repositoryFile);
    }

    @Override
    public void save(User user) {
        updateUserId(user);
        String record = userToStringRecord(user);
        IOUtils.writeRecord(repositoryFile, record);
    }

    @Override
    public User getById(Long id) {
        List<User> users = getAll();
        Optional<User> user = users.stream().filter(u -> u.getId() == id).findFirst();
        user.orElseThrow(() -> new NoSuchElementException("Repository do not contains record with id " + id));
        return user.get();
    }

    @Override
    public void update(User user) {
        List<User> users = getAll();
        Optional<User> resultUserOptional = users.stream().filter(u -> u.getId() == user.getId()).findFirst();
        User resultUser = resultUserOptional.orElseThrow(() -> new NoSuchElementException("Repository do not contains updated item"));
        resultUser.setFirstName(user.getFirstName());
        resultUser.setLastName(user.getLastName());
        resultUser.setPosts(user.getPosts());
        resultUser.setRegion(user.getRegion());
        saveAll(users);
    }

    @Override
    public void deleteById(Long id) {
        List<User> users = getAll();
        Optional<User> user = users.stream().filter(u -> u.getId() == id).findFirst();
        user.orElseThrow(() -> new NoSuchElementException("Repository do not contains record with id " + id));
        users.remove(user.get());
        saveAll(users);
    }

    @Override
    public List<User> getAll() {
        String fileContentString = IOUtils.fileToString(repositoryFile);
        if (fileContentString.length() == 0 || !fileContentString.contains(REC_END)) return new ArrayList<>();
        String[] encodedUsers = fileContentString.split(REC_END);
        return getUsersFromEncodedStrings(encodedUsers);
    }

    private String userToStringRecord(User user) {
        return user.getId() + REC_PART_DELIMITER
                + user.getFirstName() + REC_PART_DELIMITER
                + user.getLastName() + REC_PART_DELIMITER
                + user.getRegion().getId() + REC_PART_DELIMITER
                + postListToString(user.getPosts()) + REC_END;
    }

    private String postListToString(List<Post> postList) {
        if (postList == null || postList.size() == 0) return "0";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < postList.size(); i++) {
            sb.append(postList.get(i).getId());
            if (i != postList.size() - 1) sb.append(REC_POST_ID_DELIMITER);
        }

        return sb.toString();
    }

    private List<User> getUsersFromEncodedStrings(String[] encodedUsers) {
        int ID_PART = 0;
        int FIRST_NAME_PART = 1;
        int LAST_NAME_PART = 2;
        int REGION_PART = 3;
        int POSTS_PART = 4;

        return Arrays.stream(encodedUsers).map(s -> {
            String[] userParts = s.split(REC_PART_DELIMITER);
            return new User(Long.valueOf(userParts[ID_PART]), userParts[FIRST_NAME_PART],
                    userParts[LAST_NAME_PART], getRegion(Long.valueOf(userParts[REGION_PART])),
                    postListFromString(userParts[POSTS_PART]));
        }).collect(Collectors.toList());
    }

    private List<Post> postListFromString(String encodedPostList) {
        String[] postsId = encodedPostList.split(REC_POST_ID_DELIMITER);
        if (postsId.length == 1 && postsId[0].equals("0")) return new ArrayList<>();
        List<Post> postList = new ArrayList<>();

        for (String pl : postsId) {
            postList.add(postRepository.getById(Long.valueOf(pl)));
        }
        return postList;
    }

    private Region getRegion(Long id) {
        return regionRepository.getById(id);
    }

    private void updateUserId(User user) {
        List<User> users = getAll();
        long id = users.size() == 0 ? 1 : users.get(users.size() - 1).getId() + 1;
        user.setId(id);
    }

    private void saveAll(List<User> list) {
        StringBuilder sb = new StringBuilder();
        list.forEach(user -> sb.append(userToStringRecord(user)));
        IOUtils.rewriteAllRecords(repositoryFile, sb.toString());
    }
}
