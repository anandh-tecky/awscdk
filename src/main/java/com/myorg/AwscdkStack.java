package com.myorg;

import software.amazon.awscdk.core.CfnOutput;
import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.ec2.Instance;
import software.amazon.awscdk.services.ec2.InstanceClass;
import software.amazon.awscdk.services.ec2.InstanceSize;
import software.amazon.awscdk.services.ec2.InstanceType;
import software.amazon.awscdk.services.ec2.MachineImage;
import software.amazon.awscdk.services.ec2.Peer;
import software.amazon.awscdk.services.ec2.Port;
import software.amazon.awscdk.services.ec2.SecurityGroup;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.iam.Role;
import software.amazon.awscdk.services.iam.ServicePrincipal;

public class AwscdkStack extends Stack {
    public AwscdkStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public AwscdkStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        // The code that defines your stack goes here
        Vpc vpc = Vpc.Builder.create(this, "MyCDKVPC").build();
	    
	    Role role=Role.Builder.create(this, "mycdk-instance-role")
	    		.assumedBy(new ServicePrincipal("ec2.amazonaws.com"))
	    		.build();
	    
	    SecurityGroup sg=SecurityGroup.Builder.create(this, "mycdksg")
	    					.allowAllOutbound(true)
	    					.vpc(vpc)
	    					.build();
	    sg.addIngressRule(Peer.anyIpv4(), Port.tcp(80));
	    sg.addIngressRule(Peer.anyIpv4(), Port.tcp(22));
	    
	    Instance instance=Instance.Builder.create(this, "mycdkinstance")
	    					.vpc(vpc)
	    					.role(role)
	    					.securityGroup(sg)
	    					.instanceName("mycdkinstance")
	    					.instanceType(InstanceType.of(InstanceClass.BURSTABLE2, InstanceSize.MICRO))
	    					.machineImage(MachineImage.latestAmazonLinux())
	    					.keyName("EC2 Tutorial")
	    					.build();
	    CfnOutput cfOutput=CfnOutput.Builder.create(this, "MyCDKCFNOUT")
	    		             .value(getRegion())
	    		             .build();
    }
}
