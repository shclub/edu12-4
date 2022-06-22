package com.kt.edu.thirdproject.common.config;


import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class JasyptConfig{

    private static final String JASYPT_KEY        = "education";
    private static final String ALGORITHM       = "PBEWithMD5AndDES";

    @Bean("jasyptStringEncryptor")
    public StandardPBEStringEncryptor StringEnc() {
        StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig conf = new EnvironmentPBEConfig();
        conf.setPassword(JASYPT_KEY);
        conf.setAlgorithm(ALGORITHM);
        enc.setConfig(conf);
        return enc;
    }
}
