package com.recepie.sourav.recepieproject.converter;

public interface Converter <T1,T2>{
    public T2 convert(T1 source);
}
