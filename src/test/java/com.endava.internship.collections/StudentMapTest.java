package com.endava.internship.collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentMapTest {

    public static List<Student> students;
    StudentMap map;

    @BeforeAll
    public static void initStudents() {
        students = new ArrayList<>();
        students.add(new Student("Ion", LocalDate.of(2002, 5, 4), "english"));
        students.add(new Student("Cristina", LocalDate.of(2002, 5, 5), "informatica"));
        students.add(new Student("Mircea", LocalDate.of(2003, 11, 23), "mathematica"));
        students.add(new Student("Vasile", LocalDate.of(2001, 1, 12), "romanian"));
        students.add(new Student("Anna", LocalDate.of(2003, 2, 2), "russian"));
        students.add(new Student("Diana", LocalDate.of(2004, 5, 23), "Arabian"));
    }

    @BeforeEach
    public void createMap() {
        map = new StudentMap();
    }

    @Test
    public void whenInsertFirstValue_methodPutOfMapReturnNull() {
        assertNull(map.put(students.get(0), 10));
        assertNull(map.put(null, null));
    }

    @Test
    public void whenInsertSecondValueEqualsByFirstValue_methodPutOfMapReturnFirstValue() {
        map.put(students.get(0), 10);
        map.put(null, null);

        assertEquals(10, map.put(students.get(0), 10 + 10));
        assertNull(map.put(null, 10));
    }
    @Test
    public void whenInsertValue_mapIsNotEmpty() {
        map.put(students.get(0), 10);

        assertFalse(map.isEmpty());
        assertEquals(1, map.size());
    }

    @Test
    public void whenValueIsInput_mapReturnValue() {
        StudentMap map = new StudentMap(2, 10f);
        map.put(students.get(0), 10);
        map.put(students.get(1), 20);
        map.put(students.get(2), 30);
        map.put(students.get(3), 40);
        map.put(students.get(4), 50);
        map.put(null, null);

        assertEquals(10, map.get(students.get(0)));
        assertEquals(20, map.get(students.get(1)));
        assertEquals(30, map.get(students.get(2)));
        assertEquals(40, map.get(students.get(3)));
        assertEquals(50, map.get(students.get(4)));
        assertNull(map.get(null));
        assertNull(map.get(students.get(5)));
    }

    @Test
    public void whenValueInput_keyAndValueExist() {
        map.put(students.get(0), 10);
        map.put(null, null);

        assertTrue(map.containsKey(students.get(0)));
        assertTrue(map.containsValue(10));
        assertTrue(map.containsKey(null));
        assertTrue(map.containsValue(null));
        assertFalse(map.containsKey(students.get(1)));
        assertFalse(map.containsValue(20));
    }

    @Test
    public void testFromMethodRemove() {
        StudentMap map = new StudentMap(2, 10f);
        map.put(students.get(0), 10);
        map.put(students.get(1), 20);
        map.put(students.get(2), 30);
        map.put(students.get(3), 40);
        map.put(students.get(4), 50);
        map.put(null, null);
        int size = map.size();

        assertEquals(10, map.remove(students.get(0)));
        assertEquals(20, map.remove(students.get(1)));
        assertEquals(30, map.remove(students.get(2)));
        assertEquals(40, map.remove(students.get(3)));
        assertEquals(50, map.remove(students.get(4)));
        assertNull(map.remove(null));
        assertNull(map.remove(students.get(5)));
        assertNull(map.get(students.get(0)));
        assertEquals(size - 6, map.size());
    }

    @Test
    public void testFromMethodClear() {
        map.put(students.get(0), 10);
        map.put(students.get(1), 20);
        map.put(students.get(2), 30);
        map.clear();

        assertNull(map.get(students.get(0)));
        assertNull(map.get(students.get(1)));
        assertNull(map.get(students.get(2)));
        assertTrue(map.isEmpty());
    }
    @Test
    public void whenInsertOtherMapInMap_valuesOfFirstMapWrittenInMajorMap() {
        StudentMap otherMap = new StudentMap();
        otherMap.put(students.get(0), 10);
        otherMap.put(students.get(1), 20);
        otherMap.put(students.get(2), 30);
        otherMap.put(null, null);

        int size = map.size();
        map.putAll(otherMap);

        assertEquals(10, map.get(students.get(0)));
        assertEquals(20, map.get(students.get(1)));
        assertEquals(30, map.get(students.get(2)));
        assertNull(map.get(null));
        assertEquals(size + 4, map.size());
    }

    @Test
    public void whenUsedEntrySet_trowsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> map.entrySet());
    }

    @Test
    public void whenCreateCollectionOfValues_itNotEmpty() {
        map.put(students.get(0), 10);
        map.put(students.get(1), 20);
        map.put(students.get(2), 30);
        Collection<Integer> values = map.values();

        assertFalse(values.isEmpty());
        assertEquals(values.size(), map.size());
    }

    @Test
    public void whenCreateCollectionOfValues_iteratorWorked() {
        map.put(students.get(0), 10);
        map.put(students.get(1), 20);
        map.put(null, null);
        Collection<Integer> values = map.values();

        for(Integer val : values) {
            assertTrue(map.containsValue(val));
        }
    }

    @Test
    public void whenCreateSetOfKeys_isNotEmpty() {
        map.put(students.get(0), 10);
        map.put(students.get(1), 20);
        map.put(null, null);
        map.put(students.get(2), 30);
        Set<Student> keys = map.keySet();

        assertFalse(keys.isEmpty());
        assertEquals(keys.size(), map.size());
    }

    @Test
    public void whenCreateSetOfKeys_iteratorWorked() {
        map.put(students.get(0), 10);
        map.put(students.get(1), 20);
        map.put(null, null);
        map.put(students.get(2), 30);
        Set<Student> keys = map.keySet();

        for(Student key : keys) {
            assertTrue(map.containsKey(key));
        }
    }

    @Test
    public void testResize() {
        PrintStream out;
        out = System.out;

        ByteArrayOutputStream log = new ByteArrayOutputStream();
        System.setOut(new PrintStream(log));

        int capacity = 1;
        float load = 0.5f;

        StudentMap map = new StudentMap(capacity, load);
        map.put(students.get(0), 10);
        map.put(students.get(1), 20);
        map.put(students.get(2), 20);
        map.put(students.get(3), 20);
        map.put(students.get(4), 20);

        int countOfResize = countResize(map.size(), 1, 0.5f);

        System.setOut(out);
        String str = log.toString();
        int countOfResizeMap = str.split("\n").length;

        assertEquals(countOfResize, countOfResizeMap);
    }
    public int countResize(int countStudents, int capacity, float load) {
        int countOfResize = 0;
        while(countStudents - 1 > capacity * load) {
            ++countOfResize;
            capacity += capacity;
        }
        return countOfResize;
    }
}
