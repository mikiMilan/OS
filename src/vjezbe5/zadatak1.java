package vjezbe5;


class Stack{

    private int[] stack;
    private int top;

    Stack(int size){
        stack = new int[size];
        top = -1;
    }

    public boolean isEmpty(){
        return top==-1;
    }

    public boolean isFull(){
        return top==stack.length - 1;
    }

    public synchronized void push(int element){

        while (isFull()){
            System.out.println("Stek je pun!");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        stack[++top] = element;
        notify();
    }

    public synchronized int pop(){
        while (isEmpty()){
            System.out.println("Stek je prazan!");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        int x = stack[top--];
        notify();
        return x;
    }
}

class TredA extends Thread{
    private Stack s;

    TredA(Stack s){
        this.s = s;
    }

    @Override
    public void run() {
        for (int i =0; i<100;i++){
            s.push(i);
            System.out.println("Dodali smo " + i);
        }
    }
}

class TredB extends Thread{
    private Stack s;

    TredB(Stack s){
        this.s = s;
    }

    @Override
    public void run() {
        for (int i =0; i<100;i++){
            int x = s.pop();
            System.out.println("Uzeli smo " + x);
        }
    }
}

public class zadatak1 {

    public static void main(String[] args) {
        Stack s = new Stack(10);
        TredA a = new TredA(s);
        TredB b = new TredB(s);

        a.start();
        b.start();

        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Kraj");
    }
}
