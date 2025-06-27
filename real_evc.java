import java.util.Scanner;
// Import Scanner si aan u akhrino xogta user-ka laga soo geliyo keyboard-ka

public class real_evc {
    private static double balance = 500.0;
    private static double bankBalance = 1000.0;
    private static int pin = 1234;
    private static String language = "Somali";
    private static final String ZIP_CODE = "*770#";
    private static Scanner scanner = new Scanner(System.in);
    private static String[] lastThreeActions = new String[3];
    private static int actionIndex = 0;

    public static void main(String[] args) {
        System.out.print("Fadlan garac *770#: ");
        String code = scanner.nextLine();

        if (code.equals(ZIP_CODE)) { // Haddii code-ka saxda ah
            System.out.print("Fadlan Geli Pin-kaaga: ");
            int enteredPin = Integer.parseInt(scanner.nextLine()); // Akhri pin-ka
            if (enteredPin == pin) {   // Haddii pin-ku sax yahay
                displayMainMenu();     // Ku tusi menu-ga ugu weyn
            } else {
                System.out.println("Pin-ka waa khalad."); // Pin khalad ah
            }
        } else {
            System.out.println("Code-ka aad gelisay waa khalad."); // Code khalad ah
        }
    }

    private static void displayMainMenu() {
        // Menu-ga ugu weyn ee barnaamijka oo soo bandhigaya doorashooyinka
        System.out.println("\n[EVC PLUS]");
        System.out.println("1: Itus Haraaga");
        System.out.println("2: Ku shubo");
        System.out.println("3: Bixi Biil");
        System.out.println("4: Uwareeji");
        System.out.println("5: Warbixin Kooban");
        System.out.println("6: Salaam Bank");
        System.out.println("7: Maareynta");
        System.out.println("8: Bill Payment");
        System.out.println("9: Tabaruc / Sadaqo");
        System.out.println("0: Kabax");
        System.out.print("Dooro: ");
        String choice = scanner.nextLine();

        // Xulashada doorashada ku saleysan input-ka user-ka
        switch (choice) {
            case "1": itusHaraaga(); break;              // Tus haraaga Evc Plus
            case "2": kuShuboMenu(); break;
            case "3": bixiBiilMenu(); break;
            case "4": processTransaction("Uwareeji"); break;
            case "5": warbixinKoobanMenu(); break;         // Warbixin kooban oo ku saabsan dhaqdhaqaaqyada
            case "6": salaamBankMenu(); break;
            case "7": maareyntaMenu(); break;
            case "8": billPaymentMenu(); break;
            case "9": processTransaction("Tabaruc / Sadaqo"); break;
            case "0": System.out.println("Macsalaama!"); System.exit(0); break; // Kabax
            default: System.out.println("Doorasho aan sax ahayn."); displayMainMenu(); // Doorasho khalad ah, ku celi menu-ga
        }
    }

    private static void itusHaraaga() {
        System.out.println("Haraagaagu waa $" + balance);
        addAction("Itus Haraaga → $" + balance);
        returnToMainMenu();
    }

    private static void kuShuboMenu() {

        System.out.println("\nKU SHUBO");
        System.out.println("1: Ku shub Airtime");
        System.out.println("2: Ugu shub Airtime");
        System.out.println("3: MIFI Packages");
        System.out.println("4: Ku shubo Internet");
        System.out.println("5: Ugu shub qof kale");
        System.out.print("Dooro: ");
        String choice = scanner.nextLine();

        // Doorashada ku saleysan input-ka
        switch (choice) {
            case "1": processTransaction("Ku shub Airtime"); break;
            case "2": processTransaction("Ugu shub Airtime"); break;
            case "3": processTransaction("MIFI Packages"); break;
            case "4": processTransaction("Ku shubo Internet"); break;
            case "5": processTransaction("Ugu shub qof kale"); break;
            default: System.out.println("Doorasho aan sax ahayn.");
        }
        returnToMainMenu();
    }

    private static void bixiBiilMenu() {
        // Menu bixinta biilasha
        System.out.println("\nBIXI BIIL");
        System.out.println("1: Post paid");
        System.out.println("2: Ku iibso");
        System.out.print("Dooro: ");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            // Hoosaadka Post paid
            System.out.println("1: Ogow biilka");
            System.out.println("2: Bixi biil");
            System.out.println("3: Ka bixi biil");
            System.out.print("Dooro: ");
            String sub = scanner.nextLine();
            if (sub.equals("1")) {
                // Tusaale ogow biilka oo random ah $100-$200
                double biil = 100 + Math.random() * 100;
                System.out.printf("Biilkaagu waa: $%.2f\n", biil);
                addAction("Ogow biil $" + String.format("%.2f", biil));
            } else if (sub.equals("2")) {
                processTransaction("Bixi biil");
            } else {
                System.out.println("Ka baxay biil menu-ga.");
            }
        } else if (choice.equals("2")) {
            processTransaction("Ku iibso");
        } else {
            System.out.println("Doorasho aan sax ahayn.");
        }
        returnToMainMenu();
    }

    private static void billPaymentMenu() {
        // Menu ku saabsan bill payment
        System.out.println("\nBILL PAYMENT");
        System.out.println("1: Itus Haraaga");
        System.out.println("2: Wada bixi biil-ka");
        System.out.println("3: Qeyb ka bixi biil-ka");
        System.out.print("Geli doorashada (1-3): ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                // Tus biilka oo ku qoran $250
                System.out.println("Biilkaagu waa: $250.00");
                addAction("Bill Payment → Itus Haraaga $250.00");
                break;
            case "2":
                processTransaction("Wada bixi biil-ka");
                break;
            case "3":
                processTransaction("Qeyb ka bixi biil-ka");
                break;
            default:
                System.out.println("Doorasho aan sax ahayn.");
        }
        returnToMainMenu();
    }

    private static void salaamBankMenu() {
        // Menu-ka Salaam Bank oo leh doorashooyin lacageed oo bankiga la xiriira
        System.out.println("\nSALAAM BANK");
        System.out.println("1: Itus haraagaga");
        System.out.println("2: Lacag dhigasho");
        System.out.println("3: Lacag qaadasho");
        System.out.println("4: Ka wareeji Evc Plus");
        System.out.print("Geli doorashada (1-4): ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                // Tus haraaga bankiga
                System.out.println("Salaam Bank Haraaga: $" + bankBalance);
                addAction("Salaam Bank Itus haraagaga → $" + bankBalance);
                break;
            case "2":
                // Lacag dhigasho: lacag Evc Plus ka wareeji bankiga
                System.out.print("Geli lacagta: $");
                double deposit = Double.parseDouble(scanner.nextLine());
                if (deposit <= balance) {
                    if (enterPin()) {        // Hubi pin-ka kahor lacag dhigashada
                        balance -= deposit;   // Ka jar balance Evc Plus
                        bankBalance += deposit; // Ku dar bank balance
                        System.out.println("Waad dhigatay $" + deposit);
                        addAction("Salaam Bank Lacag dhigasho $" + deposit);
                    }
                } else {
                    System.out.println("Haraagagu kuguma filna.");
                }
                break;
            case "3":
                // Lacag qaadasho: lacag bankiga ka qaado kuna shubo Evc Plus
                System.out.print("Geli lacagta: $");
                double withdraw = Double.parseDouble(scanner.nextLine());
                if (withdraw <= bankBalance) {
                    if (enterPin()) {          // Hubi pin-ka kahor
                        bankBalance -= withdraw;  // Ka jar bank balance
                        balance += withdraw;      // Ku dar Evc Plus balance
                        System.out.println("Waad qaadatay $" + withdraw);
                        addAction("Salaam Bank Lacag qaadasho $" + withdraw);
                    }
                } else {
                    System.out.println("Bank haraag kuguma filna.");
                }
                break;
            case "4":
                processTransaction("Ka wareeji Evc Plus"); // Wareejinta lacaga
                break;
            default:
                System.out.println("Doorasho aan sax ahayn.");
        }
        returnToMainMenu();
    }

    private static void maareyntaMenu() {
        // Menu maareynta oo u ogolaanaya user-ka inuu badalo pin, luqad, wargelin lumay, xir lacag, iyo celinta lacag
        System.out.println("\nMAAREYNTA");
        System.out.println("1: Bedel Pin-ka");
        System.out.println("2: Bedel luqada");
        System.out.println("3: Wergelin mobile lumay");
        System.out.println("4: Lacag xirasho");
        System.out.println("5: U celi lacag qaldantay");
        System.out.print("Dooro: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                // Badal pin
                System.out.print("Geli pin cusub: ");
                int newPin = Integer.parseInt(scanner.nextLine());
                System.out.print("Mar kale geli pin cusub: ");
                int confirm = Integer.parseInt(scanner.nextLine());
                if (newPin == confirm) {
                    pin = newPin;
                    System.out.println("Pin-ka waa la badalay.");
                    addAction("Bedel Pin-ka");
                } else {
                    System.out.println("Pin-ku isma waafaqaan.");
                }
                break;
            case "2":
                // Badal luqada interface-ka
                System.out.println("1: Somali");
                System.out.println("2: English");
                System.out.print("Dooro luqada: ");
                String lang = scanner.nextLine();
                if (lang.equals("1")) language = "Somali";
                else if (lang.equals("2")) language = "English";
                else { System.out.println("Doorasho khalad."); break; }
                System.out.println("Luqada waa la badalay: " + language);
                addAction("Bedel luqada → " + language);
                break;
            case "3":
                // Wargelin mobile lumay
                System.out.println("Wergelin mobile lumay: Diiwaangashan");
                addAction("Wergelin mobile lumay");
                break;
            case "4":
                // Lacag xirasho
                System.out.println("Lacagta waa la xiray.");
                addAction("Lacag xirasho");
                break;
            case "5":
                // U celin lacag qaldantay (refund)
                System.out.print("Geli lacagta: $");
                double refund = Double.parseDouble(scanner.nextLine());
                balance += refund;
                System.out.println("$" + refund + " ayaa laguu celiyey.");
                addAction("U celi lacag qaldantay $" + refund);
                break;
            default:
                System.out.println("Doorasho aan sax ahayn.");
        }
        returnToMainMenu();
    }

    private static void warbixinKoobanMenu() {
        // Menu-ka warbixin kooban oo user-ku arki karo dhaqdhaqaaqyada ugu dambeeyey
        System.out.println("\nWARBIXIN KOOBAN");
        System.out.println("1: Last action");
        System.out.println("2: Wareejintii u danbeysay");
        System.out.println("3: Iibsashadii u danbeysay");
        System.out.println("4: Last 3 action");
        System.out.println("5: E-mail me my activity");
        System.out.print("Dooro: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1": showLastAction(); break; // Tus dhaqdhaqaaqa ugu dambeeyey
            case "2": System.out.println("Wareejintii: Tusaale oo kaliya."); break;
            case "3": System.out.println("Iibsashadii: Tusaale oo kaliya."); break;
            case "4": showLastThreeActions(); break; // Tus 3-da dhaqdhaqaaq ee ugu dambeeyey
            case "5": System.out.println("Warbixin ayaa email ahaan la diray (simulation)."); break;
            default: System.out.println("Doorasho aan sax ahayn.");
        }
        returnToMainMenu();
    }

    private static void processTransaction(String type) {
        // Habka guud ee lagu qabto dhammaan noocyada kala duwan ee lacag dirista / hawlgallada
        System.out.print("Geli Number: ");
        String target = scanner.nextLine();
        System.out.print("Geli Lacagta: $");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount <= balance) {
            if (enterPin()) {    // Hubi pin kahor inta aan lacag laga jarin balance-ka
                balance -= amount;
                System.out.println("Waxaad $" + amount + " u dirtay " + target);
                addAction(type + " $" + amount + " → " + target);
            }
        } else {
            System.out.println("Haraagagu kuguma filna."); // Haraaga lacag ku filan maqan
        }
    }

    private static boolean enterPin() {
        // Hubinta pin-ka oo user-ka weydiiso oo sax yahay iyo in kale
        System.out.print("Geli Pin-kaaga: ");
        int input = Integer.parseInt(scanner.nextLine());
        if (input == pin) return true;
        System.out.println("Pin-ka waa khalad.");
        return false;
    }

    private static void addAction(String action) {
        // Ku darista dhaqdhaqaaq cusub ee lastThreeActions array-ga si wareegsan (circular)
        lastThreeActions[actionIndex % 3] = action;
        actionIndex++;
    }

    private static void showLastAction() {
        // Muujinta dhaqdhaqaaqa ugu dambeeyey
        if (actionIndex == 0) {
            System.out.println("Ma jiro action wali.");
        } else {
            int last = (actionIndex - 1) % 3;
            System.out.println("Last action: " + lastThreeActions[last]);
        }
    }

    private static void showLastThreeActions() {
        // Muujinta 3-dii dhaqdhaqaaq ee ugu dambeeyey (haddii jiraan)
        System.out.println("3-dii dhaqdhaqaaq ee ugu dambeysay:");
        boolean empty = true;
        for (String a : lastThreeActions) {
            if (a != null) {
                System.out.println("- " + a);
                empty = false;
            }
        }
        if (empty) System.out.println("Ma jiraan wax dhaqdhaqaaq ah.");
    }

    private static void returnToMainMenu() {
        // Kadib hawlgal kasta, user-ka la waydiiyo inuu menu-ga ku noqdo ama kabaxo barnaamijka
        System.out.println("\n1: Menu-ga");
        System.out.println("0: Kabax");
        System.out.print("Dooro: ");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            displayMainMenu();
        } else {
            System.out.println("Macsalaama!");

        }
    }
}