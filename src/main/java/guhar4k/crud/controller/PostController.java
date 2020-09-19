package guhar4k.crud.controller;

import guhar4k.crud.model.Post;
import guhar4k.crud.repository.PostRepository;
import guhar4k.crud.repository.PostRepositoryImpl;

import java.util.List;

public class PostController{
    private PostRepository postRepository;

    public PostController() {
        this.postRepository = new PostRepositoryImpl();
    }

    public void save(Post post){
        postRepository.save(post);
    }

    public Post getById(Long id){
        return postRepository.getById(id);
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
