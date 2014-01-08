package jp.xet.aws.cfndsl

import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import org.codehaus.groovy.control.CompilerConfiguration

class CfnDsl {
	
	static void main(String[] args) {
		def json = new CfnDsl().runCfnDsl(new File(args.length == 0 ? "default.cfn" : args[0]))
		println JsonOutput.prettyPrint(json)
	}
	
	def runCfnDsl(File dsl) {
		def config = new CompilerConfiguration();
		config.scriptBaseClass = CfnDslScriptBase.class.name
		def shell = new GroovyShell(config);
		
		def parsed = new NodeBuilder().root(shell.evaluate("{ -> ${dsl.text} }"))
		buildJson(parsed)
	}
	
	def buildJson(root) {
		def cfn = root.CloudFormation
		def builder = new JsonBuilder()
		
		builder {
			"AWSTemplateFormatVersion" (cfn.AWSTemplateFormatVersion?.text() ?: "2010-09-09")
			if (cfn.Description?.text()) {
				"Description" (cfn.Description?.text())
			}
			// TODO
		}
		
		builder.toString()
	}
}
