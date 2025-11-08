package com.campusdual.classroom;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Phonebook {
    private Map<String, Contact> contacts = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);


    public void start() {
        int option = -1;
        while (option != 0) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Mostrar contactos");
            System.out.println("3. Acciones de contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            option = readInt();

            switch (option) {
                case 1:
                    addContactInteractive();
                    break;
                case 2:
                    showAllContacts();
                    break;
                case 3:
                    contactActionsMenu();
                    break;
                case 4:
                    deleteContactInteractive();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }


    public Map<String, Contact> getData() {
        return contacts;
    }

    public void addContact(Contact c) {
        contacts.put(c.getCode(), c);
    }

    public void deleteContact(String code) {
        contacts.remove(code);
    }

    public void showPhonebook() {
        showAllContacts();
    }


    private void addContactInteractive() {
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Apellidos: ");
        String surname = scanner.nextLine();
        System.out.print("Teléfono: ");
        String phone = scanner.nextLine();

        Contact contact = new Contact(name, surname, phone);
        addContact(contact);
        System.out.println("Contacto añadido con código: " + contact.getCode());
    }

    private void deleteContactInteractive() {
        System.out.print("Introduce el código del contacto a eliminar: ");
        String code = scanner.nextLine();
        deleteContact(code);
        System.out.println("Contacto eliminado si existía.");
    }

    private void showAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No hay contactos.");
        } else {
            for (Contact c : contacts.values()) {
                c.showContactDetails();
                System.out.println("--------------------");
            }
        }
    }

    private void contactActionsMenu() {
        System.out.print("Introduce el código del contacto: ");
        String code = scanner.nextLine();
        Contact contact = contacts.get(code);
        if (contact == null) {
            System.out.println("Contacto no encontrado.");
            return;
        }

        int option = -1;
        while (option != 0) {
            System.out.println("\n--- ACCIONES PARA " + code + " ---");
            System.out.println("1. Llamar a mi número");
            System.out.println("2. Llamar a otro número");
            System.out.println("3. Mostrar detalles");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            option = readInt();

            switch (option) {
                case 1:
                    contact.callMyNumber();
                    break;
                case 2:
                    System.out.print("Número a llamar: ");
                    String number = scanner.nextLine();
                    contact.callOtherNumber(number);
                    break;
                case 3:
                    contact.showContactDetails();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Introduce un número válido: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}

