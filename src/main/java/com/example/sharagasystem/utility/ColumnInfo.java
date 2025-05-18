package com.example.sharagasystem.utility;

import java.util.function.Function;
import lombok.Getter;

@Getter
public class ColumnInfo<T> {
    private final String header;
    private final Function<T, String> dataExtractor;

    public ColumnInfo(String header, Function<T, String> dataExtractor) {
        this.header = header;
        this.dataExtractor = dataExtractor;
    }

}
