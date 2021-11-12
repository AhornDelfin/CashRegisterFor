package com.company;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Scanner;


public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static NumberFormat nf = NumberFormat.getInstance();

    public static void main(String[] args) {
        initializeNumberFormat();
        float priceSum = 0;
        float vat = 0;
        float price = 0;


        System.out.println("Wie viele Artikel wollen Sie scannen?");
        int numberOfArticles = Math.round(getNumberInput());
        float[] priceArray = new float[numberOfArticles];

        for (int i = 0; i < numberOfArticles; i++) {
            System.out.println("Bitte geben Sie einen Preis ein:");
            price = getNumberInput();
            priceSum = priceSum + price;
            vat = vat + getVatByCategory(price, getNextString());
            priceArray[i] = price;
        }

        output(priceSum, vat, numberOfArticles, priceArray);
    }

    private static float getVatByCategory (float price, String category) {
        if (category.equals("g")) {
            return price * 0.07f;
        }
        if (category.equals("k")) {
            return price *0.19f;
        }
        else return 0;
    }

    private static float getNumberInput () {
        return scanner.nextFloat();
    }

    private static String getNextString () {
        System.out.println("Bitte wählen Sie die Kategorie aus:\n" +
                "1. 'g' für Grundbedarf\n" +
                "2. 'k' für Konsumgüter");
        String scannerValue = scanner.nextLine();
        scannerValue = scanner.nextLine();
        return scannerValue;
    }

    private static void output(float total, float vat, int numberOfItems, float[] priceArray){
        System.out.println("Der Preis beträgt: " + nf.format(total) + "€");
        System.out.println("Die Mehrwertsteuer beträgt: " + nf.format(vat) + "€");
        System.out.println("Die Anzahl Artikel beträgt: " + numberOfItems);

        for (int i = 0; i < numberOfItems; i++) {
            System.out.println("Artikel " + (i + 1) + ": " + nf.format(priceArray[i]) + "€");
        }
    }

    private static void initializeNumberFormat(){
        nf.setRoundingMode(RoundingMode.HALF_UP);
        nf.setMaximumFractionDigits(2);
    }
}

