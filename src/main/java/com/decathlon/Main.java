package com.decathlon;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + "results.csv";
        String outputFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + "data.xml";
        Decathlon decathlon = new Decathlon();

        try (FileParser fileParser = new FileParser(inputFilePath, outputFilePath)) {
            decathlon.calculateTheResultsOfCompetition(fileParser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
