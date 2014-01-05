package jp.xet.aws.cfndsl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStreamReader;

import groovy.json.JsonOutput;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import com.google.common.base.Strings;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;

public class TemplateBuilderTest {
	
	@Test
	public void test() throws Exception {
		TemplateBuilder sut = new TemplateBuilder();
		String actual = JsonOutput.prettyPrint(sut.toString());
		assertThat(actual, is(CharStreams.toString(new InputStreamReader(
				TemplateBuilderTest.class.getResourceAsStream("/fixture_simple-ec2.json")))));
	}
	
}
