package com.example.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Scanner;

public class BCryptGenerator {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("🔐 Ingrese la contraseña a encriptar: ");
        String rawPassword = scanner.nextLine();

        String hashed = new BCryptPasswordEncoder().encode(rawPassword);
        System.out.println("✅ Hash generado:");
        System.out.println(hashed);
    }

}
