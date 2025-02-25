package com.ds.springframework.ciz.beans;

/**
 * @author ds
 * @date 2025/2/24
 * @description
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
    
}
