package com.example.demo.extras;

public class ResponseObject<T, E> {

    private Boolean success;
    private T data;
    private Integer status;
    private E error;


    public ResponseObject(){}

    public ResponseObject(Boolean success, T data, Integer status, E error) {
        this.success = success;
        this.data = data;
        this.status = status;
        this.error = error;
    }

    public Boolean getSuccess(){
        return success;
    }

    public void setSuccess(Boolean success){
        this.success = success;
    }

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data=data;
    }

    public E getError(){
        return error;
    }

    public void setError(E error){
        this.error= error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return "ResponseObject{" +
        "success=" + success +
        ", data=" + data +
        ", status=" + status +
        ", error=" + error +
        '}';
    }
    
}
