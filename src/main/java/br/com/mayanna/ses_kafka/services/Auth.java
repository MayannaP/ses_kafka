package br.com.mayanna.ses_kafka.services;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

public class Auth {
	
	public SesClient clientProvider() {
		AwsCredentialsProvider credentialsProvider = new AwsCredentialsProvider() {
	        @Override
	        public AwsCredentials resolveCredentials() {
	            return new AwsCredentials() {
	                @Override
	                public String accessKeyId() {
	                    return System.getenv("AWS_ACCESS_KEY");
	                }
	
	                @Override
	                public String secretAccessKey() {
	                    return System.getenv("AWS_SECRET_KEY");
	                }
	            };
	        }
	    };
	
	    Region region = Region.US_EAST_1;
	    SesClient client = SesClient.builder()
	            .credentialsProvider(credentialsProvider)
	            .region(region)
	            .build();
	    
	    return client;
	}
}
