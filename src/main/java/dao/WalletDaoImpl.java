package dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


import dto.Wallet;
import exception.WalletException;

public class WalletDaoImpl implements WalletDao {

	// Create collection to store the Wallet information.
	Map<Integer, Wallet> wallets = new HashMap<>();

	public Wallet addWallet(Wallet newWallet) throws WalletException {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mywallet", "root", "");
			 PreparedStatement pt = con.prepareStatement("Insert into payment values(?,?,?,? )")){

			pt.setInt(1,newWallet.getId());
			pt.setString(2,newWallet.getName());
			pt.setDouble(3,newWallet.getBalance());
			pt.setString(4,newWallet.getPassword());
			pt.execute();
			System.out.println("Added successfully");

		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return newWallet;
	}

	public Wallet getWalletById(Integer walletId) throws WalletException {
		// TODO Auto-generated method stub

		Wallet a = null;

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mywallet", "root", "");
			 PreparedStatement pt = con.prepareStatement("SELECT * FROM payment WHERE id=?")) {
			pt.setInt(1, walletId);

			ResultSet rs = pt.executeQuery();
			rs.next();

			a = new Wallet(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4));
			//System.out.println("get successfully");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return a;
	}

	public Wallet updateWallet(Wallet updateWallet) throws WalletException {
		Wallet b = null;
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mywallet", "root", "");
			 PreparedStatement pt = con.prepareStatement("update payment set abalance=? where id=?");
			 PreparedStatement ptd = con.prepareStatement("SELECT * FROM payment WHERE id=?")){
			pt.setDouble(1,updateWallet.getBalance());
			pt.setInt(2,updateWallet.getId());
			pt.executeUpdate();
			System.out.println("updated");
			/*----get updated wallet--->*/
			ptd.setInt(1,updateWallet.getId() );

			ResultSet rs = ptd.executeQuery();
			rs.next();

			b = new Wallet(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4));

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return b;
	}

	public Wallet deleteWalletById(Integer walletID) throws WalletException {
		// TODO Auto-generated method stub
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mywallet", "root", "");
			 PreparedStatement pt = con.prepareStatement("Delete  FROM payment WHERE id=?")) {
			pt.setInt(1, walletID);
			pt.execute();
			System.out.println("Deleted successfully");


		} catch (SQLException e) {
			throw new RuntimeException(e);


		}
		return null;
	}

}