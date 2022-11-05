package BankingDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Dbc.Connections;

public class BankingSysImp implements BankingSys {
	boolean flag = false;
	int count = 0;
	int act = 0;

	public int getAct() {
		return act;
	}

	public void setAct(int act) {
		this.act = act;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean getFlag() {
		return flag;
	}

	public void checkAct(String em, int ps) {
		count++;

		Connection con = Connections.getConnection();
		try {
			PreparedStatement act = con.prepareStatement("select * from Accountant");
			ResultSet rs = act.executeQuery();

			while (rs.next()) {

				String email = rs.getString("email");

				int password = rs.getInt("pass");

				if (em.equalsIgnoreCase(email) && password == ps) {
					flag = true;
					System.out.println(" Admin logged in successfully ");
					System.out.println();

				} else
					System.out.println("invalid credentials!!");
				System.out.println("Please try again");
			}
		} catch (SQLException ex) {
			ex.getMessage();
		}

	}

	@Override
	public void insertCus(int cusId, String name, String cusEmail, int pass, int bal) {
		System.out.println(count);
		if (flag) {
			Connection con = Connections.getConnection();
			try {
				PreparedStatement act = con.prepareStatement("insert into customer values(?,?,?,?,?)");
				act.setInt(1, cusId);
				act.setString(2, name);
				act.setString(3, cusEmail);
				act.setInt(4, pass);
				act.setInt(5, bal);

				int x = act.executeUpdate();
				System.out.println(x + "record added successfully");
				System.out.println();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else
			System.out.println("kindly login first!!!!");
		System.out.println();

	}

	@Override
	public void deleteRecord(int actNo) {

		Connection con = Connections.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement("delete from customer where actNo=?");
			ps.setInt(1, actNo);
			int x = ps.executeUpdate();
			System.out.println(x + " record deleted sucessfully");
			System.out.println();
		} catch (SQLException e) {
			System.out.println("Record not found");
			System.out.println();
		}

	}

	@Override
	public void displayPerticular(int actNo) {
		Connection con = Connections.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from customer where actNo=?");
			ps.setInt(1, actNo);
			ResultSet rs = ps.executeQuery();
			display(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void display(ResultSet rs) {
		if (rs == null)
			System.out.println("No record found");
		System.out.println();
		try {
			while (rs.next()) {
				System.out.println("customer account number- " + rs.getInt("actNo"));
				System.out.println();
				System.out.println("customer name- " + rs.getString("name"));
				System.out.println();
				System.out.println("customer email- " + rs.getString("email"));
				System.out.println();
				System.out.println("customer password- " + rs.getInt("pass"));
				System.out.println();
				System.out.println("customer balance - " + rs.getString("bal"));
				System.out.println();
				System.out.print("==========================================");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void displayAll() {

		Connection con = Connections.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from customer ");
			ResultSet rs = ps.executeQuery();
			display(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void editName(int actNo, String name) {

		Connection con = Connections.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("update customer set name=? where actNo=? ");
			ps.setString(1, name);
			ps.setInt(2, actNo);

			int x = ps.executeUpdate();
			if (x > 0)
				System.out.println(x + " row affeted");

		} catch (SQLException e) {
			System.out.println("not updated try again");
		}
	}

	@Override
	public void editEmail(int actNo, String email) {
		Connection con = Connections.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("update customer set email=? where actNo=? ");
			ps.setString(1, email);
			ps.setInt(2, actNo);
			int x = ps.executeUpdate();
			if (x > 0)
				System.out.println(x + " row affeted");

		} catch (SQLException e) {
			System.out.println("not updated try again");
		}

	}

	@Override
	public void editpass(int actNo, int pass) {
		Connection con = Connections.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("update customer set pass=? where actNo=? ");
			ps.setInt(1, pass);
			ps.setInt(2, actNo);
			int x = ps.executeUpdate();
			if (x > 0)
				System.out.println(x + " row affeted");

		} catch (SQLException e) {
			System.out.println("not updated try again");
		}

	}

	@Override
	public void deposit(int actNo, int rsa) {
		Connection con = Connections.getConnection();
		try {
			PreparedStatement ps1 = con.prepareStatement("select * from customer where actNo=? ");
			ps1.setInt(1, actNo);

			ResultSet rs = ps1.executeQuery();
			int rup = rsa;
			while (rs.next()) {
				rup = rs.getInt("bal");

			}

			int finalrs = rup + rsa;
			PreparedStatement ps = con.prepareStatement("update customer set bal=? where ActNo=?");
			ps.setInt(1, finalrs);
			ps.setInt(2, actNo);
			int x = ps.executeUpdate();
			if (x > 0) {
				pre(actNo, rup + rsa);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("not updated try again");
		}
	}

	public void pre(int act, int rs) {

		Connection con = Connections.getConnection();
		try {
			PreparedStatement his = con.prepareStatement("insert into data values(?,?)");
			his.setInt(1, act);
			his.setInt(2, rs);
			int x = his.executeUpdate();
			if (x > 0)
				System.out.println("Transaction successful!!");

		} catch (SQLException e) {

		}

	}

	@Override
	public void withdrwal(int actNo, int rsa) {
		Connection con = Connections.getConnection();
		try {
			PreparedStatement ps1 = con.prepareStatement("select * from customer where actNo=? ");
			ps1.setInt(1, actNo);

			ResultSet rs = ps1.executeQuery();
			int rup = 0;
			while (rs.next()) {
				rup = rs.getInt("bal");

			}
			if (rup < rsa) {
				System.out.println("Insufficient balance----");
				return;
			}
			int finalrs = rup - rsa;

			PreparedStatement ps = con.prepareStatement("update customer set bal=? where ActNo=?");
			ps.setInt(1, finalrs);
			ps.setInt(2, actNo);
			int x = ps.executeUpdate();
			if (x > 0) {
				pre(actNo, rup - rsa);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println();
			System.out.println("not updated try again");
			System.out.println();
		}

	}

	@Override
	public void loginCus(String em, int ps) {
		count++;

		Connection con = Connections.getConnection();
		try {
			PreparedStatement act = con.prepareStatement("select * from customer");
			ResultSet rs = act.executeQuery();
			while (rs.next()) {

				String email = rs.getString("email");

				int password = rs.getInt("pass");

				if (em.equalsIgnoreCase(email) && password == ps) {
					flag = true;
					this.act = rs.getInt("actNo");
					System.out.println("login sucessful from customer id ");
					System.out.println();
					return;

				}

			}
			if (this.count == 0)
				System.out.println("invalid credentials!!");
		} catch (SQLException ex) {
			ex.getMessage();

		}
	}

	@Override
	public void showhis(int act) {
		Connection con = Connections.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement("select * from data where custAct=?");
			ps.setInt(1, act);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(
						"Account number -" + rs.getInt("custAct") + " Transaction amount-" + rs.getInt("balance"));
			}

		} catch (SQLException e) {
			System.out.println("No record found----");
		}

	}
}
