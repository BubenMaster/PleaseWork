package com.yoj.collections.level1.part15;

import java.io.Serializable;
import java.util.*;

/*
Построй дерево(1)
*/

public class CustomTreeOld extends AbstractList<String> implements Serializable, Cloneable{

    private int deleteCounter;

    Entry<String> root = new Entry<>("Root") ,
                        currentBud = root;
    private Entry<String> previousBud = root;
    private Entry<String> nullElement = new Entry<>();


    private enum condition {
        ALIVE,
        DEAD;

    }
    // treeSize defines size of all elements excluding root element, ony branches, also deleted.
    static private HashMap<Integer, condition> treeSize = new HashMap<>();

    public CustomTreeOld() {
        this.root = root;
    }

    @Override
    public String get(int index) {
        Entry<String> subject = root;
        while (subject.elementIndex != index) {
            subject = subject.nextBud;
            if (subject == null) return null;
            if (subject.equals(nullElement)) return null;
        }

        return subject.getParent().getName();
    }

    public Entry getEntry(String name) {
        Entry<String> subject = root;
        while (!subject.getName().equals(name)) {
            subject = subject.nextBud;
            if (subject == null) return null;
            //if (treeSize.get(subject.elementIndex) == condition.DEAD) return nullElement;
        }
        return subject;
    }

    public String getParent (String name) {
        Entry<String> subject = root;
        while (!subject.getName().equals(name)) {
            subject = subject.nextBud;
            if (subject == null) return null;
            if (subject.equals(nullElement)) return null;
        }

        return subject.getParent().getName();
    }



    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean add(String elementName) {

        //growing new branch from currentBud
            //new position in tree size before new Entry initialisation
            if (treeSize == null) {treeSize.put(1, condition.ALIVE);}
        else {treeSize.put(treeSize.size() + 1, condition.ALIVE);}
        Entry<String> newChild = new Entry<>(elementName);
            //null element connection
        newChild.setLeftChild(nullElement);
        newChild.setRightChild(nullElement);
        newChild.setNextBud(nullElement);
            //setting of connection between adjacent children
        previousBud.setNextBud(newChild);
        previousBud = newChild;
            //
        newChild.setParent(currentBud);
        currentBud.setNewChild(newChild);

        //
        if (currentBud.availableToAddLeftChildren) {
            currentBud.availableToAddLeftChildren = false;
        } else if (currentBud.availableToAddRightChildren) {
            currentBud.availableToAddRightChildren = false;
        }
        //changing of currentBud to new one
        if (!currentBud.isAvailableToAddChildren()){
            currentBud = this.getNextBud();
        }
        return true;
    }

    @Override
    public String remove(int index) {

        //changing of currentBud if it was in there
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object obj) {
        //changing of currentBud if it was in there
       if (!(obj instanceof String)) throw new UnsupportedOperationException();
       else remove((String) obj);
       return true;
    }

    public int remove(String name) {
        deleteCounter = 0;
       Entry<String> branch =  getEntry(name);
       if (!branch.leftChild.getName().equals("null"))  removeLocal(branch.leftChild.getName());
       if (!branch.rightChild.getName().equals("null"))  removeLocal(branch.rightChild.getName());

       //changing state of this element
       treeSize.replace(branch.elementIndex, condition.ALIVE, condition.DEAD);
       //changing this element to null element
        branch.setParent(nullElement);
        branch.availableToAddLeftChildren = false;
        branch.availableToAddRightChildren = false;
        branch = nullElement;


       return deleteCounter + 1;
    }

    private int removeLocal(String name) {
        Entry<String> branch =  getEntry(name);
        if (!branch.leftChild.getName().equalsIgnoreCase("null"))  removeLocal(branch.leftChild.getName());
        if (!branch.rightChild.getName().equalsIgnoreCase("null"))  removeLocal(branch.rightChild.getName());

        //changing state of this element
        treeSize.replace(branch.elementIndex, condition.ALIVE, condition.DEAD);
        //changing this element to null element
        branch.setParent(nullElement);
        branch.availableToAddLeftChildren = false;
        branch.availableToAddRightChildren = false;
        branch = nullElement;
        deleteCounter ++;
        return 1;
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        int realSize = 0;
        for (int i = 0; i <= treeSize.size(); i++) {
            if (treeSize.get(i) == condition.ALIVE) ++realSize;
        }
        return realSize;
    }

    @Override
    public CustomTreeOld clone() {
        try {
            return (CustomTreeOld) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    private Entry<String> getNextBud() {
        Entry<String> result;
        int index = currentBud.elementIndex + 1;
        //DEAD or ALIVE check
        while (treeSize.get(index) != condition.ALIVE) {
            index++;
        }
        //walking for the next desired bud with right index
        int budIndex;
        do {
            budIndex = currentBud.nextBud.elementIndex;
            currentBud = currentBud.nextBud;
        } while (index != budIndex);

            return currentBud;
    }

    public static HashMap<Integer, condition> getTreeSize() {
        return treeSize;
    }

    //Local Entry class
    static class Entry<T> implements Serializable {
        String elementName;
        int elementIndex;
        boolean availableToAddLeftChildren,
                availableToAddRightChildren;

        Entry<T> parent,
                leftChild,
                rightChild,
                nextBud;


        public Entry (String name) {
            this.elementName = name;
            if (treeSize == null) {this.elementIndex = 0;} //root condition !!! makeRoot() method in constructor!!!
            else {this.elementIndex = treeSize.size();}
            this.availableToAddRightChildren = true;
            this.availableToAddLeftChildren = true;
        }

        public Entry() {
            this.elementName = "null";
            this.elementIndex = 0;
            this.availableToAddRightChildren = false;
            this.availableToAddLeftChildren = false;
        }

        public boolean isAvailableToAddChildren() {
            return (availableToAddLeftChildren || availableToAddRightChildren);
        }

        public Entry<T> getParent() {
            return this.parent;
        }

        private void setParent(Entry<T> parent) {
            this.parent = parent;
        }

        public Entry<T> getRightChild() {
            return this.rightChild;
        }

        public void setNextBud(Entry<T> nextBud) {
            this.nextBud = nextBud;
        }

        private void setRightChild(Entry<T> rightChild) {
            this.rightChild = rightChild;
        }

        public Entry<T> getLeftChild() {
            return this.leftChild;
        }

        private void setLeftChild(Entry<T> leftChild) {
            this.leftChild = leftChild;
        }

        public String getName() {
            return this.elementName;
        }

        public void setNewChild(Entry<T> newChild) {
            if (this.availableToAddLeftChildren) {
                this.setLeftChild(newChild);
            } else if (this.availableToAddRightChildren) {
                this.setRightChild(newChild);
            }
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "elementName='" + elementName + '\'' +
                    ", elementIndex=" + elementIndex +
                    ", availableToAddLeftChildren=" + availableToAddLeftChildren +
                    ", availableToAddRightChildren=" + availableToAddRightChildren +
                    ", parent=" + parent.getName() +
                    ", leftChild=" + leftChild.getName() +
                    ", rightChild=" + rightChild.getName() +
                    ", nextBud=" + nextBud.getName() +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entry)) return false;
            Entry<?> entry = (Entry<?>) o;
            return availableToAddLeftChildren == entry.availableToAddLeftChildren && availableToAddRightChildren == entry.availableToAddRightChildren && elementName.equals(entry.elementName) && Objects.equals(parent, entry.parent) && Objects.equals(leftChild, entry.leftChild) && Objects.equals(rightChild, entry.rightChild);
        }

        @Override
        public int hashCode() {
            return Objects.hash(elementName, availableToAddLeftChildren, availableToAddRightChildren, parent, leftChild, rightChild);
        }
    }
}
