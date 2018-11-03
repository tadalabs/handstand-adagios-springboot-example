package org.tadalabs.sample.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "org.tadalabs.sample.data.dynamo.repository")
public class DynamoDBConfig {

    @Value("${amazon.dynamo.endpoint}")
    private String awsDynamoEndpoint;

    /**
     * Table Prefix to be used to separate environments
     */
    @Value("${amazon.aws.environment}")
    private String environment;

    /**
     * The AWS Region to Create the Dynamo Instance in
     */
    @Value("${amazon.aws.region}")
    private String awsRegion;

    @Value("${amazon.aws.secretkey}")
    private String awsSecretKey;

    @Value("${amazon.aws.accesskey}")
    private String awsAccessKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(this.awsDynamoEndpoint, this.awsRegion))
                .build();
    }

    @Bean(name="awsCredentials")
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
                this.awsAccessKey,
                this.awsSecretKey
        );
    }

}
