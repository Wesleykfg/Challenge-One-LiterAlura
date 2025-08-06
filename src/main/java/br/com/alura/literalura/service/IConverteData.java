package br.com.alura.literalura.service;

public interface IConverteData {
    <T> T getData(String json, Class<T> className);
}
