package jp.xet.aws.cfndsl

class CfnDsl {
	
	static void main(String[] args) {
		new CfnDsl().runCfnDsl(new File(args.length == 0 ? "default.cfn" : args[0]))
	}
	
	void runCfnDsl(File dsl) {
		Script dslScript = new GroovyShell().parse(dsl.text)
		
		dslScript.metaClass = createEMC(dslScript.class, { ExpandoMetaClass emc ->
			emc.cloudFormation = { Closure cl ->
				cl.delegate = new CloudFormationDelegate()
				cl.resolveStrategy = Closure.DELEGATE_FIRST
				cl()
			}
		})
		dslScript.run()
		
		// TODO
	}
	
	ExpandoMetaClass createEMC(Class clazz, Closure cl) {
		ExpandoMetaClass emc = new ExpandoMetaClass(clazz, false)
		cl(emc)
		emc.initialize()
		emc
	}
}
