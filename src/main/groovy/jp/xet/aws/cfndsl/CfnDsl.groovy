package jp.xet.aws.cfndsl

import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import org.codehaus.groovy.control.CompilerConfiguration

class CfnDsl {
	
	static void main(String[] args) {
		if (args) {
			def json = new CfnDsl().runCfnDsl(new File(args[0]))
			println JsonOutput.prettyPrint(json)
		} else {
			println "Usage: cfndsl <script>"
		}
	}
	
	def runCfnDsl(File dsl) {
		def parsed = new NestedMapBuilder().parse(dsl.text)
		buildJson(parsed)
	}
	
	def buildJson(cfn) {
		println cfn
		
		def builder = new JsonBuilder()
		
		builder {
			"AWSTemplateFormatVersion" (cfn.AWSTemplateFormatVersion ?: "2010-09-09")
			if (cfn.Description) {
				"Description" (cfn.Description)
			}
			// TODO
		}
		
		builder.toString()
	}
}
