package org.example;


import lombok.SneakyThrows;

import java.io.*;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

public class HomeWork {

    private static final char LEFT = 'L';

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу https://codeforces.com/problemset/problem/1324/C
     */
    @SneakyThrows
    public void frogSteps(InputStream in, OutputStream out) {
        try (var reader = new BufferedReader(new InputStreamReader(in));
             var writer = new BufferedWriter(new OutputStreamWriter(out))) {
            var queue = reader.lines().collect(Collectors.toCollection(LinkedList::new));
            var countSteps = Integer.parseInt(Objects.requireNonNull(queue.poll()));//Извлекаем что бы не мешал
            while (!queue.isEmpty()) writer.write(minimumPossibleValue(queue.poll()) + "\n");
            writer.flush();
        }
    }

    private int minimumPossibleValue(String line) {
        int max = 0;
        boolean hasLeft = line.contains(String.valueOf(LEFT));
        int count = hasLeft ? 1 : 0;
        var directions = line.toCharArray();
        for (int i = 1; i < directions.length; i++) {
            if (directions[i] == LEFT) {
                if (directions[i - 1] == directions[i]) {
                    count++;
                    continue;
                }
                max = Math.max(max, count);
                count = hasLeft ? 1 : 0;
            }
        }
        return Math.max(max, count) + 1;
    }
}
