package com.mycompany.atm_ppg1_updated;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ATM_PPG1_updated {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

                int userPin = 1234;
                int attempts = 3;
                double balance = 2000;
                int cash = 0;
                double interest = 0.00001;
                double depositAmountPESO = 0;

                System.out.print("Welcome to PPG ATM. Please enter your pin to continue. ");

                    while (attempts > 0) { //Pin Validation for start
                    System.out.print("\nEnter Pin: ");
                    String input = scanner.next();

                    try {
                        
                    int inputPin = Integer.parseInt(input); //converts input to int
                    if (inputPin == userPin) //checks if pin is correct

                    break;
                    else {
                        System.out.println("Incorrect pin. " + --attempts + " attempt(s) remaining.");
                }
                    } catch (NumberFormatException e) { //In case if its stringed  
                        System.out.println("Incorrect pin. " + --attempts + " attempt(s) remaining.");
                }
                    if (attempts == 0) { //Logs them out if 0 attempts remaining
                    System.out.println("Too many attempts. Locked for 24 hours.");
                    return;
                }
            }
                try {

                     while (true) {
                        System.out.println("\nMenu:");
                        System.out.println("1 - Change PIN");
                        System.out.println("2 - Withdraw");
                        System.out.println("3 - Deposit");                       
                        System.out.println("4 - Currency");
                        System.out.println("5 - Check Balance");
                        System.out.println("6 - Check Interest");
                        System.out.println("7 - Print Receipt");
                        System.out.println("8 - Exit");
                        System.out.println("");
                        System.out.print("Choose a number to perform action: ");

                        int choice;
                        try {
                            choice = scanner.nextInt();
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            scanner.nextLine();
                            continue;
                        }              
                        switch (choice) {
                            
                case 1: // change pin
            
                    try {
                    System.out.println("Do you want to change your PIN? (1-Yes / 2-No): ");
                    System.out.print("Enter your choice: ");
                    int changePinConfirmation = scanner.nextInt();

                    if (changePinConfirmation == 2) { // User chose not to change the pin
                        break;
                    } 
                    else if (changePinConfirmation != 1) { // Invalid input
                        System.out.println("Invalid choice. Returning to the main menu.");
                        break;
                    }
                    int currentAttempts = 3;
                    boolean isCurrentPinCorrect = false;

                    while (currentAttempts > 0) { // Loop for verifying user's current pin
                        System.out.print("Enter your current pin: ");
                        String currentPinInput = scanner.next();
                        if (currentPinInput.equals(Integer.toString(userPin))) { // Check if input matches user's pin
                            isCurrentPinCorrect = true; // If verified, it continues
                            break;
                        } 
                        else {
                            currentAttempts--;
                            System.out.println("Incorrect current pin. " + currentAttempts + " attempt(s) remaining."); // Attempts (max of 3)
                        }
                    }

                    if (!isCurrentPinCorrect) { // If pin was wrong 3x, logs out
                        System.out.println("Too many attempts. Try again in 24 hours.");
                        return;
                    }

                    int changeAttempts = 3;

                    while (changeAttempts > 0) { // Loop for changing of pins
                        System.out.println("NOTE : Only Numbers are allowed.");
                        System.out.print("Enter new 4 digit pin: ");
                        String newPin = scanner.next();

                        if (newPin.length() != 4) { // Check if the pin = 4 digits
                            System.out.println("Invalid pin. It must be exactly 4 digits.");
                            changeAttempts--;
                            System.out.println("Attempts remaining: " + changeAttempts);
                            if (changeAttempts == 0) {
                                System.out.println("Too many attempts. Try again in 24 hours.");
                                return;
                            }
                            continue;
                        }

                        boolean allDigits = true; // Checks if all pins are only digits in an ATM
                        for (int i = 0; i < newPin.length(); i++) {
                            if (newPin.charAt(i) < '0' || newPin.charAt(i) > '9') {
                                allDigits = false;
                                break;
                            }
                        }

                        if (!allDigits) { // If the new pin has non-digit, it fails the attempt
                            System.out.println("Invalid pin. It must only contain whole numbers.");
                            changeAttempts--;
                            System.out.println("Attempts remaining: " + changeAttempts);
                            if (changeAttempts == 0) {
                                System.out.println("Too many attempts. Try again in 24 hours.");
                                return;
                            }
                        } 
                        else if (newPin.equals(Integer.toString(userPin))) { // Checks if new pin = current pin of user
                            System.out.println("Invalid pin. You cannot change to the same pin.");
                            changeAttempts--;
                            System.out.println("Attempts remaining: " + changeAttempts);
                            if (changeAttempts == 0) {
                                System.out.println("Too many attempts. Try again in 24 hours.");
                                return;
                            }
                        } 
                        else {
                            int confirmAttempts = 3; // set new confirmation attempts
                            boolean isPinMatched = false; //set to false cuz we dont know if pins match

                            while (confirmAttempts > 0) {
                                System.out.print("Re-enter your new 4 digit pin: ");
                                String confirmPin = scanner.next();

                                if (confirmPin.equals(newPin)) { 
                                    isPinMatched = true; //if their pins match = true then it just goes thru confirmation
                                    break;
                                } else {
                                    confirmAttempts--;
                                    System.out.println("The Pins do not match. " + confirmAttempts + " attempt(s) remaining.");
                                }
                            }

                            if (!isPinMatched) { // If confirmation failed 3 times
                                System.out.println("Too many mismatched attempts. Try again in 24 hours.");
                                return;
                            }

                            // Confirmation before changing the pin
                            System.out.println("Are you sure you want to change your pin to " + newPin + "? (1-Yes / 2-No): ");
                            int confirmation = scanner.nextInt();

                            if (confirmation == 1) { // If confirmed, change the PIN
                                userPin = Integer.parseInt(newPin);
                                System.out.println("PIN changed successfully.");
                                break;
                            } 
                            else if (confirmation == 2) { // If canceled, exit to ATM menu
                                System.out.println("Cancelled change pin.");
                                break;
                            } 
                            else { // Invalid input for confirmation
                                System.out.println("Invalid choice. Returning to ATM menu.");
                                break;
                            }
                        }
                    }
                } 
                    catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.nextLine(); // In case of an error
                 }
                 break;
                
                // Withdraw
                case 2: 

                 if (balance > 1000) { // Ensure the balance is above the minimum limit
                    int withdrawAttempts = 3; // Number of attempts only

                     while (withdrawAttempts > 0) { // Loop until attempts are exhausted
                     System.out.println("");
                     System.out.println("MINIMUM 100");
                     System.out.println("MAXIMUM 500,000");
                     System.out.print("Enter an amount to withdraw: ");

                     try {

                         double withdrawAmount = scanner.nextDouble();
                         
                         // Check if the amount is greater than 500,000
                         if (withdrawAmount > 500000) {
                             System.out.println("Invalid amount. You cannot withdraw more than 500,000.");
                             withdrawAttempts--;
                             System.out.println("Attempts remaining: " + withdrawAttempts);
                             
                             continue;
                         }
                         
                         // Check if the amount is at least 100 and a multiple of 100
                         if (withdrawAmount >= 100 && withdrawAmount % 100 == 0) {

                             // Check if the withdrawal would leave the balance below the minimum limit
                             if (balance - withdrawAmount < 1000) {
                                 System.out.println("Withdrawal denied. Your balance cannot go below 1,000.");
                                 withdrawAttempts--;
                                 System.out.println("Attempts remaining: " + withdrawAttempts);

                                 continue;
                             }

                             // For confirmation
                             System.out.print("Are you sure you want to withdraw " + withdrawAmount + "? (1-Yes / 2-No): ");
                             
                             int confirmation = scanner.nextInt();

                             if (confirmation == 1) { // User confirmed withdrawal
                                 balance -= withdrawAmount;
                                 System.out.println("You have successfully withdrawn: " + withdrawAmount);
                                 System.out.println("Your new balance is: " + balance);
                                 break; 
                                 
                             } else if (confirmation == 2) { 
                                 System.out.println("Withdrawal cancelled. Returning to the menu.");
                                 
                                 break; 
                             } else {
                                 System.out.println("Invalid input. Withdrawal cancelled.");
                                 break; 
                             }
                            } else {
                             
                             System.out.println("Invalid amount. Please enter an amount between 100 and 500,000.");
                             withdrawAttempts--; // Decrease attempts for invalid input
                             System.out.println("Attempts remaining: " + withdrawAttempts);
                         }

                     } catch (Exception e) {

                         System.out.println("Invalid input. Please enter a valid amount.");
                         scanner.next(); // Clear the invalid input
                         withdrawAttempts--; // Decrease attempts for invalid input
                         System.out.println("Attempts remaining: " + withdrawAttempts);
                     }
                     
                     // If attempts all used
                     if (withdrawAttempts == 0) {
                         System.out.println("");
                         System.out.println("Too many attempts. Try again in 24 hours.");
                         System.exit(0);
                     }
                 }
             } else {
                 System.out.println("Your balance is currently below the minimum required amount of 1,000.");
             }
             break;
             
                    // Deposit
                   case 3: 
                     try {
                       System.out.print("Deposit an amount: ");
                       depositAmountPESO = scanner.nextInt();  

                     if (depositAmountPESO <= 500) {
                       System.out.println("Invalid amount. Deposit must be greater than 500.");
                     continue;
                     } else if (depositAmountPESO > 500000) {
                        System.out.println("Invalid amount. Deposit must not exceed 500,000 PESOS.");
                     continue;
                 } 
                   } catch (Exception e) {
                      System.out.println("Invalid input. Please enter a valid number.");
                     scanner.next(); 
                     continue; 
                 }
                     break;

                // Currency
                case 4:
                    // Declaration of an exchangeRate
                    double exchangeRateUSD = 58.44;  
                    double exchangeRateEURO = 60.30;
                    double exchangeRateGBP = 72.74;    

                    int currencyChoice; //need for currencyChoice

                        // Display the list of available currencies for the user to choose from
                        System.out.println("1. UNITED STATES 1 USD = 58.44 PESO");
                        System.out.println("2. EUROPEAN UNION 1 EURO = 60.30 PESO");
                        System.out.println("3. UNITED KINGDOM 1 GBP = 72.74 PESO");

                        System.out.print("Choose currency: ");
                        currencyChoice = scanner.nextInt();  
                        try{
                      // Display the exchange rate for the selected currency to PESO
                      if (currencyChoice == 1)  
                        while (true) {
                           try{
                             // Confirmation
                           System.out.print("Are you sure you want to convert your PESO balance in USD " + "(1-Yes / 2-No): ");

                            int confirmation = scanner.nextInt();
                            if (confirmation == 1){            

                            System.out.println("Your balance in USD is  " + String.format("%.2f", balance / exchangeRateUSD));                          
                            System.out.print("Press 1 to exit: ");

                            int backOption = scanner.nextInt();
                            if (backOption == 1) {                           
                                break;

                            } else {
                                System.out.println("Invalid option. Please press 1 to go back to the menu.");
                            }                                         }                                
                            else if (confirmation == 2) {
                                System.out.println("Balance convertion cancelled.");
                                break; 

                            } else {
                                System.out.println("Invalid input. Please enter '1' or '2'.");
                            }
                        } catch (Exception e){
                          System.out.println("Invalid input. Please input a valid number"); scanner.next();     }
                        }
                        if (currencyChoice == 2)  
                         while (true) {
                            try{
                            // Confirmation
                           System.out.printf("Are you sure you want to convert your PESO balance in EURO " + "(1-Yes / 2-No): ");
                            int confirmation = scanner.nextInt();
                            if (confirmation == 1) {            
                            System.out.println("Your balance in EURO is  " + String.format("%.2f", balance / exchangeRateEURO));                         
                            System.out.print("Press 1 to exit: ");

                            int backOption = scanner.nextInt();
                            if (backOption == 1) {
                                break; 

                            } else {
                                System.out.println("Invalid option. Please press 1 to go back to the menu.");
                            }                                         
                        }                           
                            else if (confirmation == 2) {
                                System.out.println("Balance convertion cancelled.");
                                break; 
                            } else {
                                System.out.println("Invalid input. Please enter '1' or '2'.");
                            }
                        } catch (Exception e){
                          System.out.println("Invalid input. Please input a valid number"); scanner.next();     }
                   }
                   if (currencyChoice == 3)  
                        while (true) {
                           try{
                           // Confirmation
                           System.out.printf("Are you sure you want to convert your PESO balance in GBP? " + "(1-Yes / 2-No): ");
                            int confirmation = scanner.nextInt();
                            if (confirmation == 1) {

                           System.out.println("Your balance in USD is  " + String.format("%.2f", balance / exchangeRateGBP));   
                           System.out.print("Press 1 to exit: ");

                       int backOption = scanner.nextInt();
                            if (backOption == 1) {
                                break; 

                            } else {
                                System.out.println("Invalid option. Please press 1 to go back to the menu.");
                            }                                         
                            }
                            
                         else if (confirmation == 2) {
                                System.out.println("Balance convertion cancelled.");
                                break; 
                            } else {
                                System.out.println("Invalid input. Please enter '1' or '2'.");
                            }

                        } catch (Exception e){
                          System.out.println("Invalid input. Please input a valid number"); scanner.next();     }
                   }
                   } catch (Exception e){
                      System.out.println("An unexpected error occurred. Please try again later.");
                   } 
                  break;

                // Check Balance
                case 5: 
                    
                    System.out.println("Your Balance is: " + balance);

                    while (true) {
                        System.out.print("Press 1 to go back to the menu: ");

                        try {
                            // Use nextInt() to wait for the user to press "1"
                            int backOption = scanner.nextInt();
                            if (backOption == 1) {
                                break; 

                            } else {
                                System.out.println("Invalid option. Please press 1 to go back to the menu.");
                            }

                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter the correct number.");
                            scanner.next(); // Clear the invalid input from the scanner
                        }
                    }
                    break;
                    
                // Check Interest
                case 6: 

                    if (balance > 0) {

                            try {
                                System.out.print("Are you sure you want to use the check interest feature? (1-Yes / 2-No): ");
                                int confirmation = scanner.nextInt();

                                switch (confirmation) {
                                    case 1:
                                        int months;
                                        double newBalance = balance + cash;
                                        double product = newBalance * interest;
                                        double month1 = balance + product;
                                        double month2 = month1 * interest + month1;
                                        double month3 = month2 * interest + month2;
                                        double month4 = month3 * interest + month3;
                                        double month5 = month4 * interest + month4;
                                        double month6 = month5 * interest + month5;
                                        double month7 = month6 * interest + month6;
                                        double month8 = month7 * interest + month7;
                                        double month9 = month8 * interest + month8;
                                        double month10 = month9 * interest + month9;
                                        double month11 = month10 * interest + month10;
                                        double month12 = month11 * interest + month11;
                                        double month13 = month12 * interest + month12;
                                        System.out.println("The interest works by increasing your balance by 3% per month");
                                        System.out.println("Enter how many months from 1-12:");
                                        months = scanner.nextInt();

                                        switch (months) {
                                            case 1:
                                                System.out.println("Your Balance after one month: " + month1);
                                                break;

                                            case 2:
                                                System.out.println("Your Balance after two months: " + month2);
                                                break;

                                            case 3:
                                                System.out.println("Your Balance after three months: " + month3);
                                                break;

                                            case 4:
                                                System.out.println("Your Balance after four months: " + month4);
                                                break;

                                            case 5:
                                                System.out.println("Your Balance after five months: " + month5);
                                                break;

                                            case 6:
                                                System.out.println("Your Balance after six months: " + month6);
                                                break;

                                            case 7:
                                                System.out.println("Your Balance after seven months: " + month7);
                                                break;

                                            case 8:
                                                System.out.println("Your Balance after eight months: " + month8);
                                                break;

                                            case 9:
                                                System.out.println("Your Balance after nine months: " + month9);
                                                break;

                                            case 10:
                                                System.out.println("Your Balance after ten months: " + month10);
                                                break;

                                            case 11:
                                                System.out.println("Your Balance after eleven months: " + month11);
                                                break;

                                            case 12:
                                                System.out.println("Your Balance after one year: " + month12);
                                                break;

                                            default:
                                               System.out.println("Choose 1-12 only");
                                                break;
                                        }       
                                        break;

                                    case 2:
                                        System.out.println("Operation Cancelled.");
                                        break;

                                    default:
                                        System.out.println("Invalid input. Operation Cancelled.");
                                        break;
                                }
                        } catch (Exception e) {
                            System.out.println("An error occurred. Returning back to menu.");
                            scanner.nextLine(); // Clear the scanner buffer
                        }
                    } else {
                        System.out.println("Your balance is currently 0");
                    }
                    break;

                // Receipt
                case 7: 

                   try {
                        System.out.print("Do you want to print the receipt? (1-Yes / 2-No): ");
                        int confirmation = scanner.nextInt(); // Change confirmation to use integers

                        if (confirmation == 1) {
                            double Balance = 2000; // Starting Balance
                            double Amount;
                            double Depo;
                            System.out.println("\nChoose Transaction Type:");
                            System.out.println("1 - Withdraw");
                            System.out.println("2 - Deposit");
                            System.out.print("Enter: ");
                            int choices = scanner.nextInt();
                            
                            String datetime = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").format(new Date());
                            System.out.println("");
                            System.out.println("           Receipt               ");
                            System.out.println("");
                            System.out.println("");
                            System.out.println("PPG1.2 - ATM (PASIG CANIOGAN)");
                            System.out.println("Date & Time: " + datetime);
                            System.out.println("");
                            System.out.println("---------------------------------");
                            System.out.println("                ");
                        if (choices == 1) {
                            System.out.print("Withdraw amount: ");
                            Amount = scanner.nextDouble();
                         
                            double remainingBalance = Balance - Amount;
                            
                            System.out.printf("Balance: %.2f%n", Balance);
                            System.out.printf("Withdrawal Amount: %.2f%n", Amount);
                            System.out.printf("Remaining Balance: %.2f%n", remainingBalance);
                            System.out.println("");
                            System.out.println("---------------------------------");
                            System.out.println("");
                            System.out.println("Thank you for using our ATM.");
                            
                            return;
                                } else if (choices == 2) {
                                    System.out.print("Deposit amount: ");
                            Depo = scanner.nextDouble();

                            double remainingBalance = Balance + Depo;

                            System.out.printf("Balance: %.2f%n", Balance);
                            System.out.printf("Deposit Amount: %.2f%n", Depo);
                            System.out.printf("Remaining Balance: %.2f%n", remainingBalance);
                            System.out.println("");
                            System.out.println("---------------------------------");
                            System.out.println("");
                            System.out.println("Thank you for using our ATM.");
                            return;
                            
                        } if (confirmation == 2) {
                            System.out.println("Receipt printing cancelled."); // Message for cancellation
                        } else {
                            System.out.println("Invalid input. Please respond with '1' for yes or '2' for no.");
                        }
                        }
                   }
                            catch (Exception e) {
                        System.out.println("Invalid input.");
                        scanner.next(); // Clear the invalid input
                    }
                    break;

                // Exit
                case 8: 
                    
                    System.out.println("");
                    System.out.println("Thank you for using our ATM. Logged out.");
                    return;
                    
                default:
                    System.out.println("Invalid option. Please select numbers only within the menu.");
            }
        }
    }
                catch (Exception e) {
                    System.out.println("An unexpected error occurred. Please try again later.");
                }
        }
    }