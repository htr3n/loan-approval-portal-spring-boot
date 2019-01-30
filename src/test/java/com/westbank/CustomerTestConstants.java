package com.westbank;

import com.westbank.entity.EstateType;
import com.westbank.entity.MaritalStatus;
import com.westbank.entity.ResidenceType;

import java.util.Date;
import java.util.Random;

public interface CustomerTestConstants {

    double LOANAMOUNT = new Random().nextDouble();
    String LOANREASON = "Buy a house";
    Integer LOANTERM = new Random().nextInt();
    double INTERESTRATE = new Random().nextDouble();
    Date STARTDATE = new Date();
    double TOTALPURCHASEPRICE = new Random().nextDouble();
    double PERSONALCAPITALCONTRIBUTION = new Random().nextDouble();
    ResidenceType RESIDENCETYPE = ResidenceType.MAIN_HOUSE;
    EstateType ESTATETYPE = EstateType.HOUSE;
    String ESTATEADDRESS = "KcNNtK";
    String BORROWERTITLE = "Mrs.";
    String BORROWERFIRSTNAME = "Alice";
    String BORROWERLASTNAME = "Done";
    String BORROWERPERSONALID = "7690520855";
    Date BORROWERDATEOFBIRTH = new Date();
    String BORROWERSTREET = "King St.";
    String BORROWERCITY = "Melbourne";
    String BORROWERSTATE = "VIC";
    String BORROWERZIPCODE = "1234";
    String BORROWERCOUNTRY = "Australia";
    String BORROWERPHONE = "0987654321";
    String BORROWERMOBILEPHONE = "1234567890";
    String BORROWEREMAIL = "borrower@test.com";
    MaritalStatus BORROWERMARITALSTATUS = MaritalStatus.MARRIED;
    Integer BORROWERNUMBEROFCHILDREN = new Random().nextInt();
    String BORROWEROCCUPATION = "Accountant";
    Integer BORROWERLENGTHOFSERVICE = new Random().nextInt();
    double BORROWERINCOME = new Random().nextDouble();
    String NEWPIN = "spWBJfXZe3ax";
    boolean HASCOBORROWER = true;
    String COBORROWERTITLE = "Mr.";
    String COBORROWERFIRSTNAME = "Joe";
    String COBORROWERLASTNAME = "Miller";
    String COBORROWEREMAIL = "coborrower@test.com";
    String COBORROWERPERSONALID = "2771599797";
    Date COBORROWERDATEOFBIRTH = new Date();
    String COBORROWEROCCUPATION = "Lawyer";
    Integer COBORROWERLENGTHOFSERVICE = new Random().nextInt();
    double COBORROWERINCOME = new Random().nextDouble();

    //String ALICE_CUSTOMER_ID = Generator.generateCustomerId();
    String ALICE_FIRSTNAME = "Alice";
    String ALICE_LASTNAME = "Smith";
    String ALICE_EMAIL = "alice@test.com";
    String ALICE_PIN = "uXpwjgRJ8Atj";

    //String BOB_CUSTOMER_ID = Generator.generateCustomerId();
    String BOB_FIRSTNAME = "Bob";
    String BOB_LASTNAME = "Miller";
    String BOB_EMAIL = "bob@miller.com";
    String BOB_PIN = "TN6h7EGgN8xm";

    int ONE_CUSTOMER = 1;
    int TWO_CUSTOMERS = 2;

}
