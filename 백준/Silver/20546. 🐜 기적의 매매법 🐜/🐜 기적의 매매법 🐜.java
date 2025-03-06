import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int cash1, cash2;
    static int stock1, stock2;
    static int[] priceList;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int cash = Integer.parseInt(reader.readLine());
        cash1 = cash;
        cash2 = cash;

        String[] input = reader.readLine().split(" ");
        priceList = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();

        for (int day=0; day<14; day++) {
            determine(day);
        }

        // Day 14 정산
        cash1 += stock1 * priceList[13];
        cash2 += stock2 * priceList[13];

        if (cash1 > cash2) {
            System.out.println("BNP");
        } else if (cash2 > cash1) {
            System.out.println("TIMING");
        } else {
            System.out.println("SAMESAME");
        }

    }

    static void determine(int day) {
        // 준현 case
        if (priceList[day] <= cash1) {
            stock1 += cash1 / priceList[day];
            cash1 = cash1 % priceList[day];
        }

        // 성민 case
        if (day > 2) {
            if (priceList[day] < priceList[day-1] && priceList[day-1] < priceList[day-2] && priceList[day-2] < priceList[day-3]) {
                if (priceList[day] <= cash2) {
                    stock2 += cash2 / priceList[day];
                    cash2 = cash2 % priceList[day];
                }
            } else if (priceList[day] > priceList[day-1] && priceList[day-1] > priceList[day-2] && priceList[day-2] > priceList[day-3]) {
                if (stock2 > 0) {
                    cash2 += stock2 * priceList[day];
                    stock2 = 0;
                }
            }
        }
    }

}