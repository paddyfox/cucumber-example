package uk.kainos.seleniumframework.testDataHelpers;

import net.bytebuddy.utility.RandomString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public abstract class TestData {

    public static String generateRandomString(int length) {
        RandomString randomString = new RandomString(length);

        return randomString.nextString();
    }

    public String generateRandomNumber() {
        int minimumNumber = 100000000;
        int maximumNumber = 999999999;

        Random r = new Random();

        return String.valueOf(r.nextInt((maximumNumber - minimumNumber) + 1) + minimumNumber);
    }

    public static String generateRandomDateOfBirth(int minAge, int maxAge) {
        Random rand = new Random();
        int age = rand.nextInt(maxAge - minAge) + minAge;

        LocalDate now = LocalDate.now();
        LocalDate dateOfBirth = now
                .minusYears(age);

        return String.valueOf(dateOfBirth);
    }

    public static String generateRandomDateInPast(Integer daysAgo) {

        return LocalDate.now().minusDays(daysAgo).format(DateTimeFormatter.ISO_DATE);
    }
}
