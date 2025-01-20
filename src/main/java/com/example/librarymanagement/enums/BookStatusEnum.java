package com.example.librarymanagement.enums;

import com.example.librarymanagement.exceptions.AppException;
import com.example.librarymanagement.exceptions.ErrorCode;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum BookStatusEnum {
    Borrowed, Returned, Overdue, InStock
    ;

    public static BookStatusEnum fromString(String stringStatus){
        return Arrays.stream(BookStatusEnum.values())
                .filter(s -> StringUtils.equalsIgnoreCase(s.name(), stringStatus))
                .findFirst()
                .orElseThrow(() -> new AppException(ErrorCode.STATUS_NOT_FOUND));
    }
}
