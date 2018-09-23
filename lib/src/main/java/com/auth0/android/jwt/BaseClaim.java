package com.auth0.android.jwt;


import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The BaseClaim class is a Claim implementation that returns null when any of it's methods it's called.
 */
class BaseClaim implements Claim {

    @Nullable
    @Override
    public Boolean asBoolean() {
        return null;
    }

    @Nullable
    @Override
    public Integer asInt() {
        return null;
    }

    @Nullable
    @Override
    public Double asDouble() {
        return null;
    }

    @Nullable
    @Override
    public String asString() {
        return null;
    }

    @Nullable
    @Override
    public Date asDate() {
        return null;
    }
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] asArray(Class<T> tClazz) throws DecodeException {
        return asArray(tClazz, null);
    }
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] asArray(Class<T> tClazz, Gson gson) throws DecodeException {
        return (T[]) Array.newInstance(tClazz, 0);
    }
    @Override
    public <T> List<T> asList(Class<T> tClazz) throws DecodeException {
        return asList(tClazz,null);
    }
    @Override
    public <T> List<T> asList(Class<T> tClazz, Gson gson) throws DecodeException {
        return Collections.emptyList();
    }

    @Override
    public <T> T as(Class<T> tClazz) throws DecodeException{
        return as(tClazz,null);
    }

    @Override
    public <T> T as(Class<T> tClazz, Gson gson) throws DecodeException {
        return null;
    }
}
