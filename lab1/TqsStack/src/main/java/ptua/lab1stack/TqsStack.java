package ptua.lab1stack;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TqsStack<T> {
    private int stack_limit = -1;
    private ArrayList<T> stack;

    public TqsStack() {
        this.stack = new ArrayList<>();
    }

    public TqsStack(int limit) {
        this.stack_limit = limit;
        this.stack = new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.stack.size() == 0;
    }

    public int size() {
        return this.stack.size();
    }

    public void push(T element) {
        if (this.stack_limit != -1) {
            if (this.stack_limit > this.stack.size()) {
                this.stack.add(element);
            } else {
                throw new IllegalStateException();
            }
        } else {
            this.stack.add(element);
        }
    }

    public T pop() {
        if (this.stack.size() > 0) {
            return this.stack.remove(this.stack.size()-1);
        } else {
            throw new NoSuchElementException();
        }
    }

    public T peek() {
        if (this.stack.size() > 0) {
            return this.stack.get(this.stack.size()-1);
        } else {
            throw new NoSuchElementException();
        }
    }
}