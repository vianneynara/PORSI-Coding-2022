import java.util.Arrays;

public class Antrian {
    private Pasien[] antrian;
    private int front;
    private int rear;
    private int size;
    private int panjang;

    public Antrian(int panjang) {
        this.antrian = new Pasien[panjang];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
        this.panjang = panjang;
    }

    public boolean enqueue(Pasien data){
        if (size != capactiy()) {
            antrian[rear] = data;
            if (rear >= capactiy() - 1)
                rear = 0;
            else
                rear++;
            size++;
            return true;
        }
        return false;
    }

    public Pasien dequeue(){
        if (!isEmpty()) {
            if (front >= capactiy())
                front = 0;
            size--;
            Pasien data = antrian[front];
            antrian[front] = null;
            front++;
            return data;
        }
        return null;
    }

    public Pasien cancel() {
        if (!isEmpty()) {
            if (rear == 0)
                rear = capactiy() - 1;
            else
                rear--;
            size--;
            Pasien data = antrian[rear];
            antrian[rear] = null;
            return data;
        } else return null;
    }

    public int capactiy() {
        return panjang;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public int size(){
        return size;
    }

    public Pasien[] getAntrian() {
        return antrian;
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    public void info() {
        System.out.printf("front: %d, rear: %d, size: %d %s %n", front, rear, size, Arrays.toString(antrian));
    }
}