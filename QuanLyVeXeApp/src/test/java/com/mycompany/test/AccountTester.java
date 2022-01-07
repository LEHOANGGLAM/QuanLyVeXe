/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test;

import com.mycompany.pojo.Account;
import com.mycompany.pojo.VeXe;
import com.mycompany.services.AccountService;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 *
 * @author dell
 */
public class AccountTester {
     private static final AccountService acService = new AccountService();
     
     @Test
    public void testUniqueId() throws SQLException,NoSuchAlgorithmException{
         List<Account> list = new ArrayList<>();
         list.addAll(acService.getAccounts());
         List<String> kq = new ArrayList<>();
         for (Account c : list) {
             String id = c.getTaiKhoan();
             kq.add(id);
         }
         Set<String> kq2 = new HashSet<>(kq);
         Assertions.assertEquals(kq.size(), kq2.size());
    }
    
    @Test
    public void testGetAccount() throws SQLException{
        Account ac = acService.getAccount("hoanglam");
        Assertions.assertEquals(ac.getTaiKhoan(),"hoanglam");
      
    }
    
    @Test
    public void testGetInValidAccount() throws SQLException{
        Account ac = acService.getAccount("1");
        Assertions.assertNull(ac);
      
    }
    
    // Test Insert /Update /Delete Account
    Account accountTest = new Account("TestAccount", "123456", "1337", 1);
    
    @Test
    public void testInsertAccount(){
        try{
            acService.insertAccount(accountTest);
            Account tempAccount = acService.getAccount(accountTest.getTaiKhoan());
            Assertions.assertEquals(accountTest.getTaiKhoan(), tempAccount.getTaiKhoan());
        } catch(SQLException ex){
            Logger.getLogger(AccountTester.class.getName()).log(Level.SEVERE, null, ex);

        }   
    }
    
    @Test
    public void testUpdateAccount(){
        try{
          Account testUpdateAcc = accountTest;
          testUpdateAcc.setTaiKhoan("accCuiBap");
          acService.updateAccount(testUpdateAcc);
          Assertions.assertNotEquals(accountTest.getTaiKhoan(), testUpdateAcc.getTaiKhoan());
 
        } catch(SQLException ex){
            Logger.getLogger(AccountTester.class.getName()).log(Level.SEVERE, null, ex);

        }   
    }
    
    @Test
    public void testDeleteAccount(){
        try{
          acService.deleteAccount(accountTest);
          
          Assertions.assertNull(acService.getAccount("accCuiBap"));
 
        } catch(SQLException ex){
            Logger.getLogger(AccountTester.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
}
