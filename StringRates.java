import java.io.PrintWriter;
import java.util.ArrayList;

public class StrRates {
    public static void main(String[] args) throws Exception {
        String strRates = "5.0,100,5.5,101,6.0,102:L10;5.0,99,5.5,100,6.0,101:L20;";

        String[] tokens = strRates.split("[,:;]");

        ArrayList<String> rates = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();
        ArrayList<String> locks = new ArrayList<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].contains(".")) {
                if (!rates.contains(tokens[i])) {
                    rates.add(tokens[i]);
                }
            }
            if (!tokens[i].contains(".") && !tokens[i].contains("L"))
                prices.add(tokens[i]);
            if (tokens[i].contains("L")) {
                String temp = tokens[i].replace("L", "");
                locks.add(temp);
            }
        }

        String[][] matrix = new String[rates.size() + 1][locks.size() + 1];
        ArrayList<String> list  = new ArrayList<>();

        list.add(0, "   ");
        list.addAll(locks);

        ArrayList<String> ratesAndPrices = new ArrayList<>();

        for (int i = 0; i < rates.size(); i++) {
            ratesAndPrices.add(rates.get(i));
            for (int j = 0; j < prices.size(); j+=rates.size()) {
                ratesAndPrices.add(prices.get(j + i));
            }
        }

        list.addAll(ratesAndPrices);

        int counter = 0;
        for (int i  = 0; i <= rates.size(); i++) {
            for (int j = 0 ; j < rates.size(); j++) {
                matrix[i][j] = list.get(counter);
                counter++;
            }
        }
        java.io.PrintWriter output = new java.io.PrintWriter("/Users/wesleytorrez/Downloads/justtesting.html");

        for (int row = 0; row < matrix.length; row++) {
            output.print("<table>");
            for (int column = 0; column < matrix[row].length; column++) {
                if (row == 0 && column == 0) {
                    output.print("&ensp;&emsp;");
                }
                output.printf("%6s", matrix[row][column] +"&emsp;");
            }
            output.println("</table>");
        }
        output.close();
    }
}
