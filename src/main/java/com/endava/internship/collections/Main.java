package com.endava.internship.collections;

import java.time.LocalDate;

public class Main {
    public static void main(String []args) {
        Student std1 = new Student("Ion", LocalDate.of(2002, 5, 4), "english");
        Student std2 = new Student("Cristina", LocalDate.of(2002, 5, 5), "informatica");
        Student std3 = new Student("Mircea", LocalDate.of(2003, 11, 23), "mathematica");
        Student std4 = new Student("Vasile", LocalDate.of(2001, 1, 12), "romanian");
        Student std5 = new Student("Anna", LocalDate.of(2003, 2, 2), "russian");


        StudentMap map1 = new StudentMap(10, 0.8f);
        putMap(map1, std1, 11);
        putMap(map1, std2, 22);
        putMap(map1, null, null);
        putMap(map1, std3, 33);



        System.out.println();

        showKeysMap(map1);
        showValMap(map1);

        System.out.println();

        getMap(map1, std2);

        System.out.println();

        removeMap(map1, std2);

        System.out.println();

        showKeysMap(map1);
        showValMap(map1);

        System.out.println();

        keyInMap(map1, std1);

        System.out.println();

        valInMap(map1, null);

        System.out.println();

        StudentMap map2 = new StudentMap();

        putMap(map2, std1, 1111);
        putMap(map2, std4, 2222);
        putMap(map2, std5, 4444);

        mapPutMap(map1, map2);

        System.out.println();

        showKeysMap(map1);
        showValMap(map1);

        System.out.println();

        showKeysMap(map2);
        showValMap(map2);

        System.out.println();


    }
    public static void putMap(StudentMap map, Student std, Integer val) {
        System.out.println("[putMap] from map " + map.hashCode() + ". return value : " + map.put(std, val));
    }
    public static void getMap(StudentMap map, Student std) {
        System.out.println("[getMap] from map " + map.hashCode() + ". get value : " + map.get(std));
    }
    public static void removeMap(StudentMap map, Student std) {
        System.out.println("[removeMap] remove from map " + map.hashCode() + ". value : " + map.remove(std));
    }
    public static void keyInMap(StudentMap map, Student std) {
        System.out.println("[keyInMap] from map " + map.hashCode() + ".  key : " + std + " contains : " + map.containsKey(std));
    }
    public static void valInMap(StudentMap map, Integer val) {
        System.out.println("[valInMap] from map " + map.hashCode() + ". val : " + val + " contains : " + map.containsValue(val));
    }
    public static void showKeysMap(StudentMap map) {
        for(Student std : map.keySet()) {
            System.out.println("[showKeysMap] from map " + map.hashCode() + ". key : " + std);
        }
    }
    public static void showValMap(StudentMap map) {
        for(Integer val : map.values()) {
            System.out.println("[showValMap] from map " + map.hashCode() + ". values : " + val);
        }
    }
    public static void mapPutMap(StudentMap mapIn, StudentMap mapOut) {
        System.out.println("[mapPutMap] intro in map " + mapIn.hashCode() + ", from map " + mapOut.hashCode());
        mapIn.putAll(mapOut);
    }
}
