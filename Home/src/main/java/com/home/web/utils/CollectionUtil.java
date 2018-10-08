package com.home.web.utils;

/**
 * @Author: liuhao
 * @Date: 2018/9/12 11:05
 * @Description:
 **/

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CollectionUtil {
    public static <E> Set<E> createListedSet() {
        return new ListedSet<E>();
    }

    public static <T> boolean isEmpty(final Iterable<T> iterable) {
        if (iterable == null) {
            return true;
        }
        Iterator<T> iterator = iterable.iterator();
        return !iterator.hasNext();
    }

    public static boolean isNotEmpty(final Iterable<?> iterable) {
        return !isEmpty(iterable);
    }

    public static <E> List<E> toList(final Enumeration<E> enumeration) {
        if (enumeration == null) {
            return Collections.emptyList();
        }
        List<E> list = Lists.newLinkedList();
        while (enumeration.hasMoreElements()) {
            list.add(enumeration.nextElement());
        }
        return Collections.unmodifiableList(list);
    }

    public static Map<String, String> toMap(final Properties properties) {
        if (properties == null || properties.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, String> map = Maps.newHashMap();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            map.put(key, value);
        }
        return Collections.unmodifiableMap(map);
    }

    public static class ListedSet<E> implements List<E>, Set<E> {
        private List<E> delegate = new LinkedList<E>();

        @Override
        public Iterator<E> iterator() {
            return this.delegate.iterator();
        }

        @Override
        public int size() {
            return this.delegate.size();
        }

        @Override
        public void add(final int index, final E element) {
            if (element == null) {
                throw new RuntimeException("The nulling object must not be inserted!");
            }
            if (this.delegate.contains(element)) {
                return;
            }
            this.delegate.add(index, element);
        }

        @Override
        public boolean addAll(final int index, final Collection<? extends E> c) {
            if (c == null) {
                throw new RuntimeException("The nulling objects must not be inserted!");
            }
            if (c.isEmpty()) {
                return false;
            }
            for (E element : c) {
                add(index, element);
            }
            return true;
        }

        @Override
        public E get(final int index) {
            return this.delegate.get(index);
        }

        @Override
        public int indexOf(final Object o) {
            return this.delegate.indexOf(o);
        }

        @Override
        public int lastIndexOf(final Object o) {
            return this.delegate.lastIndexOf(o);
        }

        @Override
        public ListIterator<E> listIterator() {
            return this.delegate.listIterator();
        }

        @Override
        public ListIterator<E> listIterator(final int index) {
            return this.delegate.listIterator(index);
        }

        @Override
        public E remove(final int index) {
            return this.delegate.remove(index);
        }

        @Override
        public E set(final int index, final E element) {
            this.delegate.add(index, element);
            return element;
        }

        @Override
        public List<E> subList(final int fromIndex, final int toIndex) {
            return this.delegate.subList(fromIndex, toIndex);
        }

        @Override
        public boolean add(final E e) {
            if (e == null) {
                throw new RuntimeException("The nulling object must not be inserted!");
            }
            if (this.delegate.contains(e)) {
                return false;
            }
            return this.delegate.add(e);
        }

        @Override
        public boolean addAll(final Collection<? extends E> c) {
            if (c == null) {
                throw new RuntimeException("The nulling objects must not be inserted!");
            }
            if (c.isEmpty()) {
                return false;
            }
            boolean returnValue = false;
            for (E element : c) {
                returnValue = add(element);
            }
            return returnValue;
        }

        @Override
        public void clear() {
            this.delegate.clear();
        }

        @Override
        public boolean contains(final Object o) {
            return this.delegate.contains(o);
        }

        @Override
        public boolean containsAll(final Collection<?> c) {
            return this.delegate.containsAll(c);
        }

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }

        @Override
        public boolean remove(final Object o) {
            return this.delegate.remove(o);
        }

        @Override
        public boolean removeAll(final Collection<?> c) {
            return this.delegate.removeAll(c);
        }

        @Override
        public boolean retainAll(final Collection<?> c) {
            return this.delegate.retainAll(c);
        }

        @Override
        public Object[] toArray() {
            return this.delegate.toArray();
        }

        @Override
        public <T> T[] toArray(final T[] a) {
            return this.delegate.toArray(a);
        }

        @Override
        public String toString() {
            return this.delegate.toString();
        }
    }
}