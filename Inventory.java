import java.util.Scanner;

public class Inventory {
    private static String[] itemNames = new String[50];
    private static String[] itemDescriptions = new String[50];
    private static int[] itemQuantities = new int[50];
    private static String[] itemStatus = new String[50];
    private static int itemCount = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printWelcome();
        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    addItem();
                    break;
                case "2":
                    viewItems();
                    break;
                case "3":
                    searchItems();
                    break;
                case "4":
                    updateItemStatus();
                    break;
                case "5":
                    running = false;
                    System.out.println("\nThank you for using the Inventory Management System");
                    System.out.println("GOODBYE!");
                    break;
                default:
                    System.out.println("\nInvalid choice! Please enter 1, 2, 3, 4, or 5\n");
            }
        }
        scanner.close();
    }

    private static void printWelcome() {
        System.out.println("==========================================");
        System.out.println("   Welcome to Inventory Management System  ");
        System.out.println("==========================================\n");
    }

    private static void displayMenu() {
        System.out.println("===== Inventory Menu =====");
        System.out.println("1. Add Item");
        System.out.println("2. View Items");
        System.out.println("3. Search Items");
        System.out.println("4. Update Item Status");
        System.out.println("5. Exit");
    }

    private static String addItem(String name, int quantity) {
        if (itemCount >= 50) {
            return "Error: Inventory storage is full";
        }
        itemNames[itemCount] = name;
        itemQuantities[itemCount] = quantity;
        itemDescriptions[itemCount] = "No description";
        itemStatus[itemCount] = quantity > 0 ? "In Stock" : "Out of Stock";
        itemCount++;
        return "Item added successfully";
    }

    private static String addItem(String name, int quantity, String description) {
        if (itemCount >= 50) {
            return "Error: Inventory storage is full";
        }
        itemNames[itemCount] = name;
        itemQuantities[itemCount] = quantity;
        itemDescriptions[itemCount] = description;
        itemStatus[itemCount] = quantity > 0 ? "In Stock" : "Out of Stock";
        itemCount++;
        return "Item added successfully with description";
    }

    private static void addItem() {
        System.out.println("\n=== Add New Item ===");
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter item quantity: ");
        int quantity = 0;
        try {
            quantity = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity, defaulting to 0");
        }
        System.out.print("Enter description (press Enter to skip): ");
        String description = scanner.nextLine();

        String result;
        if (description.isEmpty()) {
            result = addItem(name, quantity);
        } else {
            result = addItem(name, quantity, description);
        }
        System.out.println("\n" + result + "\n");
    }

    private static void viewItems() {
        System.out.println("\n==== All Items ====");
        if (itemCount == 0) {
            System.out.println("(No items found, add some items first)\n");
        }
        for (int i = 0; i < itemCount; i++) {
            System.out.println((i + 1) + ". [" + itemStatus[i] + "] " + itemNames[i] +
                               " - Quantity: " + itemQuantities[i]);
            System.out.println("Description: " + itemDescriptions[i]);
        }
        System.out.println("\nTotal Items: " + itemCount + "\n");
    }

    private static void searchItems() {
        System.out.println("\n==== Search Items ====");
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine().toLowerCase();
        int found = 0;
        System.out.println("\n==== Search Results for '" + keyword + "' ====");
        for (int i = 0; i < itemCount; i++) {
            if (itemNames[i].toLowerCase().contains(keyword) ||
                itemDescriptions[i].toLowerCase().contains(keyword)) {
                System.out.println((i + 1) + ". [" + itemStatus[i] + "] " + itemNames[i] +
                                   " - Quantity: " + itemQuantities[i]);
                System.out.println("Description: " + itemDescriptions[i]);
                found++;
            }
        }
        if (found == 0) {
            System.out.println("No items found matching '" + keyword + "'");
        } else {
            System.out.println("\nFound " + found + " item(s)\n");
        }
    }

    private static void updateItemStatus() {
        System.out.println("\n==== Update Item Status ====");
        if (itemCount == 0) {
            System.out.println("No items to update\n");
            return;
        }
        viewItems();
        System.out.print("Enter item number to update: ");
        try {
            int itemNum = Integer.parseInt(scanner.nextLine());
            if (itemNum < 1 || itemNum > itemCount) {
                System.out.println("Invalid item number.\n");
                return;
            }
            System.out.println("\nSelect the new status:");
            System.out.println("1. In Stock");
            System.out.println("2. Out of Stock");
            System.out.print("Enter choice: ");
            String statusChoice = scanner.nextLine();
            switch (statusChoice) {
                case "1":
                    itemStatus[itemNum - 1] = "In Stock";
                    break;
                case "2":
                    itemStatus[itemNum - 1] = "Out of Stock";
                    break;
                default:
                    System.out.println("Invalid choice! Status not updated.\n");
                    return;
            }
            System.out.println("Status updated successfully");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number\n");
        }
    }
}