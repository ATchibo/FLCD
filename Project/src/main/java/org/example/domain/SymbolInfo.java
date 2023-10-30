package org.example.domain;

public class SymbolInfo implements Comparable<SymbolInfo> {

    String value;
    ValueTypes valueType;

    public SymbolInfo(String value, ValueTypes valueType) {
        this.value = value;
        this.valueType = valueType;
    }

    public String getValue() {
        return value;
    }

    public ValueTypes getValueType() {
        return valueType;
    }

    @Override
    public int compareTo(SymbolInfo t) {
        return value.compareTo(t.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SymbolInfo symbolInfo) {
            return value.equals(symbolInfo.value);
        }
        return false;
    }

    @Override
    public String toString() {
        return value + ": " + valueType;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
