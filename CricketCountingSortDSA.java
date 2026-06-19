
class Delivery {
    int over, ball;

    Delivery(int over, int ball) {
        this.over = over;
        this.ball = ball;
    }
}

public class CricketCountingSort {

    static Delivery[] countingSortBall(Delivery arr[]) {
        int k = 6;
        Delivery output[] = new Delivery[arr.length];
        int count[] = new int[k + 1];

        for (Delivery d : arr) count[d.ball]++;

        for (int i = 1; i <= k; i++)
            count[i] += count[i - 1];

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i].ball] - 1] = arr[i];
            count[arr[i].ball]--;
        }
        return output;
    }

    static Delivery[] countingSortOver(Delivery arr[]) {
        int k = 5;
        Delivery output[] = new Delivery[arr.length];
        int count[] = new int[k + 1];

        for (Delivery d : arr) count[d.over]++;

        for (int i = 1; i <= k; i++)
            count[i] += count[i - 1];

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i].over] - 1] = arr[i];
            count[arr[i].over]--;
        }
        return output;
    }

    public static void main(String args[]) {
        Delivery arr[] = {
            new Delivery(2,4), new Delivery(1,1),
            new Delivery(3,6), new Delivery(1,5),
            new Delivery(2,2), new Delivery(3,1),
            new Delivery(1,3), new Delivery(2,6),
            new Delivery(3,4), new Delivery(1,2)
        };

        arr = countingSortBall(arr);
        arr = countingSortOver(arr);

        System.out.println("Final Sorted Order:");
        for(Delivery d : arr)
            System.out.print("(" + d.over + "," + d.ball + ") ");
    }
}
