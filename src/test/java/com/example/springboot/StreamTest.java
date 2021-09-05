package com.example.springboot;

import com.example.springboot.model.Dish;
import com.example.springboot.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamTest {

    StringBuilder sb = new StringBuilder("");
    List<Person> people = new ArrayList<>();
    List<Dish> dishes = new ArrayList<>();

    @BeforeEach
    void init() {
        sb.append("Bom dia meus caros\n");
        sb.append("Bem vindos ao mundo do java\n");

        people.add(Person.builder().name("Joao Fernandes").age(46).build());
        people.add(Person.builder().name("Adriana Percontini").age(46).build());
        people.add(Person.builder().name("Ana Fernandes").age(55).build());
        people.add(Person.builder().name("Célia Fernandes").age(51).build());

        dishes.add(Dish.builder().name("Hamburguer com batata frita").calories(700).type("meat").build());
        dishes.add(Dish.builder().name("Peixe grelhado").calories(300).type("fish").build());
        dishes.add(Dish.builder().name("Wraps de frango").calories(400).type("meat").build());
    }

    @Test
    public void testLinesFeature() {
        assertEquals(2, sb.toString().lines().count());
    }

    @Test
    public void testStreams() {
        List<String> output = sb.toString().lines()
                .filter(line -> line.contains("dia"))
                .map(line -> line.toUpperCase())
                .collect(Collectors.toList());

        assertEquals(1, output.size());
        assertEquals("BOM DIA MEUS CAROS", output.get(0));
    }

    @Test
    public void getMaxListIntegers() {
        List<Integer> values = Arrays.asList(1, 50, 36, 10);

        OptionalInt output = values.stream()
                .mapToInt(v -> v)
                .max();

        assertEquals(50, output.getAsInt());
    }

    @Test
    public void findPersonMaximumAge() {
        Optional<Person> output = people.stream()
                .max(Comparator.comparing(Person::getAge));

        assertEquals("Ana Fernandes", output.get().getName());
    }

    @Test
    public void groupingByList() {
        // Group list by age
        Map<Integer, List<Person>> map = people.stream()
                .collect(Collectors.groupingBy(Person::getAge));

        // Filter the list by number of person that has the same age equals to 2
        Map<Integer, List<Person>> filtered = map.entrySet().stream()
                .filter(x -> 2 == x.getValue().size())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        assertEquals(2, filtered.entrySet().stream().findFirst().get().getValue().size());
    }

    @Test
    public void filterDishes() {

        // Get the name of the dishes, that has less than 400 calories, sorted by calories
        // lambda expression d->d.getCalories() < 0
        // method reference Dish::getName
        List<String> dishNames = dishes.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());

        assertEquals(1, dishNames.size());
        assertEquals("Peixe grelhado", dishNames.get(0));
    }

    @Test
    public void groupDishesByType() {

        // uses method reference Dish::getType
        Map<String, List<Dish>> dishesByType = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType));

        dishesByType.entrySet().stream().forEach(System.out::println);
    }
}
