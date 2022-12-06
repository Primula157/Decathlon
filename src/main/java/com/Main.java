package com;

public class Main {
    public static void main(String[] args) {
        String inputFileName = "results.csv";
        String outputFileName = "data.xml";
        Decathlon decathlon = new Decathlon();

        try (FileParser fileParser = new FileParser(inputFileName, outputFileName)) {
            decathlon.calculateTheResultsOfCompetition(fileParser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
