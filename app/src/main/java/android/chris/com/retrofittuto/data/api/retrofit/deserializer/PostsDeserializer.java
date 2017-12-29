package android.chris.com.retrofittuto.data.api.retrofit.deserializer;


import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;

public class PostsDeserializer<T> implements ListDeserializer<T> {
    @Override
    public List<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        //JsonObject postJsonObject = json.getAsJsonObject().get("posts").getAsJsonObject();
        JsonElement postJsonArray = json.getAsJsonArray();
        return new Gson().fromJson(postJsonArray,typeOfT);
    }
}
