package com.riffhub.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//response result
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code;//status code 0-success  1-fail
    private String message;//tips message
    private T data;//response data

    //carry data return
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "success", data);
    }

    //No data
    public static Result success() {
        return new Result(0, "success", null);
    }

    public static Result error(String message) {
        return new Result(1, message, null);
    }
}
