package jp.xet.aws.cfndsl

import org.codehaus.groovy.control.CompilerConfiguration


class NestedMapBuilder {
	private nestedMap = newMap()
	
	// あらゆるキーに対してデフォルトで空マップを返すMapを返す。
	// 再帰で無限階層をサポートする。
	private static newMap() {
		[:].withDefault { newMap() }
	}
	
	def parse(String scriptText) {
		def config = new CompilerConfiguration();
		config.scriptBaseClass = CfnDslScriptBase.class.name
		def shell = new GroovyShell(config);
		Script script = shell.parse(scriptText)
		parse(script)
	}
	
	def parse(Script script) {
		script.metaClass = createEMC(script.class) { ExpandoMetaClass emc ->
			emc.CloudFormation = { Closure closure ->
				closure.delegate = new NestedMapDelegate(nestedMap)
				closure.resolveStrategy = Closure.DELEGATE_FIRST
				closure.call()
			}
		}
		script.run()
		return nestedMap
	}
	
	private static createEMC(Class clazz, Closure closure) {
		ExpandoMetaClass emc = new ExpandoMetaClass(clazz, false)
		closure(emc)
		emc.initialize()
		return emc
	}
}
