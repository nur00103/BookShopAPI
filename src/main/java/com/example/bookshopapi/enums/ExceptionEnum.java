package com.example.bookshopapi.enums;

public enum ExceptionEnum {

    USER_NOT_FOUND(401,"User can not find with given id"),
    AUTHOR_NOT_FOUND(401,"Author can not find with given id"),
    BOOK_NOT_FOUND(401,"Book can not find with given id"),
    SALES_NOT_FOUND(401,"Sales can not find with given id"),

    VALIDATION(402,"Validation error"),

    INPUT(406,"ID cannot be null and string.Please enter number"),

    UNKNOWN(403,"Unknown error"),

    SUCCESS(200,"Success"),

    ERROR(500,"Something went wrong"),

    EMPTY(405,"Users are not exist yet"),

    EMPTY_AUTHOR(405,"Authors are not exist yet"),
    EMPTY_SALES(405,"Sales are not exist yet"),
    EMPTY_BOOK(405,"Books are not exist yet"),

    BAD_REQUEST(406,"Bad request.Cannot send empty data");

    private final String message;

    private final int code;

    ExceptionEnum(int code,String message) {
        this.message=message;
        this.code=code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
