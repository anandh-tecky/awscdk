package com.myorg;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.StackProps;

import java.util.Arrays;

public class AwscdkApp {
    public static void main(final String[] args) {
        App app = new App();

        new AwscdkStack(app, "AwscdkStack", StackProps.builder()
                // If you don't specify 'env', this stack will be environment-agnostic.
                // Account/Region-dependent features and context lookups will not work,
                // but a single synthesized template can be deployed anywhere.

                // Uncomment the next block to specialize this stack for the AWS Account
                // and Region that are implied by the current CLI configuration.
                /*
                .env(Environment.builder()
                        .account(System.getenv("CDK_DEFAULT_ACCOUNT"))
                        .region(System.getenv("CDK_DEFAULT_REGION"))
                        .build())
                */

                // Uncomment the next block if you know exactly what Account and Region you
                // want to deploy the stack to.
                
                .env(Environment.builder()
                        .account("428996551227")
                        .region("ap-south-1")
                        .build())
                

                // For more information, see https://docs.aws.amazon.com/cdk/latest/guide/environments.html
                .build());

        app.synth();
    }
}
