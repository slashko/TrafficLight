package io.khasang.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class TrafficLight {
    private final int MIN = 60000; //Минуты
    private LinkedHashMap<String, Integer> time = new LinkedHashMap<>(3, 1.1f);

    public TrafficLight() {
        settingTrafficLight();
    }

    public TrafficLight(LinkedHashMap<String, Integer> time) {
        this.time = time;
        start();
    }

    private void settingTrafficLight() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        time.put("зеленый", 0);
        time.put("желтый", 0);
        time.put("красный", 0);

        Set<Map.Entry<String, Integer>> set = time.entrySet();
        for (Map.Entry<String, Integer> o : set) {
            while (true) {
                try {
                    System.out.println("Сколько минут горит " + o.getKey() + "?");
                    time.put(o.getKey(), Integer.parseInt(reader.readLine()) * MIN);
                    break;
                } catch (NumberFormatException | IOException e1) {
                    System.out.println("Это не число");
                }
            }
        }
        start();
    }

    private void start() {
        Set<Map.Entry<String, Integer>> set = time.entrySet();
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