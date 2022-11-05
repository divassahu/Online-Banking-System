package Accounts;

import java.util.Scanner;

import BankingDao.BankingSysImp;

public class Access {
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BankingSysImp ban = new BankingSysImp();

		System.out.println("Login as ");
		System.out.println("1.Admin");
		System.out.println("2.Customer");

		int res = sc.nextInt();

		if (res == 2) {

			System.out.println("Enter customer's email id---");
			String email = sc.next();
			System.out.println("Enter passWord---");
			int pass = sc.nextInt();
			ban.loginCus(email, pass);

			while (ban.getFlag()) {
				System.out.println("Enter your choice----");
				System.out.println("1.Transfer money to another account");
				System.out.println("2.Show history");
				System.out.println("3.Exit");

				int choice = sc.nextInt();

				if (choice == 1) {
					System.out.println("Enter the account no. in which you want to transfer the money");
					int act2 = sc.nextInt();
					System.out.println("Enter the amount");
					int mon = sc.nextInt();
					ban.withdrwal(ban.getAct(), mon);
					ban.deposit(act2, mon);

				} else if (choice == 2) {
					ban.showhis(ban.getAct());
				} else if (choice == 3)
					break;
				else
					System.out.println("Invalid choice please select valid choice");

			}

		} else if (res == 1) {
			System.out.println("Enter Admin email id-");
			String email = sc.next();
			System.out.println("Enter Admin passWord-");
			int pass = sc.nextInt();
			ban.checkAct(email, pass);

			while (ban.getFlag()) {
				System.out.println("Enter your choice----");
				System.out.println("1.Add new customer");
				System.out.println("2.Edit existing account detail");
				System.out.println("3.Remove existing account");
				System.out.println("4.View particular account details by account number");
				System.out.println("5.View all customer's accounts");
				System.out.println("6. deposit or withdrawl");
				System.out.println("7.Exit");

				int choice = sc.nextInt();

				if (choice == 7)
					break;
				else if (choice == 6) {
					System.out.println("1.for deposit");
					System.out.println("2.for withdrawl");

					int ch = sc.nextInt();

					if (ch == 1) {
						System.out.println("Enter account number---");
						int act = sc.nextInt();
						System.out.println("Enter amount for deposit-------");
						int rupees = sc.nextInt();
						ban.deposit(act, rupees);
					}
					if (ch == 2) {
						System.out.println("Enter account number---");
						int act = sc.nextInt();
						System.out.println("Enter amount for withdrawl-------");
						int rupees = sc.nextInt();
						ban.withdrwal(act, rupees);
					}

				} else if (choice == 1) {
					System.out.println("Enter account number");
					int act = sc.nextInt();
					System.out.println("Enter customer name-- ");
					sc.nextLine();
					String name = sc.nextLine();

					System.out.println("Enter customer email ---");
					String emai = sc.next();
					System.out.println("Set customer passWord-");
					int ps = sc.nextInt();
					System.out.println("Set customer balance----");
					int bal = sc.nextInt();
					ban.insertCus(act, name, emai, ps, bal);

				} else if (choice == 2) {

					System.out.println("Give the account number of customer which you want to edit");
					int actNo = sc.nextInt();
					System.out.println("Select what you want to change-----");

					System.out.println("1.Customer name");

					System.out.println("2.Customer email----");


					System.out.println("3.Customer passWord");

					int option = sc.nextInt();
					if (option == 1) {
						System.out.println("Enter new name====");
						sc.nextLine();
						String naam = sc.nextLine();
						ban.editName(actNo, naam);
					} else if (option == 2) {
						System.out.println("Enter new email====");
						sc.nextLine();
						String naam = sc.nextLine();
						ban.editEmail(actNo, naam);

					} else if (option == 3) {
						System.out.println("Enter new passWord===");

						int naam = sc.nextInt();
						ban.editpass(actNo, naam);

					} else
						System.out.println("Please enter correct choice");

				} else if (choice == 3) {
					System.out.println("Enter the account number of customer which you want to delete");
					int act = sc.nextInt();
					ban.deleteRecord(act);

				} else if (choice == 4) {
					System.out.println("Enter the account number of customer which you want to see the detail");
					int act = sc.nextInt();
					ban.displayPerticular(act);

				} else if (choice == 5)
					ban.displayAll();

			}
			System.out.println("======Thanks for visiting=======");
		} else
			System.out.println("Invalid choice");
	}

}
