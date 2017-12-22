package com.flipkart.zjsonpatch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InternalUtils {

    static List<JsonNode> toList(ArrayNode input) {
        List<JsonNode> toReturn = new ArrayList<JsonNode>();
        int size = input.size();
        for (int i = 0; i < size; i++) {
            toReturn.add(input.get(i));
        }
        return toReturn;
    }

    private static List<JsonNode> reverse(List<JsonNode> list) {
        List<JsonNode> toReturn = new LinkedList<JsonNode>();
        for (int i = list.size() - 1; i >= 0; i--) {
            toReturn.add(list.get(i));
        }
        return toReturn;
    }

    static List<JsonNode> longestCommonSubsequence(final List<JsonNode> a, final List<JsonNode> b) {
        if (a == null || b == null) {
            throw new NullPointerException("List must not be null for longestCommonSubsequence");
        }

        List<JsonNode> toReturn = new LinkedList<JsonNode>();

        int aSize = a.size();
        int bSize = b.size();
        int temp[][] = new int[aSize + 1][bSize + 1];

        for (int i = 1; i <= aSize; i++) {
            for (int j = 1; j <= bSize; j++) {
                if (i == 0 || j == 0) {
                    temp[i][j] = 0;
                } else if (a.get(i - 1).equals(b.get(j - 1))) {
                    temp[i][j] = temp[i - 1][j - 1] + 1;
                } else {
                    temp[i][j] = Math.max(temp[i][j - 1], temp[i - 1][j]);
                }
            }
        }
        int i = aSize, j = bSize;
        while (i > 0 && j > 0) {
            if (a.get(i - 1).equals(b.get(j - 1))) {
                toReturn.add(a.get(i - 1));
                i--;
                j--;
            } else if (temp[i - 1][j] > temp[i][j - 1])
                i--;
            else
                j--;
        }
        return reverse(toReturn);
    }
}
