package jp.xet.aws.cfndsl


class NestedMapDelegate {
	
	def subMap
	def attributePrefix = "@"
	
	NestedMapDelegate(Map subMap) {
		println "New NestedMapDelegate: $subMap"
		this.subMap = subMap
	}
	
	def methodMissing(String name, args) {
		println "methodMissing: ${name}"
		evaluateAll(name, args)
	}
	
	void propertyMissing(String name, value) {
		println "propertyMissing: ${name}"
		evaluateAll(name, value)
	}
	
	def evaluateAll(String name, args) {
		println "evaluateAll: ${name}(${args})"
		if (args instanceof String) {
			println "String: ${name}:${args?.class}"
			evaluateOne(name, args)
		} else if (args instanceof Map) {
			println "Map: ${name}:${args?.class}"
			evaluateOne(name, args)
		} else {
			println "Other: ${name}:${args?.class}"
			for (arg in args) {
				println "Calling evaluateOne ${name}:${arg?.class}"
				evaluateOne(name, arg)
			}
		}
		println "evaluateAll done"
	}
	
	def evaluateOne(String name, arg) {
		println "evaluateOne begin: ${name}"
		// クロージャはネストしたMapとして評価する。
		if (arg instanceof Closure) {
			println "evaluateOne:Closure: ${name}"
			arg.delegate = new NestedMapDelegate(subMap[name])
			arg.resolveStrategy = Closure.DELEGATE_FIRST
			arg.call()
			println "End new"
		}
		// Map形式で指定したものはデフォルトで属性値扱いとして、キーにプレフィックスを付ける。
		// 代入/メソッド形式の値と同一視したい場合は、attributePrefixを空文字にすれば良い
		else if (arg instanceof Map) {
			println "evaluateOne:Map: ${name}"
			def map = subMap[name]
			arg.each { key, value ->
				map[(attributePrefix ?: '') + key] = value
			}
		}
		// その他の場合は、素直に保持する。
		else {
			println "evaluateOne:Other: ${name}"
			println subMap
			subMap[name] = arg
		}
		println "evaluatOne done: ${subMap[name]}"
		println "currentSubMap#${this.hashCode()}: ${subMap}"
	}
}
