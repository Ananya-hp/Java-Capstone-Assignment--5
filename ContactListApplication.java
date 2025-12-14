import java.io.*;
import java.util.*;

// 1. Contact Class
class Contact {
    String name;
    String phoneNumber;
    String email;

    Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
    }
}

// 2. ContactManager Class
class ContactManager {
    private HashMap<String, Contact> contacts;

    ContactManager() {
        contacts = new HashMap<>();
    }

    // Add Contact
    public void addContact(Contact contact) {
        contacts.put(contact.name, contact);
        System.out.println("Contact added successfully.");
    }

    // Remove Contact
    public void removeContact(String name) {
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            System.out.println("Contact removed successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    // Search Contact
    public void searchContact(String name) {
        if (contacts.containsKey(name)) {
            System.out.println("Contact Found: " + contacts.get(name));
        } else {
            System.out.println("Contact not found.");
        }
    }

    // Display All Contacts
    public void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to display.");
            return;
        }
        System.out.println("All Contacts:");
        for (Contact c : contacts.values()) {
            System.out.println(c);
        }
    }

    // Save Contacts to File
    public void saveContactsToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Contact c : contacts.values()) {
                bw.write(c.name + "," + c.phoneNumber + "," + c.email);
                bw.newLine();
            }
            System.out.println("Contacts saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    // Load Contacts from File
    public void loadContactsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            contacts.clear(); // clear existing contacts before loading
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Contact c = new Contact(parts[0], parts[1], parts[2]);
                    contacts.put(c.name, c);
                }
            }
            System.out.println("Contacts loaded from file successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

// 3. Main Class - Menu-driven
public class ContactListApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactManager manager = new ContactManager();

        while (true) {
            System.out.println("\nWelcome to the Contact List Application!");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter contact name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    manager.addContact(new Contact(name, phone, email));
                    break;
                case 2:
                    System.out.print("Enter contact name to remove: ");
                    String removeName = sc.nextLine();
                    manager.removeContact(removeName);
                    break;
                case 3:
                    System.out.print("Enter contact name to search: ");
                    String searchName = sc.nextLine();
                    manager.searchContact(searchName);
                    break;
                case 4:
                    manager.displayAllContacts();
                    break;
                case 5:
                    System.out.print("Enter filename to save contacts: ");
                    String saveFile = sc.nextLine();
                    manager.saveContactsToFile(saveFile);
                    break;
                case 6:
                    System.out.print("Enter filename to load contacts: ");
                    String loadFile = sc.nextLine();
                    manager.loadContactsFromFile(loadFile);
                    break;
                case 7:
                    System.out.println("Exiting application. Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
