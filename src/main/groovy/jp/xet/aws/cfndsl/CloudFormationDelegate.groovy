package jp.xet.aws.cfndsl


class CloudFormationDelegate {
	
	void parameters(Closure cl) {
		// TODO
	}
	
	void mappings(Closure cl) {
		// TODO
	}
	
	void conditions(Closure cl) {
		// TODO
	}
	
	void resources(Closure cl) {
		cl.delegate = new ResourcesDelegate()
		cl.resolveStrategy = Closure.DELEGATE_FIRST
		cl()
	}
	
	void outputs(Closure cl) {
		// TODO
	}
	
}
