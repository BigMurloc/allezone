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

        String[] numbersAsArray = numbers.split(",");

        double sum = 0;
        try {
            for (String s : numbersAsArray) {
                sum += Double.parseDouble(s);
            }
        }
        catch(NumberFormatException e){
            return new AverageResult("Incorrect parameters.");
        }
        double result = sum / numbersAsArray.length;
        BigDecimal formattedResult = BigDecimal.valueOf(result);
        formattedResult = formattedResult.setScale(2, RoundingMode.HALF_UP);
        String message = "Average equals: ";
        message += formattedResult.stripTrailingZeros().toPlainString();
        return new AverageResult(message);
    }
}
