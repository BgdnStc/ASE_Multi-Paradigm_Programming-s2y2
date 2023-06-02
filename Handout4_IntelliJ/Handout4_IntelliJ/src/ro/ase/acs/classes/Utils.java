package ro.ase.acs.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

public class Utils {
    public static DataSeriesOperation anonymousOperation = new DataSeriesOperation() {
        @Override
        public Double doOperation(Integer[] values) {
            double product = 1;
            for (int i = 0; i < values.length; i++) {
                product *= values[i];
            }
            return product;
        }
    };

    public static DataSeriesOperation lambdaOperation = new DataSeriesOperation() {
        @Override
        public Double doOperation(Integer[] values) {
            double average = 0;
            for (int i = 0; i < values.length; i++) {
                average += values[i];
            }
            average /= values.length;
            return average;
        }
    };

    static class RunnableClass implements Runnable {
        @Override
        public void run() {
            result = anonymousOperation.doOperation(input);
        }
    }

    static class CallableClass implements Callable<Double> {
        @Override
        public Double call() throws Exception {
            return lambdaOperation.doOperation(input);
        }
    }

    public static Integer[] input = new Integer[]{2, 5, 7};

    public static Double result = null;

    public static Runnable runnable = new RunnableClass();

    public static Callable<Double> callable = new CallableClass();

    public static Stream<String> getCardsBySuit(List<String> cards, char suit) {
        List<String> cardsBySuit = new ArrayList<>();
        for (int i = 0; i < cards.toArray().length; i++) {
            if (cards.get(i).charAt(1) == suit) {
                cardsBySuit.add(cards.get(i));
            }
        }
        return cardsBySuit.stream();
    }

    public static Stream<String> getCardsByRank(List<String> cards, char rank) {
        List<String> rankCards = new ArrayList<>();
        for (int i = 0; i < cards.toArray().length; i++) {
            if (cards.get(i).charAt(0) == rank) {
                rankCards.add(cards.get(i));
            }
        }
        return rankCards.stream().distinct().sorted();
    }

    public static Stream<String> getCardsLowerThan(List<String> cards, char rank) {
        List<String> cardsLowerSorted = new ArrayList<>();
        char[] rankOrder = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
        char[] suitOrder = {'S', 'D', 'H', 'C'};
        for (int i = 0; i < rankOrder.length; i++) {
            for (int j = 0; j < suitOrder.length; j++) {
                for (String s : cards) {
                    if (rankOrder[i] == rank) {
                        return cardsLowerSorted.stream().distinct();
                    }
                    if (s.charAt(0) == rankOrder[i] && s.charAt(1) == suitOrder[j]) {
                        cardsLowerSorted.add(s);
                    }
                }
            }
        }
        return null;
    }

    public static Stream<String> getCardsLowerThanSorted(List<String> cards, char rank) {
        List<String> cardsLowerSorted = new ArrayList<>();
        char[] rankOrder = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
        char[] suitOrder = {'S', 'D', 'H', 'C'};
        for (int i = 0; i < suitOrder.length; i++) {
            for (int j = 0; j < rankOrder.length; j++) {
                if (rankOrder[j] == rank) {
                    break;
                }
                for (String s : cards) {
                    if (s.charAt(0) == rankOrder[j] && s.charAt(1) == suitOrder[i]) {
                        cardsLowerSorted.add(s);
                    }
                }
            }
        }
        return cardsLowerSorted.stream().distinct();
    }

    public static String printDeckRanks(Stream<String> cards) {
        List<String> cardsList = cards.toList();
        List<String> cardsLowerSorted = new ArrayList<>();
        List<Integer> mapped = new ArrayList<>();
        char[] rankOrder = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
        char[] suitOrder = {'S', 'D', 'H', 'C'};
        for (int i = 0; i < rankOrder.length; i++) {
            for (int j = 0; j < suitOrder.length; j++) {
                for (String s : cardsList) {
                    if (s.charAt(0) == rankOrder[i] && s.charAt(1) == suitOrder[j]) {
                        cardsLowerSorted.add(s);
                    }
                }
            }
        }
        List<Character> rankOrderList = new ArrayList<>();
        for (char h : rankOrder) {
            rankOrderList.add(h);
        }
        for (String s : cardsLowerSorted) {
            mapped.add(rankOrderList.indexOf(s.charAt(0)) + 1);
        }
        Integer[] mappedArray = new Integer[mapped.size()];
        for (int i = 0; i < mapped.size(); i++) {
            mappedArray[i] = mapped.get(i);
        }
        return lambdaOperation.transformArray(mappedArray);
    }
}
