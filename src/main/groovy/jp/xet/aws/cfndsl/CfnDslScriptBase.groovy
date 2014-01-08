package jp.xet.aws.cfndsl

import groovy.lang.Script


abstract class CfnDslScriptBase extends Script {
	
	// basic
	
	def ref(a) {
		[Ref: a]
	}
	
	def refMap(map, key1, key2) {
		["Fn::FindInMap": [map, key1, key2]]
	}
	
	def join(separator, args) {
		["Fn::Join": [separator, args]]
		
	}
	def getAtt(obj, prop) {
		["Fn::GetAtt": [obj, prop]]
	}
	
	def not(a) {
		["Fn::Not": [a]]
	}
	
	def equals(a, b) {
		["Fn::Equals": [a, b]]
	}
	
	def cfnif(cond, t, f) {
		["Fn::If": [cond, t, f]]
	}
	
	// extra ref
	
	def stackId() {
		ref("AWS::StackId")
	}
	
	def region() {
		ref("AWS::Region")
	}
	
	def noValue() {
		ref("AWS::NoValue")
	}
	
	// shorten
	
	def tag(key, value) {
		[Key: key, Value: value]
	}
	
	def tcp(port, cidr) {
		[ IpProtocol: "tcp",  FromPort: port,  ToPort: port, CidrIp: cidr ]
	}
	
	def udp(port, cidr) {
		[ IpProtocol: "udp",  FromPort: port,  ToPort: port, CidrIp: cidr ]
	}
	
	def icmp(cidr) {
		[ IpProtocol: "icmp",  FromPort: -1,  ToPort: -1, CidrIp: cidr ]
	}
	
	// macro
	
	def keyName() {
		return [
			Description : "Name of an existing EC2 KeyPair to enable SSH access to the instances",
			Type : "String",
			MinLength : 1,
			MaxLength : 64,
			AllowedPattern : "[-_ a-zA-Z0-9]*",
			ConstraintDescription : "can contain only alphanumeric characters, spaces, dashes and underscores."
		]
	}
}
