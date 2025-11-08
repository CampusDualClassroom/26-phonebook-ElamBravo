package com.campusdual.classroom;

import java.text.Normalizer;

public class Contact implements ICallActions {
    private String name;
    private String surname;
    private String phoneNumber;
    private String code;

    public Contact(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.code = generateCode(surname);
    }

    private String generateCode(String surname) {
        String normalized = Normalizer.normalize(surname.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        String[] parts = normalized.split(" ");
        if (parts.length == 1) {
            return parts[0];
        } else {
            return parts[0].substring(0, 1) + String.join("", java.util.Arrays.copyOfRange(parts, 1, parts.length));
        }
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surname;
    }

    public String getPhone() {
        return phoneNumber;
    }

    public String getCode() {
        return code;
    }

    @Override
    public void callMyNumber() {
        System.out.println(name + " está llamando a su propio número: " + phoneNumber);
    }

    @Override
    public void callOtherNumber(String number) {
        System.out.println(name + " está llamando al número: " + number);
    }

    @Override
    public void showContactDetails() {
        System.out.println("Nombre: " + name);
        System.out.println("Apellidos: " + surname);
        System.out.println("Teléfono: " + phoneNumber);
        System.out.println("Código: " + code);
    }
}


