package pl.edu.pjwstk.jaz;

import liquibase.pro.packaged.A;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@RestController
public class AverageController {
    @GetMapping("average")
    public AverageResult getAverage(@RequestParam(value = "numbers", required = false) String numbers){
        // numbers...
        //jesli brak argumentow
        if (numbers == null || numbers.equals(""))
            return new AverageResult("Please put parameters.");
        //jezeli argumenty zostaly podane to wsadz do tablicy wg przecinkow
        String[] numbersAsArray = numbers.split(",");
        //policz sume liczb
        double sum = 0;
        try {
            for (int i = 0; i < numbersAsArray.length; i++) {
                sum += Double.parseDouble(numbersAsArray[i]);
            }
        }
        //w razie zle podanych argumentow (np. litery)
        catch(NumberFormatException e){
            System.out.println(e);
            return new AverageResult("Incorrect parameters.");
        }
        //srednia
        double result = sum / numbersAsArray.length;
        BigDecimal formattedResult = BigDecimal.valueOf(result);
        //zaokraglenie do dwoch miejsc po przecinku
        formattedResult = formattedResult.setScale(2, RoundingMode.HALF_UP);
        String message = "Average equals: ";
        message += formattedResult.stripTrailingZeros().toPlainString();
        return new AverageResult(message);
    }
}
