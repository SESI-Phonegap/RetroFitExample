package android.chris.com.retrofittuto.data.api;



public class Constants {

    public static final class BaseUrl{
        public static final String BASE_URL_EXAMPLE = "https://jsonplaceholder.typicode.com/";
        public static final String BASE_URL_IPIFY = "https://api.ipify.org";
        public static final String BASE_URL_OCR_IMAGE = "https://api.ocr.space/parse/image";
        public static final String BASE_URL_HEADERS = "http://httpbin.org/get";
        public static final String BASE_URL_AUTH = "https://httpbin.org/basic-auth/myusername/mypassword";
    }
    public static final class EndPoint{
        public static final String POSTS = "/posts";
        public static final String USERS = "/users";
        public static final String COMMENTS = "/comments";
    }

    public static final class PostsParams{
        public static final String ID = "id";
        public static final String USER_ID = "userId";
        public static final String TITLE = "title";
        public static final String BODY = "body";

    }

    public static final class Users{
        public static final String ID = "id";
        public static final String NAME = "name";
    }

    public static final class CommentsParams{
        public static final String POST_ID = "postId";
    }

    public static final class OcrImageParams{
        public static final String API_KEY = "apikey";
        public static final String LANGUAGE = "language";
        public static final String KEY = "34eab8418788957";
        public static final String ENG = "eng";
    }
}
