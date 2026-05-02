package com.webmarket.service;

// Этот класс предназначен для тестирования хэширования пароля с помощью библиотеки jBCrypt
// его можно просто отдельно запустить и посмотреть хэш пароля "admin123" с использованием соли, сгенерированной методом gensalt().
// иначе не получится тестировать функционал админа
public class TestHash {
    public static void main(String[] args) {
        System.out.println(
                org.mindrot.jbcrypt.BCrypt.hashpw(
                        "admin123",
                        org.mindrot.jbcrypt.BCrypt.gensalt()

                )
        );
    }
}