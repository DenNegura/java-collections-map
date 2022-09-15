package com.endava.internship.collections;


import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class StudentMap implements Map<Student, Integer> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final int MAXIMUM_CAPACITY = 1_000_000;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node[] table;

    private int size;

    private final int capacity;

    private final float loadFactor;

    final class Node {
        final int hash;
        final Student key;
        Integer value;
        Node next;

        Node(int hash, Student key, Integer value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
        public Student getKey() {
            return key;
        }
        public Integer getVal() {
            return value;
        }
        public int getHash() {
            return hash;
        }
        public Node getNext() {
            return next;
        }
        public void setVal(Integer value) {
            this.value = value;
        }
        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Node) {
                Node another = ((Node) obj);
                if(this.getKey() == null && another.getKey() == null) return true;
                if(this.getKey() != null) {
                    return this.getKey().equals(another.getKey());
                }
            }
            return false;
        }
        @Override
        public int hashCode() {
            return this.getHash();
        }
    }
    StudentMap(int initCapacity, float loadFactor)  {
        if(initCapacity < 0) {
            throw new IllegalArgumentException(
                    "Illegal initial capacity : " + initCapacity);
        }
        if(initCapacity > MAXIMUM_CAPACITY) {
            initCapacity = MAXIMUM_CAPACITY;
        }
        if(loadFactor <= 0) {
            throw new IllegalArgumentException(
                    "Illegal load factor " + loadFactor);
        }
        this.capacity = initCapacity;
        this.loadFactor = loadFactor;
    }
    StudentMap(int initCapacity) {
        this(initCapacity, DEFAULT_LOAD_FACTOR);
    }
    StudentMap() {
        this.capacity = DEFAULT_CAPACITY;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public static int hash(Student key) {
        return (key == null) ? 0 : key.hashCode();
    }

    private void resize() {
        System.out.println("resize");
        Node[] oldTable = table;
        size = 0;
        int n = Math.min(oldTable.length + oldTable.length, MAXIMUM_CAPACITY);
        table = new Node[n];
        for(int i = 0; i < oldTable.length; i++) {
            if(oldTable[i] != null) {
                while(oldTable[i].getNext() != null) {
                    Node lastNode = oldTable[i];
                    while(lastNode.getNext().getNext() != null) {
                        lastNode = lastNode.getNext();
                    }
                    putNode(lastNode.getNext());
                    lastNode.setNext(null);
                }
                putNode(oldTable[i]);
                oldTable[i] = null;
            }
        }
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object o) {
        if(o instanceof Student || o == null) {
            Node node = getNode(new Node(hash(((Student) o)), ((Student) o), null, null));
            return node != null;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        if(table != null && size > 0 &&
                (o instanceof Integer || o == null)) {
            for(int i = 0; i < table.length; i++) {
                for(Node node = table[i]; node != null; node = node.getNext()) {
                    if(node.getVal() == o || node.getVal().equals(o)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Integer get(Object o) {
        if(o instanceof Student || o == null) {
            Node node = getNode(new Node(hash(((Student) o)), ((Student) o), null, null));
            return node == null ? null : node.getVal();
        }
        return null;
    }
    private Node getNode(Node node) {
        if(table != null && size > 0) {
            Node listNode = node.getKey() == null ? table[0] :
                    table[(table.length - 1) & node.getHash()];
            if(listNode != null) {
                while(true) {
                    if(listNode.equals(node)) {
                        node.setVal(listNode.getVal());
                        return node;
                    }
                    else {
                        if(listNode.getNext() == null) {
                            break;
                        }
                        else {
                            listNode = listNode.getNext();
                        }
                    }
                }
            }
        }
        return null;
    }
    @Override
    public Integer put(Student student, Integer integer) {
        return putNode(new Node(hash(student), student, integer, null));
    }

    private Integer putNode(Node node) {
        if(table == null) {
            table = new Node[capacity];
        }
        if(size > table.length * loadFactor) {
            resize();
        }
        int i = node.getKey() == null ? 0 : (table.length - 1) & node.getHash();
        if(table[i] == null) {
            table[i] = node;
            size++;
        }
        else {
            // Идти по ссылкам
            Node listNode = table[i];
            while(true) {
                if(listNode.equals(node)) {
                    listNode.setVal(node.getVal());
                    break;
                }
                else {
                    if(listNode.getNext() == null) {
                        listNode.setNext(node);
                        size++;
                    }
                    else {
                        listNode = listNode.getNext();
                    }
                }
            }
        }
        return node.getVal();
    }
    @Override
    public Integer remove(Object o) {
        if(table != null && size > 0 && (o instanceof Student || o == null)) {
            Node node = new Node(hash(((Student) o)), ((Student) o), null, null);
            int i = node.getKey() == null ? 0 : (table.length - 1) & node.getHash();
            Node listNode = table[i];
            if(!listNode.equals(node)) {
                while(listNode.getNext() != null) {
                    if(listNode.getNext().equals(node)) {
                        Node removeNode = listNode.getNext();
                        node.setNext(listNode.getNext().getNext());
                        listNode.setNext(listNode.getNext().getNext());
                        size--;
                        return removeNode.getVal();
                    }
                    listNode = listNode.getNext();
                }
            }
            else {
                table[i] = listNode.getNext();
                size--;
                return listNode.getVal();
            }
        }
        return null;
    }
    @Override
    public void putAll(Map<? extends Student, ? extends Integer> map) {
        Iterator<? extends Integer> value = map.values().iterator();
        Iterator<? extends Student> key = map.keySet().iterator();
        while(key.hasNext()) {
            this.put(key.next(), value.next());
        }
    }

    @Override
    public void clear() {
        if(table != null && size > 0) {
            size = 0;
            for(int i = 0; i < table.length; i++) {
                table[i] = null;
            }
        }
    }

    @Override
    public Set<Student> keySet() {
        return new KeySet();
    }
    final class KeySet extends AbstractSet<Student> {
        @Override
        public Iterator<Student> iterator() {
            return new SetIterator();
        }

        @Override
        public int size() {
            return size;
        }
        @Override
        public void forEach(Consumer<? super Student> action) {
            if(action == null) {
                throw new NullPointerException();
            }
            if(table != null && size > 0) {
                for(int i = 0; i < table.length; i++) {
                    for(Node node = table[i]; node != null; node = node.getNext()) {
                        action.accept(node.getKey());
                    }
                }
            }
        }
    }
    final class SetIterator implements Iterator<Student> {
        Node current;
        Node next;
        int index;
        SetIterator() {
            current = next = null;
            for(index = 0; index < table.length; index++) {
                if(table[index] != null) {
                    next = table[index];
                    break;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Student next() {
            current = next;
            nextNode();
            return current.getKey();
        }
        public void nextNode() {
            if(next.getNext() == null) {
                for(index++; index < table.length; index++) {
                    if(table[index] != null) {
                        next = table[index];
                        return;
                    }
                }
            }
            else {
                next = next.getNext();
                return;
            }
            next = null;
        }
    }
    @Override
    public Collection<Integer> values() {
        return new Values();
    }

    final class Values extends AbstractCollection<Integer> {
        @Override
        public Iterator<Integer> iterator() {
            return new ValueIterator();
        }
        @Override
        public int size() {
            return size;
        }
        @Override
        public void forEach(Consumer<? super Integer> action) {
            if(action == null) {
                throw new NullPointerException();
            }
            if(table != null && size > 0) {
                for(int i = 0; i < table.length; i++) {
                    for(Node node = table[i]; node != null; node = node.getNext()) {
                        action.accept(node.getVal());
                    }
                }
            }
        }
    }

    final class ValueIterator implements Iterator<Integer> {

        Node current;
        Node next;
        int index;
        ValueIterator() {
            current = next = null;
            for(index = 0; index < table.length; index++) {
                if(table[index] != null) {
                    next = table[index];
                    break;
                }
            }
        }
        @Override
        public boolean hasNext() {
            return next != null;
        }
        @Override
        public Integer next() {
            current = next;
            nextNode();
            return current.getVal();
        }
        public void nextNode() {
            if(next.getNext() == null) {
                for(index++; index < table.length; index++) {
                    if(table[index] != null) {
                        next = table[index];
                        return;
                    }
                }
            }
            else {
                next = next.getNext();
                return;
            }
            next = null;
        }
    }

    @Override
    public Set<Entry<Student, Integer>> entrySet() {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }
}

