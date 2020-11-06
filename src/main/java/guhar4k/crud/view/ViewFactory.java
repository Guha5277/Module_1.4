package guhar4k.crud.view;

public class ViewFactory {
    public static final String POSTS = "posts";
    public static final String USERS = "users";
    public static final String REGIONS = "regions";

    public static View getView(String viewType) {
        String type = viewType.toLowerCase();
        switch (type) {
            case POSTS:
                return PostView.getInstance();

            case USERS:
                return UserView.getInstance();

            case REGIONS:
                return RegionView.getInstance();

            default:
                throw new RuntimeException("Illegal argument for type of View instance: " + viewType);
        }
    }
}
