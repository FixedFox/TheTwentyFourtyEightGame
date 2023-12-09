package ru.fixedfox.thetwentyfourtyeightgame;

import java.util.ArrayList;
import java.util.List;

public class GameHelper {
    public List<Integer> moveAndMergeEqual(List<Integer> list) {
        var movedAndMergedItems = new ArrayList<>(list);
        var listWithoutNull = leftShifter(movedAndMergedItems);
        for (int j = 0; j < listWithoutNull.size() - 1; j++) {

            var left = listWithoutNull.get(j);
            var right = listWithoutNull.get(j + 1);

            if (left == null) {
                listWithoutNull.set(j, right);
                listWithoutNull.set(j + 1, null);
                listWithoutNull = leftShifter(listWithoutNull);
                continue;
            }
            if (right == null) {
                continue;
            }

            if (left.equals(right)) {
                listWithoutNull.set(j, left * 2);
                listWithoutNull.set(j + 1, null);
                listWithoutNull = leftShifter(listWithoutNull);
            }
        }
        return listWithoutNull;
    }

    private List<Integer> leftShifter(List<Integer> list) {
        var shiftedList = new ArrayList<>(list);
        for (int i = 0; i < shiftedList.size() - 1; i++) {
            var left = shiftedList.get(i);
            var right = shiftedList.get(i + 1);

            if (left == null) {
                int j = i + 1;
                while (j < (shiftedList.size())) {
                    right = shiftedList.get(j);
                    if (right != null) {
                        shiftedList.set(i, right);
                        shiftedList.set(j, null);
                        break;
                    }
                    j++;
                }
            }
        }
        return shiftedList;
    }
}

