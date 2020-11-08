package guhar4k.crud.controller;

import com.google.gson.reflect.TypeToken;
import guhar4k.crud.model.Post;
import guhar4k.crud.repository.GenericRepository;
import guhar4k.crud.repository.io.JavaIOPostRepositoryImpl;
import guhar4k.crud.repository.json.JsonRepositoryImpl;

import java.util.List;

public class PostController{
    private GenericRepository postRepository;

    public PostController() {
        this.postRepository = new JavaIOPostRepositoryImpl();
//        this.postRepository = new JsonPostRepositoryImpl();
//        this.postRepository = new JsonRepositoryImpl<Post, Long>("posts.json", new TypeToken<List<Post>>(){}.getType());
    }

    public void save(Post post){
        postRepository.save(post);
    }

    public Post getById(Long id){
        return (Post) postRepository.getById(id);
    }

    public void update(Post post){
        postRepository.update(post);
    }

    public void deleteById(Long id){
        postRepository.deleteById(id);
    }

    public List<Post> getAll(){
        return postRepository.getAll();
    }
}
