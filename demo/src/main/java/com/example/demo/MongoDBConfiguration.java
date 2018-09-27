package com.example.demo;


import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.bol.crypt.CryptVault;
import com.bol.secure.ReflectionEncryptionEventListener;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
public class MongoDBConfiguration extends AbstractMongoConfiguration {

	@Value("${spring.data.mongodb.uri}")
	private String mongodburi;
	
	
    // normally you would use @Value to wire a property here
    private static final byte[] secretKey = Base64.getDecoder().decode("hqHKBLV83LpCqzKpf8OvutbCs+O5wX5BPu3btWpEvXA=");
    private static final byte[] oldKey = Base64.getDecoder().decode("cUzurmCcL+K252XDJhhWI/A/+wxYXLgIm678bwsE2QM=");

    
    
    @Override
    protected String getDatabaseName() {
        return "test";
    }

	@Override
	public MongoClient mongoClient() {
		// TODO Auto-generated method stub
		return  new MongoClient(new MongoClientURI(mongodburi));
	}


    @Bean
    public CryptVault cryptVault() {
        return new CryptVault()
                .with256BitAesCbcPkcs5PaddingAnd128BitSaltKey(0, oldKey)
                .with256BitAesCbcPkcs5PaddingAnd128BitSaltKey(1, secretKey)
                // can be omitted if it's the highest version
                .withDefaultKeyVersion(1);
    }

    @Bean
    public ReflectionEncryptionEventListener encryptionEventListener(CryptVault cryptVault) {
        return new ReflectionEncryptionEventListener(cryptVault);
    }





}