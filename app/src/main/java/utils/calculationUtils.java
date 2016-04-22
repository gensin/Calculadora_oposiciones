package utils;

import com.google.common.math.DoubleMath;

/**
 * Created by Pau on 23/04/2016.
 */
public class calculationUtils {

    /**
     * This function returns the probability of occurrence of a studied topic in a examen where
     * you have @total topics to studied you had studied @studied topics and you can choose from a
     * group of @taken different topics.
     * @param total
     * @param taken
     * @param studied
     * @return probability of occurrence of a studied topic (by percentage)
     */
    public static Double probabilityPercentage(Integer total, Integer taken, Integer studied){
        Double percentage = 0.0;
        percentage = 100 - 100 * (DoubleMath.factorial(total-studied) * DoubleMath.factorial(total-taken)
                / ((DoubleMath.factorial(total) * DoubleMath.factorial(total-studied-taken))));
        return percentage;
    }
}
