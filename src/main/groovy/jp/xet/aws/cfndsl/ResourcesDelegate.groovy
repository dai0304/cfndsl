package jp.xet.aws.cfndsl


class ResourcesDelegate {
	
	
	def methodMissing(String name, args) {
		// throw new MissingMethodException(name, delegate, args)
		println "$name ( $args )"
	}
//	void ec2Vpc(String name, String dependsOn = null, Closure cl) {
//		// TODO
//	}
//	
//	void ec2InternetGateway(String name, String dependsOn = null, Closure cl = null) {
//		// TODO
//	}
//	
//	void ec2VPCGatewayAttachment(String name, String dependsOn = null, Closure cl) {
//		// TODO
//	}
//	
//	void ec2Subnet(String name, String dependsOn = null, Closure cl) {
//		// TODO
//	}
//	
//	void ec2EIP(String name, String dependsOn = null, Closure cl) {
//		// TODO
//	}
//	
//	void ec2Instance(String name, String dependsOn = null, Closure cl) {
//		// TODO
//	}
}
