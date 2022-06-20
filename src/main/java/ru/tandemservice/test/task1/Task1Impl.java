package ru.tandemservice.test.task1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>Задание №1</h1>
 * Реализуйте интерфейс {@link IStringRowsListSorter}.
 *
 * <p>Мы будем обращать внимание в первую очередь на структуру кода и владение стандартными средствами java.</p>
 */
public class Task1Impl implements IStringRowsListSorter {

        // ваша реализация должна работать, как singleton. даже при использовании из нескольких потоков.
        public static final IStringRowsListSorter INSTANCE = new Task1Impl();


    /**
     * @param rows список записей таблицы (например, результат sql select), которые нужно отсортировать по указанной колонке
     * @param columnIndex индекс колонки, по которой нужно провести сортировку
     * @throws IllegalArgumentException При некорректности данных выбрасываем exception
     *
     */

    @Override
        public void sort(final List<String[]> rows, final int columnIndex) {
            if (rows == null || rows.isEmpty()) {
                throw new IllegalArgumentException("Parameter rows is null or empty");
            }
            if (columnIndex < 0 || columnIndex >= rows.get(0).length) {
                throw new IllegalArgumentException("Parameter columnIndex is not valid");
            }
            rows.sort(Comparator.comparing(row -> row[columnIndex], Comparator.nullsFirst(StringComparator)));
        }


        /**
         * Метод получает в качестве аргумента строку, а возвращает массив подстрок, разбивая ее на последовательности

         * @param input произвольная строка, которая при наличии в ней цифр будет разбита на подстроки.
         *
         * @return массив подстрок, полученных в результате разбиения.
         * Если строка не содержит цифр, то возвращается массив с 1 элементом.
         * Формируем 2 группы Отбора (только цифры и только буквы) и собираем из ним массив значений.
         */

        private String[] getSubStrings(String input){
            if(input.chars().allMatch(Character::isLetter))
            {
                return new String[]{input};
            }
            List<String> tempList = new ArrayList<>();
            Pattern pattern = Pattern.compile("(\\d+)([a-zA-Z]+)");
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                tempList.add(matcher.group(1));
                tempList.add(matcher.group(2));
            }
            return tempList.toArray(new String[tempList.size()]);
        }



    /**
     * Свой вариант компаратора - в лямбда-вырыжении переопределяем метод compare():
     * Получаем два массива подстрок:
     * Если они состоят из цифр сравниваем как целые числа иначе как строки
     */
    private final Comparator<String> StringComparator = (firstValue, secondValue) -> {

            String[] first = getSubStrings(firstValue);
            String[] second = getSubStrings(secondValue);
            for (int j = 0; j < first.length && j < second.length; j++) {
                if (first[j].matches("\\d+") && second[j].matches("\\d+")) {
                    if (Integer.parseInt(first[j]) > Integer.parseInt(second[j]))
                        return 1;
                    if (Integer.parseInt(first[j]) < Integer.parseInt(second[j]))
                        return -1;
                } else if (!first[j].equals(second[j]))
                    return first[j].compareTo(second[j]);
            }
            return firstValue.compareTo(secondValue);
        };
    }
