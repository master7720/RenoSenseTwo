package me.sjnez.renosense.features.setting;

import com.google.common.base.Converter;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.Arrays;

public class EnumConverter
extends Converter<Enum, JsonElement> {
    private final Class<? extends Enum> clazz;

    public EnumConverter(Class<? extends Enum> clazz) {
        this.clazz = clazz;
    }

    public static int currentEnum(Enum clazz) {
        for (int i = 0; i < ((Enum[])clazz.getClass().getEnumConstants()).length; ++i) {
            Enum e = ((Enum[])clazz.getClass().getEnumConstants())[i];
            if (!e.name().equalsIgnoreCase(clazz.name())) continue;
            return i;
        }
        return -1;
    }

    public static int enumCount(Enum clazz) {
        return (int)Arrays.stream(clazz.getClass().getEnumConstants()).count();
    }

    public static Enum decreaseEnum(Enum clazz) {
        int index = EnumConverter.currentEnum(clazz);
        for (int i = 0; i < ((Enum[])clazz.getClass().getEnumConstants()).length; ++i) {
            Enum e = ((Enum[])clazz.getClass().getEnumConstants())[i];
            if (i != index - 1) continue;
            return e;
        }
        int c = ((Enum[])clazz.getClass().getEnumConstants()).length;
        return ((Enum[])clazz.getClass().getEnumConstants())[c - 1];
    }

    public static Enum increaseEnum(Enum clazz) {
        int index = EnumConverter.currentEnum(clazz);
        for (int i = 0; i < ((Enum[])clazz.getClass().getEnumConstants()).length; ++i) {
            Enum e = ((Enum[])clazz.getClass().getEnumConstants())[i];
            if (i != index + 1) continue;
            return e;
        }
        return ((Enum[])clazz.getClass().getEnumConstants())[0];
    }

    public static String getProperName(Enum clazz) {
        return Character.toUpperCase(clazz.name().charAt(0)) + clazz.name().toLowerCase().substring(1);
    }

    @Override
    public JsonElement doForward(Enum anEnum) {
        return new JsonPrimitive(anEnum.toString());
    }

    @Override
    public Enum doBackward(JsonElement jsonElement) {
        try {
            return Enum.valueOf(this.clazz, jsonElement.getAsString());
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }
}
