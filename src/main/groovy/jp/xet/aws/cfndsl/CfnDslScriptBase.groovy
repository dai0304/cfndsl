package jp.xet.aws.cfndsl

import groovy.lang.Script


abstract class CfnDslScriptBase extends Script {
	
	// ===================
	// Intrinsic Functions
	// ===================
	
	// http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference-ref.html
	def ref(logicalName) {
		[Ref: logicalName]
	}
	
	// http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference-findinmap.html
	def refMap(mapName, topLevelKey, secondLevelKey) {
		["Fn::FindInMap": [mapName, topLevelKey, secondLevelKey]]
	}

	// http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference-join.html
	def join(delimiter, Object[] values) {
		["Fn::Join": [delimiter, values]]
	}
	
	// http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference-getatt.html
	def getAtt(logicalNameOfResource, attributeName) {
		["Fn::GetAtt": [logicalNameOfResource, attributeName]]
	}
	
	// http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference-base64.html
	def base64(valueToEncode) {
		["Fn:Base64": valueToEncode]
	}

	// http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference-getavailabilityzones.html
	def getAZs(region) {
		["Fn:GetAZs": region]
	}
	
	// http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference-select.html
	def select(index, String[] listOfObjects) {
		["Fn:Select": [index, listOfObjects]]
	}

	// ===================
	// Condition Functions
	// ===================
	
	def condition(conditionName) {
		[Condition: conditionName]
	}
	
	// http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference-conditions.html#d0e31795
	def fnAnd(Object[] conditions) {
		["Fn::And": conditions]
	}
	
	// http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference-conditions.html#d0e32092
	def fnOr(Object[] conditions) {
		["Fn::Or": conditions]
	}
	
	// http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference-conditions.html#d0e32028
	def fnNot(condition) {
		["Fn::Not": [condition]]
	}
	
	// http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference-conditions.html#d0e31903
	def fnIf(cond, t, f) {
		["Fn::If": [cond, t, f]]
	}
	
	// http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference-conditions.html#d0e31853
	def fnEquals(a, b) {
		["Fn::Equals": [a, b]]
	}
	
	// =====================
	// Pseudo Parameters Ref
	// =====================
	
	def stackId() {
		ref("AWS::StackId")
	}
	
	def stackname() {
		ref("AWS::StackName")
	}

	def region() {
		ref("AWS::Region")
	}
	
	def accountId() {
		ref("AWS::AccountId")
	}
	
	def notificationARNs() {
		ref("AWS::NotificationARNs")
	}

	def noValue() {
		ref("AWS::NoValue")
	}
	
	// ============
	// DSL Shortcut
	// ============
	
	def tag(key, value) {
		[Key: key, Value: value]
	}
	
	def tcp(port, cidr) {
		[IpProtocol: "tcp",  FromPort: port,  ToPort: port, CidrIp: cidr]
	}
	
	def udp(port, cidr) {
		[IpProtocol: "udp",  FromPort: port,  ToPort: port, CidrIp: cidr]
	}
	
	def icmp(cidr) {
		[IpProtocol: "icmp",  FromPort: -1,  ToPort: -1, CidrIp: cidr]
	}
	
	// ==========
	// DSL Preset
	// ==========
	
	def keyName() {
		return {
			Description = "Name of an existing EC2 KeyPair to enable SSH access to the instances"
			Type = "String"
			MinLength = 1
			MaxLength = 64
			AllowedPattern = "[-_ a-zA-Z0-9]*"
			ConstraintDescription = "can contain only alphanumeric characters, spaces, dashes and underscores."
		}
	}
	
	def availabilityZone() {
		return {
			// TODO
		}
	}
	
	def cidr() {
		return {
			// TODO
		}
	}
	
	def amiId() {
		return {
			// TODO
		}
	}
	
	def instanceType() {
		return {
			// TODO
		}
	}
	
	def amazinLinuxAmi() {
		return {
			// TODO
		}
	}
}
