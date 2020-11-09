package guhar4k.crud.repository.json;


import com.google.gson.reflect.TypeToken;
import guhar4k.crud.model.Storable;
import guhar4k.crud.model.User;
import guhar4k.crud.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonUserRepositoryImpl extends JsonRepositoryImpl<User> implements UserRepository{

    public JsonUserRepositoryImpl() {
        super("users.json", new TypeToken<List<User>>() {}.getType());
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
        List<User> userList = storableList.stream()
                .map(u -> (User) u)
                .collect(Collectors.toList());
        return userList;
    }

}
