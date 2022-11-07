package controller;

import dao.WalletDao;
import dao.WalletDaoImpl;
import dto.Wallet;
import exception.WalletException;
import service.WalletService;
import service.WalletServiceImpl;

import java.util.Scanner;

public class Controller {

	public static void main(String[] args) {
		
		WalletService ws = new WalletServiceImpl();
		WalletDao wc = new WalletDaoImpl() ;


		try {
			System.out.println("Adding new account");
			Wallet x,y,z;
			x=new Wallet(1 ,"Lavanya",100.00,"123");
			y=new Wallet(2,"Vedha",200.00,"1234");
			z=new Wallet(3,"Kannan",300.00,"12345");
			System.out.println(ws.registerWallet(x));
			System.out.println(ws.registerWallet(y));
			System.out.println(ws.registerWallet(z));
			System.out.println("Login Account");
			System.out.println(ws.login(1,"123"));
			System.out.println("Show Account Balance");
			System.out.println(ws.showWalletBalance(1));
			System.out.println("Add account balance");
			System.out.println(ws.addFundsToWallet(1,200.00));
			System.out.println("Transfer Fund");
			System.out.println(ws.fundTransfer(1,3,50.00));
			System.out.println("Delete Account");
			System.out.println(ws.unRegisterWallet(2,"1234"));


			Scanner sc=new Scanner(System.in);
			System.out.println("1.FundTransfer 2.Delete 3.Login");
			int a=sc.nextInt();
			switch(a){
				case 1:System.out.println("Exception for fund transfer");
				       ws.fundTransfer(14,3,50.00);
				    break;
				case 2:System.out.println("Exception for password");
				       ws.unRegisterWallet(17,"1417");
					break;
				case 3:System.out.println("Exception for password wrong while logins");
					   ws.unRegisterWallet(1,"1417");
					break;

			}

		} catch (WalletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
