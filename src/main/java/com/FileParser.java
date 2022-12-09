package com;

import com.athlete.Athlete;
import com.event.Event;
import com.event.TrackEvent;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.Closeable;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.time.LocalTime;


public class FileParser implements Closeable {
    private final BufferedReader bufferedReader;
    private final String outputFilePath;

    public FileParser(String inputFileName, String outputFileName) throws IOException {
        String inputFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + inputFileName;
        outputFilePath = System.getProperty("user.dir") + "\\" + outputFileName;
        bufferedReader = new BufferedReader(new FileReader(inputFilePath));
    }

    // обработка содержимого файла
    public Map<Athlete, Double[]> getCompetitionResults() throws IOException {
        String allCompetitionResultsFromFile = readFile();
        String[] competitionResultsLineByLine = allCompetitionResultsFromFile.split("\n");
        Map<Athlete, Double[]> competitionResults = new HashMap<>();

        for (String s : competitionResultsLineByLine) {
            String[] line = s.split(";");
            String athleteName = line[0];
            Athlete athlete = new Athlete(athleteName);
            Double[] allPerformancesByAthlete = new Double[line.length - 1];
            List<Event> events = Decathlon.getEvents();
            for (int j = 1; j < line.length; j++) {
                allPerformancesByAthlete[j - 1] = getPerformanceByTheAthlete(line[j]);
            }
            competitionResults.put(athlete, allPerformancesByAthlete);
        }
        return competitionResults;
    }

    // проверка корректности данных из файла и преобразование минут в секунды
    private Double getPerformanceByTheAthlete(String performanceByTheAthlete) {
        double result = 0d;

        try {
            if (performanceByTheAthlete.contains(":")) {
                result = getSeconds(performanceByTheAthlete);
            } else {

                result = Double.parseDouble(performanceByTheAthlete);
                if (result < 0) return 0d;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return result;
    }

    // перевод минут в секунды
    private Double getSeconds(String performanceByTheAthlete) throws NumberFormatException {
        String[] time = performanceByTheAthlete.split("[:. ]");
        int nanoSeconds = 0;
        int seconds = 0;
        int minutes = 0;

        nanoSeconds = Integer.parseInt(time[time.length - 1]);
        seconds = time.length - 2 >= 0 ? Integer.parseInt(time[time.length - 2]) : 0;
        minutes = time.length - 3 >= 0 ? Integer.parseInt(time[time.length - 3]) : 0;

        LocalTime localTime = LocalTime.of
                (0, minutes, seconds, nanoSeconds);
        return localTime.toSecondOfDay() + (localTime.getNano() * 0.01);
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
