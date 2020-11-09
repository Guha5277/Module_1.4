package guhar4k.crud.repository.json;

import com.google.gson.reflect.TypeToken;
import guhar4k.crud.model.Post;
import guhar4k.crud.model.Storable;
import guhar4k.crud.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonPostRepositoryImpl extends JsonRepositoryImpl<Post> implements PostRepository {


    public JsonPostRepositoryImpl() {
        super("posts.json", new TypeToken<List<Post>>() {
        }.getType());
    }

    @Override
    public void save(Post post) {
        super.save(post);
    }


    @Override
    public Post getById(Long id) {
        return (Post) super.getById(id);
    }

    @Override
    public void update(Post post) {
        super.update(post);
    }


    @Override
    public List<Post> getAll() {
        List<Storable> storableList = super.getAllRecords();
        if (storableList.size() == 0) return new ArrayList<>();
        return storableList.stream()
                .map(p -> (Post) p)
                .collect(Collectors.toList());
    }
}
