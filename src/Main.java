import java.util.Scanner;

class Main {
    private static final String shopName = "Наш Магаз";
    private static final int ndsPercent = 20;

    private static final String[] PRODUCTS = { "Хлеб", "Молоко", "Кетчуп", "Яйца", "Масло", "Макароны", "Сосиски" };
    private static final int[] PRICES = { 45, 80, 62, 90, 120, 70, 420 };
    private static final int[] COUNTS = new int[PRODUCTS.length];
    public static final int[] COSTS = new int[PRODUCTS.length];

    private static void showProducts() {
        System.out.println(" >-------- У вас в корзине сейчас имеется --------< ");
        for (int i = 1; i < PRODUCTS.length - 1; i++) {
            System.out.println("[" + i + "]\t" + PRODUCTS[i-1] + "\t\t"
                    + PRICES[i-1] + " руб\\шт." + "\t\t"
                    + COUNTS[i-1] + " шт.");
        }
        System.out.println(" >------------------ Конец списка ----------------< ");
    }

    public static int calculateSum() {
        int sum = 0;
        for (int i = 0; i < PRODUCTS.length; i++) {
            sum += COSTS[i];
        }
        return sum;
    }

    public static double calculateNds() {
        return (double) calculateSum() * ndsPercent / 100;
    }

    public static void splitChoice(String choiceString) {
        if (!choiceString.equals("")) {
            String[] choice = choiceString.split(" ");
            int index = Integer.parseInt(choice[0]);
            int count = Integer.parseInt(choice[1]);
            COUNTS[index - 1] += count;
            COSTS[index - 1] += count * PRICES[index - 1];
        }
    }

    public static void showCheck() {
        System.out.println("------------------ ЧЕК -----------------------");
        System.out.println("Наименование Количество Цена/за ед. Стоимость");
        for (int i = 0; i < PRODUCTS.length; i++) {
            if(COUNTS[i] > 0) {
                System.out.printf("%-12s %-10d %-11d %-9d%n", PRODUCTS[i], COUNTS[i], PRICES[i], COSTS[i]);
            }
        }
        System.out.println("Стоимость: " + (calculateSum() - calculateNds()) + " руб.");
        System.out.println("      НДС: " + calculateNds() + " руб.");
        System.out.println("    Итого: " + calculateSum() + " руб.");
        System.out.println("------------- Спасибо за покупку! ------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Приветствуем Вас в <<" + shopName + ">> !");
        while(true) {
            showProducts();
            System.out.println("Введите код товара и количесто, " +
                    "либо наберите end для выхода: ");
            String element = scanner.nextLine();
            if (element.equals("end")) {
                break;
            } else {
                splitChoice(element);
            }
        }
        showCheck();
    }
}