package GUI.tests;

import GUI.GUI;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		Test test = new Test();

		test.buttOpts();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public void buttOpts() {

		List<String> buttOpts = new ArrayList<>();

		for (int i = 0; i < 5; i++)
			buttOpts.add("Yay!" + i);

		String[] optionsForButts = new String[buttOpts.size()];
		for (int i = 0; i < buttOpts.size(); i++)
			optionsForButts[i] = buttOpts.get(i);

		GUI.getUserButtonPressed("", optionsForButts);

	}
}