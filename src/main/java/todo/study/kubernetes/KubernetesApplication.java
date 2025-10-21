package todo.study.kubernetes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class KubernetesApplication {

	public static void main(String[] args) {
        SpringApplication.run(KubernetesApplication.class, args);
        promptForTwoIntsAndPrintRandom();

	}

	/**
	 * Prompts the user to enter two integers (re-prompts on invalid input),
	 * then prints a random integer between them (inclusive).*
	 */
	public static void promptForTwoIntsAndPrintRandom() {
		Scanner scanner = new Scanner(System.in);
		Integer first = null;
		Integer second = null;

		while (first == null) {
			System.out.print("Enter the first integer: ");
			String line = scanner.nextLine();
			try {
				first = Integer.parseInt(line.trim());
			} catch (NumberFormatException e) {
				System.out.println("That's not a valid integer. Please try again.");
			}
		}

		while (second == null) {
			System.out.print("Enter the second integer: ");
			String line = scanner.nextLine();
			try {
				second = Integer.parseInt(line.trim());
			} catch (NumberFormatException e) {
				System.out.println("That's not a valid integer. Please try again.");
			}
		}

		int min = Math.min(first, second);
		int max = Math.max(first, second);

		int random;
		if (min == max) {
			random = min;
		} else {
			// ThreadLocalRandom.nextInt(origin, bound) is [origin, bound)
			// so we pass max + 1 to make it inclusive
			random = ThreadLocalRandom.current().nextInt(min, max + 1);
		}

		System.out.println("Random number between " + min + " and " + max + ": " + random);
		scanner.close();
	}

}