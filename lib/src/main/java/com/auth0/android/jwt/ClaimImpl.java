package com.auth0.android.jwt;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The ClaimImpl class implements the Claim interface.
 */
@SuppressWarnings("WeakerAccess")
class ClaimImpl extends BaseClaim {

    private final JsonElement value;

    ClaimImpl(@NonNull JsonElement value) {
        this.value = value;
    }

    @Override
    @Nullable
    public Boolean asBoolean() {
        if (!value.isJsonPrimitive()) {
            return null;
        }
        return value.getAsBoolean();
    }

    @Override
    @Nullable
    public Integer asInt() {
        if (!value.isJsonPrimitive()) {
            return null;
        }
        return value.getAsInt();
    }

    @Override
    @Nullable
    public Double asDouble() {
        if (!value.isJsonPrimitive()) {
            return null;
        }
        return value.getAsDouble();
    }

    @Override
    @Nullable
    public String asString() {
        if (!value.isJsonPrimitive()) {
            return null;
        }
        return value.getAsString();
    }

    @Override
    @Nullable
    public Date asDate() {
        if (!value.isJsonPrimitive()) {
            return null;
        }
        long ms = Long.parseLong(value.getAsString()) * 1000;
        return new Date(ms);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] asArray(Class<T> tClazz) throws DecodeException {
        return asArray(tClazz, new Gson());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] asArray(Class<T> tClazz, Gson gson) throws DecodeException {
        try {
            if (!value.isJsonArray() || value.isJsonNull()) {
                return (T[]) Array.newInstance(tClazz, 0);
            }
            JsonArray jsonArr = value.getAsJsonArray();
            T[] arr = (T[]) Array.newInstance(tClazz, jsonArr.size());
            for (int i = 0; i < jsonArr.size(); i++) {
                arr[i] = gson.fromJson(jsonArr.get(i), tClazz);
            }
            return arr;
        } catch (JsonSyntaxException e) {
            throw new DecodeException("Failed to decode claim as array", e);
        }
    }

    @Override
    public <T> List<T> asList(Class<T> tClazz) throws DecodeException {
        return asList(tClazz, new Gson());
    }

    @Override
    public <T> List<T> asList(Class<T> tClazz, Gson gson) throws DecodeException {
        try {
            if (!value.isJsonArray() || value.isJsonNull()) {
                return new ArrayList<>();
            }
            JsonArray jsonArr = value.getAsJsonArray();
            List<T> list = new ArrayList<>();
            for (int i = 0; i < jsonArr.size(); i++) {
                list.add(gson.fromJson(jsonArr.get(i), tClazz));
            }
            return list;
        } catch (JsonSyntaxException e) {
            throw new DecodeException("Failed to decode claim as list", e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T as(Class<T> tClazz, Gson gson) throws DecodeException {
        try {
            if (value.isJsonNull()) {
                return null;
            }
            if (!value.isJsonObject()) {
                throw new DecodeException("Failed to decode claim is not object");
            }
            return gson.fromJson(value, tClazz);
        } catch (JsonSyntaxException e) {
            throw new DecodeException("Failed to decode claim as object", e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T as(Class<T> tClazz) throws DecodeException {
        return as(tClazz, new Gson());
    }
}
