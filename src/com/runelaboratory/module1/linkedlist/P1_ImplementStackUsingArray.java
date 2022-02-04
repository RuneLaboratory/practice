package com.runelaboratory.module1.linkedlist;

public class P1_ImplementStackUsingArray {

    /*
    Implement a Stack Using an Array in Java

    The requirements of the stack are:
    1) the stack has a constructor which accepts a number to initialize its size,
    2) the stack can hold any type of elements,
    3) the stack has a push() and a pop() method.
    */

    public static void main(String[] args) {
        Stack<Character> a = new Stack<>(7);
        a.push('A');
        a.push('B');
        a.push('C');
        System.out.println(a.pop());
        a.push('D');
        a.push('E');
        a.push('F');
        a.push('G');
        a.push('H');

        System.out.println(a.pop());
        System.out.println(a.pop());
        System.out.println(a.pop());
        System.out.println(a.pop());
        System.out.println(a.pop());
        System.out.println(a.pop());

        Stack<String> stack = new Stack<>(11);
        stack.push("hello");
        stack.push("world");

        System.out.println(stack);

        stack.pop();
        System.out.println(stack);

        stack.pop();
        System.out.println(stack);
    }
}

class Stack<T> {

    private T[] elements;
    private int size;

    public Stack(int size) {
        this.elements = (T[]) new Object[size];
        this.size = 0;
    }

    public T push(T element) {

        if (size == this.elements.length) {
            return null;
        }

        this.elements[size] = element;
        this.size++;
        return element;
    }

    public T pop() {

        if (size == 0) {
            return null;
        }

        size--;
        T temp = this.elements[size];
        this.elements[size] = null;
        return temp;
    }

    public String toString() {
        if (this.size == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            sb.append(this.elements[i] + ", ");
        }

        sb.setLength(sb.length() - 2);
        return sb.toString();
    }
}

//Answer
class StackAns<E> {
    private E[] arr = null;
    private int CAP;
    private int top = -1;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public StackAns(int cap) {
        this.CAP = cap;
        this.arr = (E[]) new Object[cap];
    }

    public E pop() {
        if (this.size == 0) {
            return null;
        }

        this.size--;
        E result = this.arr[top];
        this.arr[top] = null;//prevent memory leaking
        this.top--;

        return result;
    }

    public boolean push(E e) {
        if (isFull())
            return false;

        this.size++;
        this.arr[++top] = e;

        return true;
    }

    public boolean isFull() {
        if (this.size == this.CAP)
            return true;
        return false;
    }

    public String toString() {
        if (this.size == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            sb.append(this.arr[i] + ", ");
        }

        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    public static void main(String[] args) {

        StackAns<String> stack = new StackAns<String>(11);
        stack.push("hello");
        stack.push("world");

        System.out.println(stack);

        stack.pop();
        System.out.println(stack);

        stack.pop();
        System.out.println(stack);
    }
}
