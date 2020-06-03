package com.softobt.core.utils;

import com.softobt.core.exceptions.models.RestServiceException;
import com.softobt.core.logger.services.LoggerService;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author aobeitor
 * @since 4/28/20
 */
public class CsvReader {

    public static List<Map> processInputCsvFile(String inputFilePath, final String[] keys, boolean skipFirst)throws Exception{
        List<Map> inputList ;

            int skippedLines = skipFirst ? 1 : 0;
            Function<String, List<String>> addToKeys = e -> {
                List<String> inputs = new ArrayList<>(Arrays.asList(keys));
                inputs.add(e);
                return inputs;
            };

            File inputF = new File(inputFilePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            inputList = br.lines().skip(skippedLines).map(addToKeys).map(mapToItem).collect(Collectors.toList());
            br.close();

        return inputList;
    }

    private static Function<List<String>, Map> mapToItem = (inputs) -> {
        if(inputs.size() <= 1 )
            return null;
        String line = inputs.get(inputs.size()-1);
        String[] csValue = line.split(",");
        LoggerService.info(CsvReader.class,line);
        Map<String, String> mappedItem = new HashMap<>();
        for(int i=0;i<inputs.size()-1;i++){
            mappedItem.put(inputs.get(i), (i>=csValue.length ? "" : csValue[i]));
        }
        return mappedItem;
    };
}
