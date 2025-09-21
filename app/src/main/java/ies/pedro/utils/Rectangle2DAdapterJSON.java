package ies.pedro.utils;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import java.io.IOException;
import java.lang.reflect.Type;

public class Rectangle2DAdapterJSON implements JsonSerializer<Rectangle2D>, JsonDeserializer<Rectangle2D> {
    @Override
    public JsonElement serialize(Rectangle2D src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("minX", src.getMinX());
        obj.addProperty("minY", src.getMinY());
        obj.addProperty("width", src.getWidth());
        obj.addProperty("height", src.getHeight());
        return obj;
    }

    @Override
    public Rectangle2D deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        return new Rectangle2D(
                obj.get("minX").getAsDouble(),
                obj.get("minY").getAsDouble(),
                obj.get("width").getAsDouble(),
                obj.get("height").getAsDouble()
        );
    }
}