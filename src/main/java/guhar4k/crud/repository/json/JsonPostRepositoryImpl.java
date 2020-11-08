//package guhar4k.crud.repository.json;
//
//import com.google.gson.reflect.TypeToken;
//import guhar4k.crud.model.Post;
//import guhar4k.crud.repository.PostRepository;
//import guhar4k.crud.utils.Library;
//import guhar4k.crud.utils.Utils;
//import guhar4k.crud.utils.json.JsonUtils;
//
//import java.io.File;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//public class JsonPostRepositoryImpl implements PostRepository {
//    private final String FILE_NAME = "posts.json";
//    private Utils<Post> ioUtils;
//
//    public JsonPostRepositoryImpl() {
//        ioUtils = new JsonUtils<>(new File(Library.RES_DIR, FILE_NAME), new TypeToken<List<Post>>(){}.getType());
//        ioUtils.initRepositoryFile();
//    }
//
//    @Override
//    public void save(Post post) {
//        List<Post> postList = getAll();
//        post.setId(postList.size() == 0 ? 1 : postList.size() + 1);
//        ioUtils.saveRecord(post);
//    }
//
//    @Override
//    public Post getById(Long id) {
//        List<Post> postList = getAll();
//        Optional<Post> post = postList.stream().filter(r -> r.getId() == id).findFirst();
//        return post.orElseThrow(() -> new NoSuchElementException("Repository do not contains record with id " + id));
//    }
//
//    @Override
//    public void update(Post post) {
//        List<Post> postList = getAll();
//        Optional<Post> foundPost = postList.stream().filter(r -> r.getId() == post.getId()).findFirst();
//        foundPost.orElseThrow(() -> new NoSuchElementException("Repository do not contains record: " + post)).setContent(post.getContent());
//        ioUtils.rewriteAllRecords(postList);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        List<Post> postList = getAll();
//        Optional<Post> foundPost = postList.stream().filter(r -> r.getId() == id).findFirst();
//        postList.remove(foundPost.orElseThrow(() -> new NoSuchElementException("Repository do not contains record with id " + id)));
//        ioUtils.rewriteAllRecords(postList);
//    }
//
//    @Override
//    public List<Post> getAll() {
//        return ioUtils.getAll();
//    }
//}
