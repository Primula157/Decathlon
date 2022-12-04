package com;

import com.athlete.Athlete;
import com.event.Event;
import com.event.TrackEvent;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.time.LocalTime;
import java.util.*;

public class FileParser implements Closeable {
    private BufferedReader bufferedReader;
    private String inputFilePath;
    private String outputFilePath;


    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    public FileParser(String inputFileName, String outputFileName) throws IOException {
        inputFilePath = System.getProperty("user.dir") + "\\" + inputFileName;
        outputFilePath = System.getProperty("user.dir") + "\\" + outputFileName;
        bufferedReader = new BufferedReader(new FileReader(inputFilePath));
    }

    // обработка содержимого файла
    public Map<Athlete, Double[]> getCompetitionResults() throws IOException {
        String allCompetitionResultsFromFile = readFile();
        String[] competitionResultsLineByLine = allCompetitionResultsFromFile.split("\n");
        Map<Athlete, Double[]> competitionResults = new HashMap<>();

        for (int i = 0; i < competitionResultsLineByLine.length; i++) {
            String[] line = competitionResultsLineByLine[i].split(";");
            String athleteName = line[0];
            Athlete athlete = new Athlete(athleteName);
            Double[] allPerformancesByAthlete = new Double[line.length - 1];
            List<Event> events = Decathlon.getEvents();
            for (int j = 1; j < line.length; j++) {
                allPerformancesByAthlete[j - 1] = getPerformanceByTheAthlete(line[j], events.get(j - 1) instanceof TrackEvent);
            }
            competitionResults.put(athlete, allPerformancesByAthlete);
        }
        return competitionResults;
    }

    // проверка корректности данных из файла и преобразование минут в секунды
    private Double getPerformanceByTheAthlete(String performanceByTheAthlete, boolean isTrackEvent) {
        Double result = 0d;

        try {
            result = Double.parseDouble(performanceByTheAthlete);
            if (result < 0) return 0d;
        } catch (NumberFormatException e) {
            if (isTrackEvent) {
                result = getSeconds(performanceByTheAthlete);
            } else {
                e.printStackTrace();
            }
        }

        return result;
    }

    // перевод минут в секунды
    private Double getSeconds(String performanceByTheAthlete) {
        String[] time = performanceByTheAthlete.split("[:. ]");
        int nanoSeconds = 0;
        int seconds = 0;
        int minutes = 0;

        try {
            nanoSeconds = Integer.parseInt(time[time.length - 1]);
            seconds = time.length - 2 >= 0 ? Integer.parseInt(time[time.length - 2]) : 0;
            minutes = time.length - 3 >= 0 ? Integer.parseInt(time[time.length - 3]) : 0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        LocalTime localTime = LocalTime.of
                (0, minutes, seconds, nanoSeconds);
        return (double) localTime.toSecondOfDay() + (localTime.getNano() * 0.01);
    }

    private String readFile() throws IOException {
        StringBuilder result = new StringBuilder();

        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            result.append(line).append("\n");
        }

        return result.toString().trim();
    }

    public void saveDataToFile(ScoreTable scoreTable) throws JAXBException {
        File outputFile = new File(outputFilePath);
        JAXBContext context = JAXBContext.newInstance(ScoreTable.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(scoreTable, outputFile);
    }

    public void close() throws IOException {
        bufferedReader.close();
    }
}
