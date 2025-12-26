package com.urise.webapp;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.ListSection;
import com.urise.webapp.model.OrganizationsSection;
import com.urise.webapp.model.Position;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.Section;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.TextSection;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = createResume("uuid7", "Григорий Кислицин");
        printResume(resume);
    }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        initialContact(resume);
        initialSections(resume);
        return resume;
    }

    public static void printResume(Resume r) {
        printContact(r);
        printSection(r);
    }

    private static void initialContact(Resume resume) {
        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");
    }

    private static void printContact(Resume resume) {
        for (ContactType value : ContactType.values()) {
            System.out.println(value.getTitle() + ": " + resume.getContact(value));
        }
    }

    private static void initialSections(Resume resume) {
        resume.addSection(SectionType.OBJECTIVE, new TextSection(
                "Ведущий стажировок по Java Web и Enterprise технологиям"));
        resume.addSection(SectionType.PERSONAL, new TextSection(
                "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода."));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(initAchievement()));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(initQualifications()));
        resume.addSection(SectionType.EXPERIENCE, initExperience());
        resume.addSection(SectionType.EDUCATION, initEducation());
    }

    private static List<String> initAchievement() {
        List<String> achievement = new ArrayList<>();
        achievement.add("Организация Java-проектов для сторонних заказчиков: " +
                "Spring Cloud, Spring Boot, Play, Vaadin.");
        achievement.add("С 2013 года: ведение стажировок по Java Web и Enterprise." +
                " Более 3500 выпускников.");
        achievement.add("Реализация 2FA для Wrike: Twilio, DuoSecurity, Google " +
                "Authenticator, Jira, Zendesk.");
        achievement.add("Настройка CI/CD и архитектуры ERP River BPM: 1C, Bonita, CMIS, LDAP, CIFS/SMB.");
        achievement.add("Разработка RIA-приложения на JPA, Spring, GWT, Highstock для " +
                "алгоритмического трейдинга.");
        achievement.add("Создание отказоустойчивого JavaEE фреймворка: JAX-WS, JMS, Glassfish, Nagios, JMX.");
        achievement.add("Интеграция платежных систем России, Беларуси и Никарагуа.");
        return achievement;
    }

    private static List<String> initQualifications() {
        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish, OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("DB: PostgreSQL, Redis, H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.add("Languages: Java, Scala, Python, JavaScript, Groovy");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualifications.add("Java Frameworks: Spring Boot, JPA, Hibernate, Guice, Vaadin," +
                " JasperReports, JUnit");
        qualifications.add("Python: Django");
        qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.add("Scala: SBT, Play2, Specs2, Anorm, Akka");
        qualifications.add("Технологии: Servlet, JSP, JAX-WS, REST, JMS, JAXB, " +
                "JMX, JDBC, JPA, LDAP, OAuth, JWT");
        qualifications.add("Инструменты: Maven, Gradle, Nginx, Jenkins, Ant," +
                " SoapUI, Flyway, Nagios, pgBouncer");
        qualifications.add("Отличное знание ООП, SOA, шаблонов проектирования, " +
                "UML, функционального программирования");
        qualifications.add("Родной русский, английский upper intermediate");
        return qualifications;
    }

    private static OrganizationsSection initExperience() {
        OrganizationsSection experience = new OrganizationsSection();
        experience.add("Java Online Projects", "https://javaops.ru/",
                new Position(2013, Month.OCTOBER,
                        "Автор проекта",
                        "Создание и ведение Java онлайн стажировок."));
        experience.add("Wrike", "https://www.wrike.com/",
                new Position(2014, Month.OCTOBER, 2016, Month.JANUARY,
                        "Старший разработчик (backend)",
                        "Разработка платформы управления проектами на Spring, Vaadin, PostgreSQL."));
        experience.add("RIT Center", "",
                new Position(2012, Month.APRIL, 2014, Month.OCTOBER,
                        "Java архитектор",
                        "Разработка ERP-системы: CI/CD, Flyway, SSO, 1C, CMIS, CMIS."));
        experience.add("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/",
                new Position(2010, Month.DECEMBER, 2012, Month.APRIL,
                        "Ведущий программист",
                        "Разработка CRM на WebLogic, Spring, GWT, Oracle."));
        experience.add("Yota", "https://www.yota.ru/",
                new Position(2008, Month.JUNE, 2010, Month.DECEMBER,
                        "Ведущий специалист",
                        "Разработка Java EE фреймворка: EJB, JMS, GlassFish."));
        experience.add("Enkata", "https://enkata.com/",
                new Position(2007, Month.MARCH, 2008, Month.JUNE,
                        "Разработчик ПО",
                        "Кластерное J2EE приложение на JBoss, Hibernate."));
        experience.add("Siemens AG", "https://www.siemens.com/",
                new Position(2005, Month.JANUARY, 2007, Month.FEBRUARY,
                        "Разработчик ПО",
                        "Разработка ПО для мобильной IN платформы на Java."));
        experience.add("Alcatel", "https://alcatel.ru/",
                new Position(1997, Month.SEPTEMBER, 2005, Month.JANUARY,
                        "Инженер по тестированию",
                        "Тестирование и внедрение ПО цифровой телефонной станции."));
        return experience;
    }

    private static OrganizationsSection initEducation() {
        OrganizationsSection education = new OrganizationsSection();
        education.add("Coursera", "https://www.coursera.org/course/progfun",
                new Position(2013, Month.MARCH, 2013, Month.MAY,
                        "Functional Programming Principles in Scala by Martin Odersky", ""));
        education.add("Luxoft", "https://www.luxoft-training.ru/",
                new Position(2011, Month.MARCH, 2011, Month.APRIL,
                        "Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML", ""));
        education.add("Siemens AG", "https://www.siemens.ru/",
                new Position(2005, Month.JANUARY, 2005, Month.APRIL,
                        "Обучение мобильным IN сетям (Берлин)", ""));
        education.add("Alcatel", "https://alcatel.ru/",
                new Position(1997, Month.SEPTEMBER, 1998, Month.MARCH,
                        "Обучение цифровым телефонным сетям (Москва)", ""));
        List<Position> itmoPositions = new ArrayList<>();
        itmoPositions.add(new Position(1993, Month.SEPTEMBER, 1996, Month.JULY,
                "Аспирантура (программист С, С++)", ""));
        itmoPositions.add(new Position(1987, Month.SEPTEMBER, 1993, Month.JULY,
                "Инженер (программист Fortran, C)", ""));
        education.add("Санкт-Петербургский университет ИТМО", "https://itmo.ru/", itmoPositions);
        return education;
    }

    private static void printSection(Resume resume) {
        for (SectionType value : SectionType.values()) {
            Section section = resume.getSections(value);
            if (section != null) {
                System.out.println("\n" + value.getTitle() + ":\n" + section);
            }
        }
    }
}