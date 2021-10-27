package com.solvd.banksystem;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordCounter {

    public static void countWordsInFile(String fromFilePath, String toFilePath) throws IOException {
        File fromFile = new File(fromFilePath);
        String fileString = FileUtils.readFileToString(fromFile, StandardCharsets.UTF_8);
        fileString = StringUtils.lowerCase(fileString);
        fileString = StringUtils.replaceChars(fileString, "1234567890,.;!?:\n\r\"", " ");
        List<String> words = Arrays.asList(StringUtils.substringsBetween(fileString, " ", " "));
        Map<String, Integer> wordsCount = new HashMap<>();
        for(String word : words) {
            if(wordsCount.containsKey(word)) {
                wordsCount.put(word, wordsCount.get(word) + 1);
            } else  {
                wordsCount.put(word, 1);
            }
        }
        File toFile = new File(toFilePath);
        toFile.createNewFile();
        FileUtils.writeLines(toFile,
                wordsCount.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(Collectors.toList()));
    }

}
