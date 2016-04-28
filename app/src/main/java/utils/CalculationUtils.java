package utils;

import com.google.common.math.DoubleMath;

import java.util.Locale;

/**
 * Created by Pau on 23/04/2016.
 */
public class CalculationUtils {

    /**
     * This function returns the probability of occurrence of a studied topic in a examen where
     * you have @total topics to studied you had studied @studied topics and you can choose from a
     * group of @taken different topics.
     * @param total Number of total topics that have the exam
     * @param taken Number of topics which you can choose from
     * @param studied Number of topics you study for the exam
     * @return probability of occurrence of a studied topic (by percentage) as string
     */
    public static String probabilityPercentage(Integer total, Integer taken, Integer studied){
        double percentage = 0.0;
        percentage = 100 - 100 * (DoubleMath.factorial(total-studied) * DoubleMath.factorial(total-taken)
                / ((DoubleMath.factorial(total) * DoubleMath.factorial(total-studied-taken))));
        return String.format(Locale.getDefault(),"%.2f",percentage);
    }
}
