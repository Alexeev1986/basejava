package com.urise.webapp.util;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
       /* File filePath = new File(".\\.gitignore");
        try {
            System.out.println(filePath.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File(".\\src\\com\\urise\\webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)){
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        File root = new File(".");
        listFilesRecurs(root);
    }

    private static void listFilesRecurs(File dir) {
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Недопустимый каталог: " + dir.getAbsolutePath());
            return;
        }
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println(file.getName());
                listFilesRecurs(file);
            } else {
                System.out.println(file.getName());
            }
        }
    }
}
