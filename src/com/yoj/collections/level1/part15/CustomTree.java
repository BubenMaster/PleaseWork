package com.yoj.collections.level1.part15;


import java.io.Serializable;
import java.util.*;

/*
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Serializable, Cloneable{

    TreeElem root = new TreeElem("Root");

    //private TreeElem<String> previousBud = root;
    private TreeElem nullElement = new TreeElem();


    private int rawLevel;
    private final int MAX_LEVEL = Integer.MAX_VALUE - 1;

    private enum condition {
        ALIVE,
        DEAD;
    }

    public CustomTree () {
        //Root element initialization in this Custom tree;
        this.root = root;
        this.root.setRoot();
        this.root.setBurgeon();
        this.root.levelIndex = 0;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    public String getParent (String name) {
        TreeElem result = this.searchParentByChildName(root, name);
        return result.entry.getName();
    }

    private TreeElem getParentElement (String name) {
        TreeElem result = this.searchParentByChildName(root, name);
        return result;
    }

    private TreeElem searchParentByChildName(TreeElem parent, String childName) {
        if (parent.isNull) return nullElement;
        if (parent.leftChild.entry.getName().equals(childName) || parent.rightChild.entry.getName().equals(childName)) {
            return parent;
        }
        TreeElem leftPath = this.searchParentByChildName(parent.leftChild, childName);
        TreeElem rightPath = this.searchParentByChildName(parent.rightChild, childName);
        if (!leftPath.isNull) return leftPath;
        if (!rightPath.isNull) return rightPath;
        else return nullElement;
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    //enable false returning if name is exist
    @Override
    public boolean add(String elementName) {
        //search for availible place;
        TreeElem parent = searchNewParent(root);
        if (parent.isNull()) {
            System.out.println("Null Parent!");
            // turning closing elements from Branches to Burgeons
            regrow(root);
            System.out.println("Tree ready for new elements");
            // search for availible place again
            parent = searchNewParent(root);
        }
        //adding new element as Burgeon and with two null elements as Children
        parent.addNewChild(elementName);
        //changing condition of parent if full of Children
        if (!parent.availableToAddRightChildren && !parent.availableToAddLeftChildren) parent.setBranch();
        return true;
    }

    //Execute this only if no elements can have child!
    private void regrow(TreeElem element) {
        if (element.isNull()) return;
        if (element.isBranch() && element.leftChild.isNull() && element.rightChild.isNull()) {
            element.setBurgeon();
            System.out.println("regrowth of " + element.entry.getName() + " done");
            return;
        }
        else {
            if (!element.leftChild.isNull()) {
                regrow(element.leftChild);
            }
            if (!element.rightChild.isNull()) {
                regrow(element.rightChild);
            }
        }
    }

    private void addNewElement(TreeElem parent) {

    }

    private TreeElem searchNewParent(TreeElem branch) {
        if (branch.isRoot()) rawLevel = MAX_LEVEL;
        //nullelement check
        if (branch.isNull()) {
            return nullElement;}

        if (branch.isBurgeon) {
            if (branch.levelIndex < rawLevel) {
                rawLevel = branch.getLevelIndex();
                return branch;
            }
            else return nullElement;
        }

        if (branch.isBranch) {
            TreeElem leftPath = searchNewParent(branch.leftChild);
            TreeElem rightPath = searchNewParent(branch.rightChild);
            if (leftPath.levelIndex > rightPath.levelIndex) return rightPath;
            else return leftPath;
        }

        return nullElement;

    }

    @Override
    public String remove(int object) {
        //changing of currentBud if it was in there
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove (Object object) {
        if (object instanceof String) return remove((String) object);
        throw new UnsupportedOperationException();
    }

    public boolean remove (String name) {
        boolean result = false;
        //get parent element of target
        TreeElem parent = getParentElement(name);
        if (parent.isNull()) return result;
        //replacing target with nullElement
        if (parent.leftChild.entry.getName().equals(name)) {
            parent.leftChild = nullElement;
            result = true;
        }
        if (parent.rightChild.entry.getName().equals(name)) {
            parent.rightChild = nullElement;
            result = true;
        }
        //!! need to check for block

        return result;
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
        int result = this.walker(root, 0) - 1;
        return result;
    }

    private int walker (TreeElem elem, int sizeCounter){
        if (elem.isNull()) return sizeCounter;
        else return this.walker(elem.leftChild, sizeCounter) + this.walker(elem.rightChild, sizeCounter) + 1;
    }

    @Override
    public CustomTree clone() {
        try {
            return (CustomTree) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


    //Local class for wrapping Entry<T>
    private class TreeElem {
        private Entry<String> entry;
        private TreeElem leftChild,
                         rightChild;

        private boolean availableToAddLeftChildren,
                        availableToAddRightChildren;

        private int levelIndex;

        private boolean isRoot = false, //for the root element only
                        isBranch = false, // Branch cannot have new elements at all
                        isBurgeon = false, //Burgeon can have at least one new element
                        isNull = false;

        //new Element
        private TreeElem (String name) {
            this.entry = new Entry(name);
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
            this.setBurgeon();
            //!adds null children
            this.leftChild = nullElement;
            this.rightChild = nullElement;
        }

        //null element
        private TreeElem () {
            this.entry = new Entry<>("null");
            availableToAddLeftChildren = false;
            availableToAddRightChildren = false;
            this.levelIndex = Integer.MAX_VALUE;
            this.setNull();
        }


        private boolean isRoot() {
            return this.isRoot;
        }

        public void setRoot() {
            this.isRoot = true;
        }

        private boolean isNull() {
            return this.isNull;
        }

        public void setNull() {
            this.isNull = true;
        }

        private boolean isBranch() {
            return this.isBranch;
        }

        public void setBranch() {
            this.isBranch = true;
            this.isBurgeon = false;

        }

        private boolean isBurgeon() {
            return this.isBurgeon;
        }

        public void setBurgeon() {
            this.isBranch = false;
            this.isBurgeon = true;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        public void setLevelIndex(int levelIndex) {
            this.levelIndex = levelIndex;
        }

        public int getLevelIndex() {
            return this.levelIndex;
        }

        public void addNewChild(String elementName) {
         if (this.availableToAddLeftChildren) {
             this.leftChild = new TreeElem(elementName);
             this.leftChild.levelIndex = this.levelIndex + 1;
             this.availableToAddLeftChildren = false;
         }
         else if (this.availableToAddRightChildren) {
             this.rightChild = new TreeElem(elementName);
             this.availableToAddRightChildren = false;
             this.rightChild.levelIndex = this.levelIndex + 1;
         }

        }
    }

    //Local unwrapped Entry class
    private class Entry<T> implements Serializable {
        private T name;
        //private int index;

        Entry (T name) {
            this.name = name;
            //this.index = index;
        }

       // public void setIndex(int index) {            this.index = index;        }

       // public int getIndex() {            return index;        }

        public void setName(T name) {
            this.name = name;
        }

        public T getName() {
            return name;
        }

    }


}
