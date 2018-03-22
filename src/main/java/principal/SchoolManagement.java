/**
 * Package principal => package for functional classes 
 */
package principal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import org.postgresql.util.PSQLException;

import java.sql.SQLException;

import abstractClass.Character;
import abstractClass.DaoCharacter;
import daoClass.DaoStudent;
import daoClass.DaoTeacher;

/**
 * @author Frederick PAREJA first version 
 * management student and teacher
 * this is the main class
 */
public class SchoolManagement {
	public static Scanner input = new Scanner(System.in);
	private static int menuChoice = -1;

	/**
	 * method to catch the user input with a message to introduce the question
	 * version for a String / version for an integer / version for a date
	 * 
	 * @return
	 */
	public static String getUserInputString(String message) {
		System.out.print(message + " : ");
		String result = input.nextLine();
		return result;
	}

	public static int getUserInputInt(String message) {
		System.out.print(message + " : ");
		int result = input.nextInt();
		input.nextLine();
		return result;
	}
	
	/**
	 * method to catch the user input about date of birth there is a check about the
	 * format of date but actually there is missing a check on conformity of date
	 * @param message
	 * @return
	 */
	public static Date getUserInputDate(String message) {
		Date result = null;
		do {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String answer = getUserInputString(message);
				result = sdf.parse(answer);
			} catch (ParseException e) {
				System.err.println(
						"the format of your date is not correct, please try again with the format YYYY-MM-DD.");
			}
		} while (result == null);
		return result;
	}

	/**
	 * Main menu to manage a teacher or a student or the display of lists
	 * @param args
	 * @throws SQLException
	 * @throws PSQLException
	 */
	public static void main(String[] args) throws PSQLException, SQLException {
		// TODO Auto-generated method stub
		do {
			try {
				System.out.println("Welcome to the management School application.");
				System.out.println("What would you want to do ?");
				System.out.println("1 - Manage a teacher.");
				System.out.println("2 - Manage a student.");
				System.out.println("3 - Display a list.");
				System.out.println("0 - Quit the application.");

				menuChoice = getUserInputInt("Your choice");
				switch (menuChoice) {
				case 1:
					Character teacher = new Teacher();
					management(teacher);
					break;
				case 2:
					Character student = new Student();
					management(student);
					break;
				case 3:
					boolean displayChoice = false;
					do {
						try {
							System.out.println("What is the list that you want to see ?");
							System.out.println("1 - Teachers list.");
							System.out.println("2 - Students list.");
							System.out.println("3 - the list of classes.");
							System.out.println("4 - the list of teaches.");
							System.out.println("5 - Return to the main menu.");
							System.out.println("0 - Quit the application.");

							int result = getUserInputInt("Your choice");
							if (result == 0) {
								displayChoice = true;
								System.out.println("Thank you and Goodbye!");
								menuChoice = 0;
							}
							if (result == 5) {
								displayChoice = true;
								menuChoice = -1;
							}
							if (result >= 1 && result <= 4) {
								see(result);
							}
							if (result < 0 || result > 5) {
								System.err.println("Your choice is not correct, please try again.");
							}
						} catch (InputMismatchException e) {
							System.err.println("Your choice is not a valid character, please try again.");
							input.nextLine();
						}
					} while (!displayChoice);
					break;
				case 0:
					System.out.println("Thank you and Goodbye !");
					break;
				default:
					System.err.println("Your choice is not correct, please try again.");
					break;
				}

			} catch (InputMismatchException e) {
				System.err.println("Your choice is not a valid character, please try again.");
				input.nextLine();
			}
		} while (menuChoice != 0);
	}
	

	/**
	 * method to display the common menu between student and teacher
	 * manage the add, update and delete information
	 * an option can to manage the link (class of student or teach of teacher) but actually is not made
	 * @param subject
	 *            to define the subject of menu
	 */
	private static void managementMenu(String subject) {
		System.out.println("What would you want to do ?");
		System.out.println("1 - Add a " + subject + ".");
		System.out.println("2 - Update a " + subject + ".");
		System.out.println("3 - Delete a " + subject + ".");
		String str = "";
		if (subject.equals("Teacher")) {
			str = "teach's subject";
		}
		if (subject.equals("Student")) {
			str = "class";
		}
		System.out.println("4 - Manage a " + str + ".");
		System.out.println("5 - Return to the main menu.");
		System.out.println("0 - Quit the application.");
	}

	private static void management(Character character) throws PSQLException, SQLException {
		String result = character.getClass().getSimpleName();

		int userChoice = -1;
		do {
			try {
				managementMenu(result);
				userChoice = getUserInputInt("Your choice");
				switch (userChoice) {
				case 1:
					character.prepareCharacter();
					character.prepareLink();
					character.save();
					break;
				case 2:
					List<Character> list = character.display();
					boolean answer = false;
					if (list.size() == 0) {
						System.out.println("The list is actually empty.\nPlease add at least one element.");
						answer = true;
					}
					for (Character person : list) {
						System.out.println(person);
					}
					while (!answer) {
						try {
							int characterUpdate = getUserInputInt(
									"Indicate the key of the person that you want to modify");
							boolean verify = false;
							for (int i = 0; i != list.size(); i++) {
								int controlKey = list.get(i).getId();
								if (controlKey == characterUpdate) {
									verify = true;
									characterUpdate = i;
								}
							}
							if (verify) {
								character = list.get(characterUpdate);
								character.update();
								answer = true;
							} else {
								System.err.println("Your choice is not correct, please try again.");
							}
						} catch (InputMismatchException e) {
							System.err.println("Your choice is not a valid character, please try again.");
							input.nextLine();
						}
					}
					break;
				case 3:
					List<Character> deleteList = character.display();
					boolean deleteAnswer = false;
					if (deleteList.size() == 0) {
						System.out.println("The list is actually empty.\nPlease add at least one element.");
						deleteAnswer = true;
					}
					for (Character person : deleteList) {
						System.out.println(person);
					}
					while (!deleteAnswer) {
						try {
							int characterDelete = getUserInputInt(
									"Indicate the key of the person that you want to delete");
							boolean verify = false;
							for (int i = 0; i != deleteList.size(); i++) {
								int controlKey = deleteList.get(i).getId();
								if (controlKey == characterDelete) {
									verify = true;
									characterDelete = i;
								}
							}
							if (verify) {
								character = deleteList.get(characterDelete);
								character.delete();
								deleteAnswer = true;
							} else {
								System.err.println("Your choice is not correct, please try again.");
							}
						} catch (InputMismatchException e) {
							System.err.println("Your choice is not a valid character, please try again.");
							input.nextLine();
						}
					}
					break;
				case 4:
					System.out.println("option in progress, pleasy try more later.");
					break;
				case 5:
					menuChoice = -1;
					break;
				case 0:
					menuChoice = 0;
					System.out.println("Thank you and Goodbye!");
					break;
				default:
					System.err.println("Your choice is not correct, please make an another.");
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Your choice is not a valid character, please try again.");
				input.nextLine();
			}
		} while (userChoice != 0 && userChoice != 5);
	}
	
	/**
	 * method to display a map
	 * use to display classes or teaches
	 * @param map
	 */

	public static void displayMap(Map<String, Integer> map) {
		List<Entry<String, Integer>> set = new ArrayList<>(map.entrySet());
		if (set.size() == 0) {
			System.out.println("The list is actually empty.\nPlease add at least one element.");
		}
		for (Entry<String, Integer> entry : set) {
			System.out.println(entry.getKey() + " ==> key : " + entry.getValue());
		}
	}
	
	/**
	 * method to manage the display menu about the main menu
	 * @param choix
	 */

	private static void see(int choix) {
		if (choix == 1) {
			Character teacher = new Teacher();
			List<Character> list = teacher.display();
			if (list.size() == 0) {
				System.out.println("The list is actually empty.\nPlease add at least one element.");
			}
			for (Character person : list) {
				System.out.println(person);
			}
		}
		if (choix == 2) {
			Character student = new Student();
			List<Character> list = student.display();
			if (list.size() == 0) {
				System.out.println("The list is actually empty.\nPlease add at least one element.");
			}
			for (Character person : list) {
				System.out.println(person);
			}
		}
		if (choix == 3) {
			Map<String, Integer> map = new TreeMap<>();
			DaoCharacter daoStudent = new DaoStudent();
			map = daoStudent.link();
			displayMap(map);
		}
		if (choix == 4) {
			Map<String, Integer> map = new TreeMap<>();
			DaoCharacter daoTeacher = new DaoTeacher();
			map = daoTeacher.link();
			displayMap(map);
		}
	}

}
