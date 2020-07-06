package com.company;

import java.util.NoSuchElementException;

class DoublyLinkedListImpl<E> {

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedListImpl() {
        size = 0;
    }

    private class Node {
        E element;
        Node next;
        Node prev;

        private Node(E element, Node next, Node prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void addLast(E element) {

        Node tmp = new Node(element, null, tail);
        if(tail != null) {tail.next = tmp;}
        tail = tmp;
        if(head == null) { head = tmp;}
        size++;
        System.out.println("adding: "+element);
    }

    public E removeLast() {
        if (size == 0) throw new NoSuchElementException();
        Node tmp = tail;
        tail = tail.prev;
        tail.next = null;
        size--;
        System.out.println("deleted: "+tmp.element);
        return tmp.element;
    }

    private E forward (int index){
        if (size == 0) throw new NoSuchElementException();
        Node tmp = head;
        int count=0;
        while (tmp != null) {
            if (count==index){
                return tmp.element;
            }
            tmp = tmp.next;
            count++;
        }
        return tmp.element;
    }

    private E Backward (int index){
        if (size == 0) throw new NoSuchElementException();
        Node tmp = tail;
        int count=size-1;
        while (tmp != null) {
            if (index==count){
                return tmp.element;
            }
            tmp = tmp.prev;
            count--;
            if (count == -1) throw  new NoSuchElementException();
        }
        return tmp.element;
    }
    public E optima (int index){
        if ((Math.abs((index+1)-1))<(Math.abs((index+1)-(size+1)))){
            return forward(index);
        } else {
            return Backward (index);
        }
    }
}