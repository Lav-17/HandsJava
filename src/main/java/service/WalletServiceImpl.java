package service;

import dao.WalletDao;
import dao.WalletDaoImpl;
import dto.Wallet;
import exception.WalletException;

import java.util.Objects;

public class WalletServiceImpl implements WalletService {

	private WalletDao walletRepository = new WalletDaoImpl();
	
	
	public Wallet registerWallet(Wallet newWallet) throws WalletException {
		
		return this.walletRepository.addWallet(newWallet);
		
	}

	public Boolean login(Integer walletId, String password) throws WalletException {
		// TODO Auto-generated method stub
		try{
			Wallet getaccount = walletRepository.getWalletById(walletId);
			if(Objects.equals(getaccount.getPassword(),password)){
				return true;
			}else throw new WalletException(super.toString());
		}
		catch(WalletException e){
         throw new WalletException("Password not match");
		}
	}

	public Double addFundsToWallet(Integer walletId, Double amount) throws WalletException {
		// TODO Auto-generated method stub
		Double currbalc;
		Double addbalc;
		Double finalbalc;
		Wallet getamount = walletRepository.getWalletById(walletId);
		currbalc = getamount.getBalance();
		addbalc=currbalc+amount;
		getamount.setBalance(addbalc);
		this.walletRepository.updateWallet(getamount);
		Wallet updatedacc = walletRepository.getWalletById(walletId);
		finalbalc=updatedacc.getBalance();
		return finalbalc;
	}

	public Double showWalletBalance(Integer walletId) throws WalletException {
		// TODO Auto-generated method stub
		Wallet getamount = walletRepository.getWalletById(walletId);
		Double balc;
		balc=getamount.getBalance();

		return balc;
	}

	public Boolean fundTransfer(Integer fromId, Integer toId, Double amount) throws WalletException {
		// TODO Auto-generated method stub
		Boolean sc=true;
		Wallet fromacc  = walletRepository.getWalletById(fromId);
		Wallet toacc = walletRepository.getWalletById(toId);
		Double frombal;
		Double tobal;

		frombal = fromacc.getBalance();

		if(frombal>=amount) {
			frombal =- amount;
			fromacc.setBalance(frombal);
			this.walletRepository.updateWallet(fromacc);
			tobal = amount + toacc.getBalance();
			toacc.setBalance(tobal);
			System.out.println(this.walletRepository.updateWallet(toacc));

			return sc;
		}else {
			throw new WalletException("Insufficient Balance");
		}
	}



	public Wallet unRegisterWallet(Integer walletId, String password) throws WalletException {
		// TODO Auto-generated method stub
		try {
			Wallet getaccount = walletRepository.getWalletById(walletId);
			if (Objects.equals(getaccount.getPassword(), password)) {
				this.walletRepository.deleteWalletById(walletId);
			} else {
				throw new WalletException(super.toString());
			}
		} catch (WalletException e) {
			throw new WalletException("Password not match");
		}
       return null;

	}

}
