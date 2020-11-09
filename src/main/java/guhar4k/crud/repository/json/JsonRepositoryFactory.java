package guhar4k.crud.repository.json;

import com.google.gson.reflect.TypeToken;
import guhar4k.crud.model.Post;
import guhar4k.crud.model.Region;
import guhar4k.crud.model.User;
import guhar4k.crud.repository.PostRepository;
import guhar4k.crud.repository.RegionRepository;
import guhar4k.crud.repository.UserRepository;

import java.lang.reflect.Type;
import java.util.List;

public class JsonRepositoryFactory {
    private static final String regionsFileName = "regions.json";
    private static final String postsFileName = "posts.json";
    private static final String usersFileName = "users.json";

    private static final Type regionsListType = new TypeToken<List<Region>>() {}.getType();
    private static final Type postsListType = new TypeToken<List<Post>>() {}.getType();
    private static final Type usersListType = new TypeToken<List<User>>() {}.getType();

    public static RegionRepository getRegionRepository(){
        return new JsonRegionRepositoryImpl(regionsFileName, regionsListType);
    }

    public static PostRepository getPostRepository(){
        return new JsonPostRepositoryImpl(postsFileName, postsListType);
    }

    public static UserRepository getUserRepository(){
       return new JsonUserRepositoryImpl(usersFileName, usersListType, getRegionRepository());
    }
}
