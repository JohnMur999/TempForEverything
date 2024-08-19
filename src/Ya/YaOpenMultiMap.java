package Ya;

import java.util.*;
import java.util.stream.Collectors;

public class YaOpenMultiMap {

    public static Optional<Map<Long, Integer>> OpenMultiMap (Map<Integer, List<Long>> someMap) {
        Map<Long, Integer> newMap = someMap.entrySet().stream()
                .flatMap(entry -> {
                            var key = entry.getKey();
                            var value = entry.getValue();
                            return value.stream().map(num -> Map.entry(num, key));
                        })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return Optional.of(newMap);
    }

    public static void main(String[] args) {
        // 1-> [0,1,2]
        // 2-> [3,4]

        Map<Integer, List<Long>> inputMap = new HashMap<>();
        inputMap.put(1, Arrays.asList(0L, 1L, 2L));
        inputMap.put(2, Arrays.asList(3L, 4L));


        Map<Long,Integer> openedMap = OpenMultiMap(inputMap).get();
        System.out.println(openedMap);
    }
}