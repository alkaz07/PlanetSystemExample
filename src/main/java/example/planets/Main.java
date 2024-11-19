package example.planets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
       // example1();
       // example2();
        example3();
    }

    private static void example3() {
        List<Planet> planets = Arrays.asList(
                new Planet("Меркурий", 3.302e23, List.of()),
                new Planet("Венера", 4.867e24, List.of()),
                new Planet("Земля", 5.972e24, List.of(
                        new Satellite("Луна", 7.348e22, "серый")
                )),
                new Planet("Марс", 6.417e23, List.of(
                        new Satellite("Фобос", 1.072e16, "красный"),
                        new Satellite("Деймос", 1.476e15, "красный")
                )),
                new Planet("Юпитер", 1.899e27, List.of(
                        new Satellite("Ио", 8.94e22, "желтый"),
                        new Satellite("Европа", 4.8e22, "белый"),
                        new Satellite("Ганимед", 1.482e23, "белый"),
                        new Satellite("Каллисто", 1.076e23, "коричневый")
                )),
                new Planet("Сатурн", 5.685e26, List.of(
                        new Satellite("Титан", 1.345e23, "оранжевый"),
                        new Satellite("Рея", 2.307e21, "серый"),
                        new Satellite("Япет", 1.806e21, "черно-белый")
                )),
                new Planet("Уран", 8.683e25, List.of(
                        new Satellite("Титания", 3.53e21, "серый"),
                        new Satellite("Оберон", 3.02e21, "серый"),
                        new Satellite("Умбриэль", 1.17e21, "серый"),
                        new Satellite("Ариэль", 1.35e21, "серый")
                )),
                new Planet("Нептун", 1.024e26, List.of(
                        new Satellite("Тритон", 2.14e22, "розовый"),
                        new Satellite("Протей", 5.0e21, "серый")
                ))
        );
        System.out.println("Вывод названий планет в параллельном режиме");
        //planets.parallelStream().forEach(x-> System.out.println(x.getName()));
        planets.parallelStream().peek(x-> System.out.println("параллельно посещаем "+x.getName()))
                                .forEachOrdered(x-> System.out.println("терминальное посещение:"+ x.getName()));
        System.out.println("---------------------------");
        planets.stream().sorted(Comparator.comparingDouble(Planet::getMass).reversed())
                .parallel().peek(x-> System.out.println("параллельно посещаем "+x.getName()))
                .forEachOrdered(x-> System.out.println("терминальное посещение:"+ x.getName()));

        planets.stream().parallel().sorted(Comparator.comparing(Planet::getName))
                .forEach(x-> System.out.println(" "+x.getName()));
        System.out.println("---------------------------");
        Planet[] arr = planets.toArray(new Planet[0]);
        Arrays.parallelSort(arr, Comparator.comparing(Planet::getName));
        Arrays.stream(arr).forEach(System.out::println);
    }

    private static void example2() {
        List<Planet> planets = Arrays.asList(
                new Planet("Меркурий", 3.302e23, List.of()),
                new Planet("Венера", 4.867e24, List.of()),
                new Planet("Земля", 5.972e24, List.of(
                        new Satellite("Луна", 7.348e22, "серый")
                )),
                new Planet("Марс", 6.417e23, List.of(
                        new Satellite("Фобос", 1.072e16, "красный"),
                        new Satellite("Деймос", 1.476e15, "красный")
                )),
                new Planet("Юпитер", 1.899e27, List.of(
                        new Satellite("Ио", 8.94e22, "желтый"),
                        new Satellite("Европа", 4.8e22, "белый"),
                        new Satellite("Ганимед", 1.482e23, "белый"),
                        new Satellite("Каллисто", 1.076e23, "коричневый")
                )),
                new Planet("Сатурн", 5.685e26, List.of(
                        new Satellite("Титан", 1.345e23, "оранжевый"),
                        new Satellite("Рея", 2.307e21, "серый"),
                        new Satellite("Япет", 1.806e21, "черно-белый")
                )),
                new Planet("Уран", 8.683e25, List.of(
                        new Satellite("Титания", 3.53e21, "серый"),
                        new Satellite("Оберон", 3.02e21, "серый"),
                        new Satellite("Умбриэль", 1.17e21, "серый"),
                        new Satellite("Ариэль", 1.35e21, "серый")
                )),
                new Planet("Нептун", 1.024e26, List.of(
                        new Satellite("Тритон", 2.14e22, "розовый"),
                        new Satellite("Протей", 5.0e21, "серый")
                ))
        );
        System.out.println("Список планет по убыванию массы:");
        planets.stream().sorted(Comparator.comparingDouble(Planet::getMass).reversed())
                .forEach(x-> System.out.println(x.getName()));

        System.out.println("\nПланеты по возрастанию суммарной массы спутников:");
        planets.stream()
                .sorted(Comparator.comparingDouble(p -> p.getSatellites().stream().mapToDouble(Satellite::getMass).sum()))
                .map(Planet::getName)
                .forEach(System.out::println);

        System.out.println("Извлечь все спутники и отсортировать по массе:");
        //planets.stream().map(x->x.getSatellites()).forEach(System.out::println);
        planets.stream().flatMap(x -> x.getSatellites().stream())
                        .sorted(Comparator.comparingDouble(Satellite::getMass))
                        .forEach(System.out::println);
    }

    private static void example1() {
        List<Planet> planetList = new ArrayList<>();
        planetList.add(new Planet("Земля", 100000));
        planetList.add(new Planet("Венера", 90000));
        planetList.add(new Planet("Марс", 70000));
        planetList.add(new Planet("Юпитер", 3900000));
        planetList.add(new Planet("Сатурн", 270000));
        System.out.println("planetList = " + planetList);

        System.out.println("Список планет:");
        planetList.stream().forEach(System.out::println);
        System.out.println("Список планет по убыванию массы:");
        planetList.stream().sorted(Comparator.comparingDouble(Planet::getMass).reversed())
                            .forEach(x-> System.out.println(x.getName()));

    }
}