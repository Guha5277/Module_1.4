//package guhar4k.crud.repository.json;
//
//import com.google.gson.reflect.TypeToken;
//import guhar4k.crud.model.User;
//import guhar4k.crud.repository.UserRepository;
//import guhar4k.crud.utils.Library;
//import guhar4k.crud.utils.Utils;
//import guhar4k.crud.utils.json.JsonUtils;
//
//import java.io.File;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//public class JsonUserRepositoryImpl implements UserRepository {
//    private final String FILE_NAME = "users.json";
//    private Utils<User> ioUtils;
//
//    public JsonUserRepositoryImpl() {
//        ioUtils = new JsonUtils<>(new File(Library.RES_DIR, FILE_NAME), new TypeToken<List<User>>(){}.getType());
//        ioUtils.initRepositoryFile();
//    }
//    @Override
//    public void save(User user) {
//        List<User> userList = getAll();
//        user.setId(userList.size() == 0 ? 1 : userList.size() + 1);
//        ioUtils.saveRecord(user);
//    }
//
//    @Override
//    public User getById(Long id) {
//        List<User> userList = getAll();
//        Optional<User> user = userList.stream().filter(u -> u.getId() == id).findFirst();
//        return user.orElseThrow(() -> new NoSuchElementException("Repository do not contains record with id " + id));
//    }
//
//    @Override
//    public void update(User user) {
//        List<User> userList = getAll();
//        User foundUser = userList.stream()
//                .filter(u -> u.getId() == user.getId())
//                .findFirst()
//                .orElseThrow(() -> new NoSuchElementException("Repository do not contains record: " + user));
//        int index = userList.indexOf(foundUser);
//        userList.set(index, user);
//        ioUtils.rewriteAllRecords(userList);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//
//    }
//
//    @Override
//    public List<User> getAll() {
//        return ioUtils.getAll();
//    }
//}
