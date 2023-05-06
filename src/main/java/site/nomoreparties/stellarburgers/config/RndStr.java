package site.nomoreparties.stellarburgers.config;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class RndStr {
    public String get(final String config, final int length) {
        String conf;
        switch (config) {
            case RndConf.NAME:
                conf = "User_" + RandomStringUtils.randomAlphanumeric(length);
                break;

            case RndConf.EMAIL:
                conf = RandomStringUtils.randomAlphanumeric(length) + "@domain.com";
                break;

            case RndConf.PASS:
                conf = RandomStringUtils.random(length, true, true);
                break;

            default:
                throw new IllegalArgumentException("Unknown argument to perform random string generation!");
        }
        return conf;
    }
}
