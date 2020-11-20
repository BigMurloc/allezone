package pl.edu.pjwstk.jaz;

import liquibase.pro.packaged.A;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

//TODO refactor

@RestController
public class AverageController {
    @GetMapping("/auth0/average")
    public AverageResult getAverage(@RequestParam(value = "numbers", required = false) String numbers){

        if (numbers == null || numbers.equals(""))
            return new AverageResult("Please put parameters.");

        double result;
        String[] numbersAsArray = numbers.split(",");

        try {
            result = calculateAverage(numbersAsArray);
        }
        catch(NumberFormatException e){
            return new AverageResult("Incorrect parameters.");
        }

        BigDecimal formattedResult = BigDecimal.valueOf(result);
        formattedResult = formattedResult.setScale(2, RoundingMode.HALF_UP);

        return new AverageResult("Average equals: "+formattedResult.stripTrailingZeros().toPlainString());
    }

    public double calculateAverage(String[] numbersAsArray){
        double sum = 0;
        for(String s : numbersAsArray){
            sum += Double.parseDouble(s);
        }
        return sum/numbersAsArray.length;
    }

}
