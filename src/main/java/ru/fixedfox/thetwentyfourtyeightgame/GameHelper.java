package ru.fixedfox.thetwentyfourtyeightgame;

import java.util.List;

public class GameHelper {
    public List<Integer> moveAndMergeEqual(List<Integer> list) {
        shiftLeft(list);

        for (int j = 0; j < list.size() - 1; j++) {

            var first = list.get(j);
            var second = list.get(j + 1);

            if (first == null) {
                list.set(j, second);
                list.set(j + 1, null);
                shiftLeft(list);
                continue;
            }
            if (second == null) {
                continue;
            }

            if (first.equals(second)) {
                list.set(j, first * 2);
                list.set(j + 1, null);
                shiftLeft(list);
            }
        }
        return list;
    }

    private void shiftLeft(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            var left = list.get(i);
            var right = list.get(i + 1);

            if (left == null) {
                int j = i + 1;
                while (j < (list.size())) {
                    right = list.get(j);
                    if (right != null) {
                        list.set(i, right);
                        list.set(j, null);
                        break;
                    }
                    j++;
                }
            }
        }
    }
}

