package guhar4k.crud.repository.json;


import guhar4k.crud.model.Post;
import guhar4k.crud.model.Storable;
import guhar4k.crud.model.User;
import guhar4k.crud.repository.PostRepository;
import guhar4k.crud.repository.RegionRepository;
import guhar4k.crud.repository.UserRepository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonUserRepositoryImpl extends JsonRepositoryImpl<User> implements UserRepository {
    private RegionRepository regionRepository;
    private PostRepository postRepository;

    public JsonUserRepositoryImpl(String fileName, Type listType, RegionRepository regionRepository, PostRepository postRepository) {
        super(fileName, listType);
        this.regionRepository = regionRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void save(User user) {
        super.save(user);
    }

    @Override
    public User getById(Long id) {
        return (User) super.getById(id);
    }

    @Override
    public void update(User user) {
        super.update(user);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        List<Storable> storableList = super.getAllRecords();
        if (storableList.size() == 0) return new ArrayList<>();
        return storableList.stream()
                .map(u -> (User) u)
                .peek(user -> {
                    user.setRegion(regionRepository.getById(user.getRegion().getId()));

                    List<Post> userPosts = user.getPosts();
                    if (userPosts != null && userPosts.size() > 0) {
                        for (int i = 0; i < userPosts.size(); i++) {
                            userPosts.set(i, postRepository.getById(userPosts.get(i).getId()));
                        }
                    }
                }).collect(Collectors.toList());
    }

}
