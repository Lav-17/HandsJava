package Service;

import dto.Wallet;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import service.WalletService;
import service.WalletServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WalletServiceTest {
    static WalletService ws = new WalletServiceImpl();

    @Test
    @Order(1)
    public void Register()  {
        try{
            Wallet a = new Wallet(110, "vedha", 1000.0, "098");
            Wallet b = new Wallet(109, "shree", 100.0, "678");

            assertEquals(a,ws.registerWallet(a));
            assertEquals(b,ws.registerWallet(b));
        }catch (Exception e){
            e.printStackTrace();

        }

    }

    @Test
    @Order(2)
    public void Login()  {
        try{
            assertTrue(ws.login(111,"12345"));

        }catch (Exception e){
            e.printStackTrace();

        }

    }

    @Test
    @Order(3)
    public void AddFundsToWallets()  {
        try{
            assertEquals(1010,ws.addFundsToWallet(110,10.0));

        }catch (Exception e){
            e.printStackTrace();

        }

    }

    @Test
    @Order(4)
    public void ShowWalletBalance()  {
        try{
            assertEquals(1010,ws.showWalletBalance(110));

        }catch (Exception e){
            e.printStackTrace();

        }

    }

    @Test
    @Order(5)
    public void FundTransfer()  {
        try{

            assertEquals(true, ws.fundTransfer(110, 111, 10.0));


        }catch (Exception e){
            e.printStackTrace();

        }

    }





    @Test
    @Order(6)
    public void UnRegisterWallet()  {
        try{

            assertEquals(null,ws.unRegisterWallet(110,"098"));
            assertEquals(null,ws.unRegisterWallet(109,"678"));


        }catch (Exception e){
            e.printStackTrace();

        }

    }

}