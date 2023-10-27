package org.example.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class PIF {
    private final List<Pair<String, Integer>> pif;

    public PIF() {
        pif = new ArrayList<>();
    }

    public void add(String token, Integer index) {
        pif.add(Pair.of(token, index));
    }

    public Integer getTokenIndex(String token) {
        for (Pair<String, Integer> pair : pif) {
            if (pair.getKey().equals(token)) {
                return pair.getValue();
            }
        }

        return null;
    }

    public Integer size() {
        return pif.size();
    }
}
