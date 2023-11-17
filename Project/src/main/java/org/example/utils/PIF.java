package org.example.utils;

import org.apache.commons.lang3.tuple.Pair;
import org.example.domain.SymbolInfo;
import org.example.domain.ValueTypes;

import java.util.ArrayList;
import java.util.List;

public class PIF {
    private final List<Pair<SymbolInfo, Integer>> pif;

    public PIF() {
        pif = new ArrayList<>();
    }

    public void add(SymbolInfo token, Integer index) {
        pif.add(Pair.of(token, index));
    }

    public Integer getTokenIndex(SymbolInfo token) {
        for (Pair<SymbolInfo, Integer> pair : pif) {
            if (pair.getKey().equals(token)) {
                return pair.getValue();
            }
        }

        return null;
    }

    public Integer size() {
        return pif.size();
    }

    public void clear() {
        pif.clear();
    }

    @Override
    public String toString() {
        StringBuilder tableStr = new StringBuilder();
            for (Pair<SymbolInfo, Integer> pair : pif) {
            tableStr.append(pair.getKey().getValue()).append(" | ").append(pair.getValue()).append("\n");
        }
        return "PIF\n" +
            "Size: " + pif.size() + "\n" +
            tableStr;
    }
}
