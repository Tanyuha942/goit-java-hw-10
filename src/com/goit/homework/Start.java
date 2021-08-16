package com.goit.homework;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Start {

    public static void main(String[] args) {

        MyStream myStream = new MyStream();
    /*
      Задание 1#
      Метод принимает на вход список имен.
      Вернуть строку в виде: "1. Ivan, 3. Peter ...", с именами из списка, стоящими под нечетным индексом (1, 3 и т.д.).
    */
        List<String> names = Arrays.asList("John", "Bill", "Peter", "Vasiliy", "Mariya", "William", "Laura", "Jack");
        System.out.println("Задание#1 - " + myStream.getListStringWithOddIterator(names));
    /*
      Задание 2#
      Метод принимает на вход список из строк (можно список из Задания 1).
       Возвращает список этих строк в верхнем регистре и отсортированные по убыванию (от Z до А).
    */
        System.out.println("Задание#2 - " + myStream.getReverseStringList(names));
    /*
      Задание 3#
      Дан массив: ["1, 2, 0", "4, 5"]
    */
        String[] str = {"1, 2, 0", "4, 5"};
        System.out.println("Задание#3 - " + myStream.getArrayNumbersToString(str));

    /*
      Задание 4#
      Используя Stream.iterate сделайте бесконечный стрим рандомных чисел, но не используя Math.random.
      Реализуйте свой "линейный конгруэнтный генератор".
      Для этого начните с x[0] = seed и затем каждый следующий элемент x[n + 1] = 1 (a x[n] + c) % m,
      для корректных значений a, c, и m. Необходимо имплементировать метод, который принимает на вход параметры
       a, c, m и seed и возвращает Stream<Long>. Для теста используйте данные a = 25214903917, c = 11 и m = 2^48 (2 в степени 48).
       m = 281474976710656
    */
//      Realise for limit = 50, seed = 1, a = 25214903917, c = 11, m = 2^48 = 281474976710656.
        Stream<Long> resultLong = myStream.iterator(1L, 25214903917L, 11L, 281474976710656L);
        System.out.println("Задание#4 - " + resultLong.collect(Collectors.toList()));

    /*
      Задание 5#
      Напишите метод public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) который "перемешивает" элементы из стримов first и second,
      останавливается тогда, когда у одного из стримов закончатся элементы.
    */
        zip(Stream.of(1, 2, 3, 4), Stream.of("AA", "BB", "CC", "DD"));
        System.out.println("Задание#5 - " + zip(Stream.of(1, 2, 3, 4), Stream.of("AA", "BB", "CC", "DD")).toList());
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Spliterator<T> splLeft = first.spliterator();
        Spliterator<T> splRight = second.spliterator();

        T[] single = (T[]) new Object[1];

        Stream.Builder<T> builder = Stream.builder();
        while (splRight.tryAdvance(x -> single[0] = x) && splLeft.tryAdvance(builder)) {
            builder.add(single[0]);
        }
        return builder.build();
    }
}