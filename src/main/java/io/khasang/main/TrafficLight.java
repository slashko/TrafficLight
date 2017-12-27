package io.khasang.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class TrafficLight {
    private final int MIN = 60000; //Минуты
    private LinkedHashMap<String, Integer> timeColor = new LinkedHashMap<>(3, 1.1f);

    //Можно создать пустой конструктор и вызывать настройки из Main
    public TrafficLight() {
        settingTrafficLight();
    }

    public TrafficLight(LinkedHashMap<String, Integer> timeColor) {
        this.timeColor = timeColor;
        start();
    }

    private void settingTrafficLight() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        timeColor.put("зеленый", 0);
        timeColor.put("желтый", 0);
        timeColor.put("красный", 0);

        Set<Map.Entry<String, Integer>> set = timeColor.entrySet();
        for (Map.Entry<String, Integer> o : set) {
            while (true) {
                try {
                    System.out.println("Сколько минут горит " + o.getKey() + "?");
                    timeColor.put(o.getKey(), Integer.parseInt(reader.readLine()) * MIN);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Это не число");
                } catch (IOException e1) {
                    System.out.println("Ошибка ввода/ввывода!");
                    System.exit(0);
                } finally {
                    try {
                        reader.close();
                    } catch (IOException e2) {
                        System.out.println("Ошибка закрытия потока");
                    }
                }
            }
        }
        start();
    }

    private void start() {
        Set<Map.Entry<String, Integer>> set = timeColor.entrySet();
        for (Map.Entry<String, Integer> o : set) {
            try {
                System.out.println("Загорелся " + o.getKey());
                Thread.sleep(o.getValue());
            } catch (InterruptedException e) {
                System.out.println("Ошибка в работе светофора");
            }
        }
    }
}