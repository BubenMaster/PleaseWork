package com.yoj.collections.level1.part15;


import java.io.Serializable;
import java.util.*;

public class FakeCustomTree extends AbstractList<String> implements Cloneable, Serializable {

    /**
     * ��������� ������ ��� ������;
     */
    private Entry<String> root;

    /**
     * root ���������������� �� ���������, � ���������� ������ ��������� ������������ ��� "����� �������� ������� ���
     * �������� ������ ������"; newLineRootElement - ������ ������� ����� ������ ������� ������.
     */
    public FakeCustomTree() {
        root = new Entry<String>(null);
        root.newLineRootElement = true;
        root.lineNumber = 0;
    }

    /**
     * ����� getParent ��������� �������� String value, ���� � ����� Entry c ��������� elementName �������������
     * �������� value, � ���������� �������� elementName � ���������� Entry (Entry.parent)
     *
     * @param value elementName ����������� Entry
     * @return parent.elementName ���������� Entry (Entry.parent)
     */
    public String getParent(String value) {
        setValidCollection();
        String s = null;
        for (Entry<String> entry : queue) {
            if (entry.lineNumber != 1) {
                if (entry.elementName.equals(value)) {
                    s = entry.parent.elementName;
                    break;
                }
            }
        }
        return s;
    }

    /**
     * ��������� ��� �������� ��������� ������.
     */
    private transient ArrayList<Entry<String>> queue;

    /**
     * ����� setUpCollection �������� �� ������, ������� � �������� Entry<String> root � �������������� ��� �������� �
     * queue;
     *
     * @param root ��������� ������� Entry<String> ��� ������������� ������� �� ������.
     */
    private void setUpCollection(Entry<String> root) {
        queue = new ArrayList<>();
        Queue<Entry<String>> subQueue = new LinkedList<Entry<String>>();
        queue.add(root);
        subQueue.add(root);
        do {
            if (!subQueue.isEmpty()) {
                root = subQueue.poll();
            }
            if (root.leftChild != null) {
                queue.add(root.leftChild);
                subQueue.add(root.leftChild);
            }
            if (root.rightChild != null) {
                queue.add(root.rightChild);
                subQueue.add(root.rightChild);
            }
        } while (!subQueue.isEmpty());

    }

    /**
     * �����  getCollection ���������� �� ������ setUpCollection ������ ���, ��� ���������� ���������� ���������;
     *
     * @param entry ��������� ������� Entry<String> ��� ������������� ������� �� ������.
     * @return ���������� ��������� (List<Entry<String>>) ��������� ������.
     */
    private List<Entry<String>> getCollection(Entry<String> entry) {
        ArrayList<Entry<String>> list = new ArrayList<>();
        Queue<Entry<String>> subQueue = new LinkedList<Entry<String>>();
        list.add(entry);
        subQueue.add(entry);
        do {
            if (!subQueue.isEmpty()) {
                entry = subQueue.poll();
            }
            if (entry.leftChild != null) {
                list.add(entry.leftChild);
                subQueue.add(entry.leftChild);
            }
            if (entry.rightChild != null) {
                list.add(entry.rightChild);
                subQueue.add(entry.rightChild);
            }
        } while (!subQueue.isEmpty());
        return list;
    }

    /**
     * ����� setValidCollection ���������� ������ ��������� ��������� ������, � ����� ������� 1 ������� ���������,
     * ������� �������� ����������� ������.
     * ������������ � ������� remove � �.�. ��� ��������� ������� �� ���������.
     */
    private void setValidCollection() {
        setUpCollection(root);
        queue.remove(0);
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    /**
     * ����� indexOf() ���������� ������ �������� � ���������, ��� -1 ���� ������ �������� ���.
     *
     * @param o ������� �������
     * @return ������ �������� � ���������
     */
    @Override
    public int indexOf(Object o) {
        String string = String.valueOf(o);
        setValidCollection();
        for (Entry<String> entry : queue) {
            if (entry.elementName.equals(string)) {
                return queue.indexOf(entry);
            }
        }
        return -1;
    }

    /**
     * ����� lastIndexOf() ���������� ������ �������� � ���������, ��� -1 ���� ������ �������� ���.
     *
     * @param o ������� �������
     * @return ������ �������� � ���������
     */
    @Override
    public int lastIndexOf(Object o) {
        String string = String.valueOf(o);
        setValidCollection();
        Entry<String> element = null;
        for (Entry<String> entry : queue) {
            if (entry.elementName.equals(string)) {
                element = entry;
            }
        }
        return queue.lastIndexOf(element);
    }

    /**
     * ����� add ��������� ������� � ������. ������� ��������������� ������ ��������� ���������, ����� ����������
     * �������� �� ���������. ��� �������� ������ ������� ����������� ������� checkChildren �� ����������� �����
     * �����. isAvailableToAddChildren() ���������� true ���� ����� ����������� �������; ����� ���� ��������� �
     * ���������������� ����� ������� Entry<String>, ����������� � ���������. ����� ���� ��������� ������
     * ����������� � ���������������� � ������� setValidCollection();
     * ���� ����� ������� �� ���� ��������� �� ���� ������� �� �������� ����� ����� �����, ��� ��������, ��� ���
     * ������� ��������. ����� ���������� ��������� �������� �� ��������� � ����� ������ �������� � boolean ������
     * newLineRootElement; ��������� ������� � ��� ����������� �������� � ��������� ��������������� �����������
     * ����� ��������;
     *
     * @param s ������ (String) ������� ���������� �������� � ���������;
     * @return true ����� ���������� ������ ��������;
     */
    @Override
    public boolean add(String s) {
        setUpCollection(root);
        for (Entry<String> entry : queue) {
            entry.checkChildren();
            if (entry.isAvailableToAddChildren()) {
                createChild(entry, s);
                setValidCollection();
                return true;
            }
        }

        boolean reAddingChildren = false;
        for (Entry<String> entry : queue) {
            if (entry.newLineRootElement) {
                reAddingChildren = true;
            }
            if (reAddingChildren) {
                entry.availableToAddLeftChildren = true;
                entry.availableToAddRightChildren = true;
            }
        }

        return add(s);
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    /**
     * ����� toArray ���������� ������ elementName ������� ��������� ���������.
     *
     * @return ������ ����� elementName;
     */
    @Override
    public String[] toArray() {
        setValidCollection();
        int size = size();
        String[] array = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = queue.get(i).elementName;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        int size = size();
        T[] array = a.length >= size ? a : (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        Iterator<String> iterator = iterator();
        for (int i = 0; i < array.length; i++) {
            if (!iterator.hasNext()) { // fewer elements than expected
                if (a != array) {
                    return Arrays.copyOf(array, i);
                }
                array[i] = null; // null-terminate
                return array;
            }
            array[i] = (T) iterator.next();
        }
        return array;
    }

    /**
     * ����� changeNewLineRootElement ������ boolean ���� newLineRootElement e ���������, ���������� ��������.
     * ����� ������� ������ �� ��������: ���� �������� �������� ������, �� ���������� ��������, ���� �� ��������,
     * �� ����������� �� ������� ���� � ���� ��� ����� �����.
     *
     * @param entry   Entry<String> ������� ������� ���������� newLineRootElement;
     * @param delList ������ ��������� ��������� Entry<String> ���������� ��������;
     */
    private void changeNewLineRootElement(Entry<String> entry, List<Entry<String>> delList) {
        setValidCollection();
        int size = size();
        for (int i = 0; i < size; i++) {
            Entry<String> newEntry = queue.get(i);
            if (newEntry == entry) {
                if (i < size - 1) {
                    newEntry.newLineRootElement = false;
                    for (int k = i + 1; k < size; k++) {
                        Entry<String> newRootEntry = queue.get(k);
                        if (!delList.contains(newRootEntry)) {
                            newRootEntry.newLineRootElement = true;
                            break;
                        }
                    }
                    //���� ��� �������� ������ �������� �������� ���� ����� �� ������:
                    Entry<String> element = getUndelRoot(newEntry.parent, delList);
                    element.newLineRootElement = true;
                    break;
                } else {
                    newEntry.newLineRootElement = false;
                    Entry<String> element = getUndelRoot(newEntry.parent, delList);
                    element.newLineRootElement = true;
                    break;
                }
            }
        }
    }

    /**
     * ����� getUndelRoot ���� ����������� � ������������� ������ ������� Entry<String> �� �������� � ���� delList
     * (������ ��������� ���������� ��������);
     *
     * @param entry   ��������� ������� Entry<String> �� �������� ���������� �����;
     * @param delList ������ ��������� ���������� ��������
     * @return ������ ��������� ������� ����� ������� Entry<String> �� �������� � delList;
     */
    private Entry<String> getUndelRoot(Entry<String> entry, List<Entry<String>> delList) {
        for (Entry<String> element : queue) {
            if (element.lineNumber == entry.lineNumber) {
                if (!delList.contains(element)) {
                    return element;
                }
            }
        }
        if (entry.lineNumber == 0) {   // ����� �� root � ���������� ���.
            return entry;
        }
        return getUndelRoot(entry.parent, delList);
    }

    /**
     * ���. ����� ��� ������������. ��� �������� ������ ���������� 2 �������� newLineRootElement;
     * ���� ����� �������� ����������� ��� ����� �������� � ������� ����� newLineRootElement;
     *
     * @param entry Entry<String> �� ��������� true ���������� newLineRootElement, ������� ��������
     *              ���������;
     * @return ������ ������� � Entry<String> entry � ���������� ����� ��� parent �� ��������� true
     * ���������� newLineRootElement, ������� �������� ���������;
     */
    private List<Entry<String>> getNewLineRootElementsCollection(Entry<String> entry) {
        ArrayList<Entry<String>> list = new ArrayList<>();
        list.add(entry);
        if ((entry.parent != null) && (entry.parent.newLineRootElement)) {
            list.addAll(getNewLineRootElementsCollection(entry.parent));
        }
        return list;
    }

    /**
     * ����� createChild ������� ����� ������� Entry<String> � ������������� �������� ���������� parent
     *
     * @param parent ������������ ������� Entry<String>;
     * @param s      �������� elementName ��� ������ �������� Entry<String>;
     */
    private void createChild(Entry<String> parent, String s) {
        Entry<String> newOne = new Entry<String>(s);
        newOne.parent = parent;
        newOne.lineNumber = parent.lineNumber + 1;
        setChild(parent, newOne);
    }

    /**
     * ����� setChild ����������� ���������� left/rightChild �������� ������ �� ������� Entry<String> child;
     * ���� ���������� newLineRootElement �������� ����� �������� true, �� ��� �������� ���������� �������, �
     * �������� ��������� �� false;
     *
     * @param parent ������������ ������� Entry<String>
     * @param child  �������-������� Entry<String>
     */
    private void setChild(Entry<String> parent, Entry<String> child) {
        if (parent.availableToAddLeftChildren) {
            parent.leftChild = child;
            parent.availableToAddLeftChildren = false;
            if (parent.newLineRootElement) {
                List<Entry<String>> list = getNewLineRootElementsCollection(parent);
                for (Entry<String> entry : list) {
                    entry.newLineRootElement = false;
                }
                child.newLineRootElement = true;
            }
        } else {
            parent.rightChild = child;
            parent.availableToAddRightChildren = false;
            if (parent.newLineRootElement) {
                List<Entry<String>> list = getNewLineRootElementsCollection(parent);
                for (Entry<String> entry : list) {
                    entry.newLineRootElement = false;
                }
                child.newLineRootElement = true;
            }
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        setValidCollection();
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Unsupported Operation
     *
     * @param index param
     * @return UnsupportedOperationException();
     */
    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * This implementation iterates over this collection, checking each
     * element returned by the iterator in turn to see if it's contained
     * in the specified collection.  If it's not so contained, it's removed
     * from this collection with the iterator's <tt>remove</tt> method.
     *
     * @param c ��������� �������� ������� ����� ��������� � ������� ���������.
     * @return true ���� ������� ��������� ����������� ���������, ����� false
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        setValidCollection();
        boolean modified = false;
        Iterator<String> iterator = iterator();
        while (iterator.hasNext()) {
            if (!c.contains(iterator.next())) {
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * This implementation iterates over the specified collection, and adds
     * each object returned by the iterator to this collection, in turn.
     *
     * @param collection �������� ��� ����������
     * @return true ���� ��������� ����������
     */
    @Override
    public boolean addAll(Collection<? extends String> collection) {
        boolean modified = false;
        for (String line : collection) {
            if (add(line)) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * @return ������ ��������� ��������� Entry<String>;
     */
    @Override
    public int size() {
        setValidCollection();
        return queue.size();
    }

    /**
     * ���� ! � instanceof String throws UnsupportedOperationException();
     * ������� ������ ��������� � ������ ������� Entry<String> �� ��������� ��������� elementName
     * �������������� o �, � ������ ������ ���������� true. ���� ������� �� ������ ������������ false;
     *
     * @param o �������� ������ ���������� �������� �� ������.
     * @return ���� ������� ������ � ������ ������������ true, ����� false;
     */
    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String)) {
            throw new UnsupportedOperationException();
        }
        String value = (String) o;
        setValidCollection();
        for (int i = 0; i < queue.size(); i++) {
            Entry<String> entry = queue.get(i);
            if (entry.elementName.equals(value)) {
                List<Entry<String>> list = getCollection(entry);
                for (Entry<String> stringEntry : list) {
                    if (stringEntry.newLineRootElement) {
                        changeNewLineRootElement(stringEntry, list);
                    }
                }
                if (entry.parent.leftChild == entry) {
                    entry.parent.leftChild = null;
                    setValidCollection();
                    return true;
                } else {
                    entry.parent.rightChild = null;
                    setValidCollection();
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * ����� removeAll ������� ��� �������� �� ������� ��������� queue, elementName ������� ������ � ��������� �;
     *
     * @param c ��������� � ���������� ����������� ��������;
     * @return true ���� �������� 1 � ����� ���������, ����� false;
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        setValidCollection();
        for (Entry<String> entry : queue) {
            if (c.contains(entry.elementName)) {
                remove(entry.elementName);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * @return true, ���� ��������� �����;
     */
    @Override
    public boolean isEmpty() {
        setValidCollection();
        return size() == 0;
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     */
    @Override
    public void clear() {
        root.newLineRootElement = true;
        root.availableToAddLeftChildren = true;
        root.availableToAddRightChildren = true;
        root.leftChild = null;
        root.rightChild = null;
        setValidCollection();
    }

    /**
     * @param o ������ ��� ������ (������);
     * @return true ���� � ������ ������ ������� Entry<String> �� ��������� ��������� elementName
     * ������������� �������� �; � ��������� ������ ���������� false;
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }


    /**
     * Creates and returns a copy of this object.  The precise meaning
     * of "copy" may depend on the class of the object.
     *
     * @return a clone of this instance.
     * @throws CloneNotSupportedException if the object's class does not
     *                                    support the {@code Cloneable} interface. Subclasses
     *                                    that override the {@code clone} method can also
     *                                    throw this exception to indicate that an instance cannot
     *                                    be cloned.
     */
    @Override
    protected CustomTree clone() throws CloneNotSupportedException {
        CustomTree customTree = (CustomTree) super.clone();
        return customTree;
    }

    /**
     * ���������� equals ��� ������;
     *
     * @param o another object
     * @return true if objects equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FakeCustomTree customTree = (FakeCustomTree) o;
        this.setValidCollection();
        customTree.setValidCollection();
        int size = size();
        for (int i = 0; i < size; i++) {
            Entry first = queue.get(i);
            Entry second = customTree.queue.get(i);
            if (!first.equals(second)) {
                return false;
            }
        }
        return true;
    }

    /**
     * ���������� hashCode ��� ������;
     *
     * @return hashCode �������
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (queue != null ? queue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        setValidCollection();
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < queue.size(); i++) {
            builder.append(queue.get(i).elementName);
            if (i < queue.size() - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    /**
     * ����� ���������� ����-�������� ��� ��������� ���������;
     *
     * @return new SolutionListIterator;
     */
    @Override
    public ListIterator<String> listIterator() {
        return new SolutionListIterator(0);
    }

    /**
     * ����� ���������� ����-�������� ��� ������� �� ��������� ���������, ������� � ������� index;
     *
     * @param index - ��������� ������ ��� ������� �� ���������.
     * @return new SolutionListIterator;
     */
    @Override
    public ListIterator<String> listIterator(int index) {
        checkElementIndex(index);
        return new SolutionListIterator(index);
    }

    /**
     * �������� ������� �� ����������
     *
     * @param index ������ ��������
     * @return true ���� ������ ��������� � �������� ����� ���������.
     */
    private boolean checkIndex(int index) {
        return index >= 0 && index < size();
    }

    /**
     * ���� ������ �������� ������ 0 ��� ������ ������� ��������� �������� ����������
     *
     * @param index ������ ��������
     */
    private void checkElementIndex(int index) {
        if (!checkIndex(index)) {
            throw new IndexOutOfBoundsException("List size: " + queue.size() + ", Index: " + index);
        }
    }

    /**
     * ����� ���������� �������� ��� ��������� ���������;
     *
     * @return new SolutionListIterator;
     */
    @Override
    public Iterator<String> iterator() {
        return new SolutionIterator();
    }

    private class SolutionIterator implements Iterator<String> {
        volatile ArrayList<Entry<String>> list;
        volatile int index, lastReturned = -1; // lastReturned - ������ ���������� ������������� ��������, -1 ���� ������ �������� ���.

        SolutionIterator() {
            setValidCollection();
            list = queue;
        }

        @Override
        public synchronized boolean hasNext() {
            return index != list.size();
        }

        @Override
        public synchronized String next() {
            int i = index;
            if (i >= size()) {
                throw new NoSuchElementException();
            }
            index = i + 1;
            return list.get(lastReturned = i).elementName;
        }

        @Override
        public synchronized void remove() {
            if (lastReturned < 0) {
                throw new IllegalStateException();
            }
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("List size: " + list.size() + ", Index: " + index);
            }
            FakeCustomTree.this.remove(list.get(lastReturned).elementName);
            index = lastReturned;
            lastReturned = -1;
            list = queue;
        }
    }

    private class SolutionListIterator extends SolutionIterator implements ListIterator<String> {

        SolutionListIterator(int index) {
            super();
            this.index = index;
        }

        @Override
        public synchronized boolean hasPrevious() {
            return index != 0;
        }

        @Override
        public synchronized String previous() {
            int i = index - 1;
            if (i < 0) {
                throw new NoSuchElementException("List size: " + list.size() + ", Index: " + i);
            }
            if (i >= list.size()) {
                throw new IllegalStateException();
            }
            index = i;
            return list.get(lastReturned = i).elementName;
        }

        @Override
        public synchronized int nextIndex() {
            return index;
        }

        @Override
        public synchronized int previousIndex() {
            return index - 1;
        }

        @Override
        public synchronized void set(String s) {
            if (lastReturned < 0) {
                throw new IllegalStateException();
            }
            for (Entry<String> entry : queue) {
                if (entry.equals(list.get(lastReturned))) {
                    entry.elementName = s;
                }
            }
        }

        @Override
        public synchronized void add(String s) {
            FakeCustomTree.this.add(s);
            list = queue;
        }
    }


    private static class Entry<T> implements Serializable {

        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        boolean newLineRootElement;
        Entry<T> parent, leftChild, rightChild;

        private Entry(String name) {
            elementName = name;
            newLineRootElement = false;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public void checkChildren() {
            if (this.leftChild != null) {
                availableToAddLeftChildren = false;
            }
            if (this.rightChild != null) {
                availableToAddRightChildren = false;
            }
        }

        public boolean isAvailableToAddChildren() {
            return this.availableToAddRightChildren || this.availableToAddLeftChildren;
        }
    }
}

