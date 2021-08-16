package com.goit.homework;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class MyStream {

  public String getListStringWithOddIterator(List<String> nameList) {

    List<Integer> id = IntStream.iterate(1, i -> i+2)
        .limit(nameList.size())
        .boxed().collect(Collectors.toList());

    List<String> name = nameList.stream()
        .map(m -> id.get(nameList.indexOf(m)) + ". " + m)
        .collect(Collectors.toList());

    return name.stream()
          .map(Object::toString)
          .collect(Collectors.joining(", "));
  }

  public List<String> getReverseStringList(List<String> names) {
    return names.stream()
          .sorted(Comparator.reverseOrder())
          .map(String::toUpperCase)
          .collect(Collectors.toList());
  }

  public String getArrayNumbersToString(String[] strArray) {

    List<String> s = Arrays.stream(strArray)
        .flatMap(a -> Arrays.stream(a.split(", ")))
        .collect(Collectors.toList());

    return s.stream()
        .map(Integer::parseInt)
        .sorted()
        .map(Object::toString)
        .collect(Collectors.joining(", "));
  }

  public Stream<Long> iterator(Long seed, Long a, Long c, Long m) {

    return Stream.iterate(seed, n -> (n * a + c) % m)
            .limit(50);
  }
}