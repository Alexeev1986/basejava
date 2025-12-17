package com.urise.webapp;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.ListSection;
import com.urise.webapp.model.OrganizationsSection;
import com.urise.webapp.model.Position;
import com.urise.webapp.model.Resume;
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
        //initialSections(resume);
        return resume;
    }

    public static void printResume(Resume r) {
        printContact(r);
        printSection(r);
    }

    private static void initialContact(Resume resume) {
        resume.setContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.setContact(ContactType.SKYPE, "grigory.kislin");
        resume.setContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.setContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        resume.setContact(ContactType.HOMEPAGE, "http://gkislin.ru/");
    }

    private static void printContact(Resume resume) {
        for (ContactType value : ContactType.values()) {
            System.out.println(value.getTitle() + ": " + resume.getContacts(value));
        }
    }

    private static void initialSections(Resume resume) {
        resume.setSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного " +
                "обучения по Java Web и Enterprise технологиям"));
        resume.setSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума," +
                " сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.setSection(SectionType.ACHIEVEMENT, new ListSection(initAchievement()));
        resume.setSection(SectionType.QUALIFICATIONS, new ListSection(initQualifications()));
        resume.setSection(SectionType.EXPERIENCE, initExperience());
        resume.setSection(SectionType.EDUCATION, initEducation());
    }

    private static List<String> initAchievement() {
        List<String> achievement = new ArrayList<>();
        achievement.add("""
                   Организация команды и успешная реализация Java проектов для сторонних заказчиков:
                приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей
                спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot
                + Vaadin проект для комплексных DIY смет""");
        achievement.add("""
                С 2013 года: разработка проектов "Разработка Web приложения","Java Enterprise"
                , "Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP).
                 Удаленное взаимодействие (JMS/AKKA)". Организация онлайн стажировок и
                 ведение проектов. Более 3500 выпускников.""");
        achievement.add("""
                Реализация двухфакторной аутентификации для онлайн платформы управления проектами
                Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.""");
        achievement.add("""
                Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM.
                 Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на
                 стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных
                 ERP модулей, интеграция CIFS/SMB java сервера.""");
        achievement.add("""
                Реализация c нуля Rich Internet Application приложения на стеке технологий JPA,
                 Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock
                 для алгоритмического трейдинга.""");
        achievement.add("""
                Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных
                 сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и
                 информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для
                 администрирования и мониторинга системы по JMX (Jython/ Django).""");
        achievement.add("""
                Реализация протоколов по приему платежей всех основных платежных системы
                России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.""");
        return achievement;
    }

    private static List<String> initQualifications() {
        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("""
                DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,
                "MySQL, SQLite, MS SQL, HSQLDB""");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualifications.add("""
                Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis,
                 Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink),
                 Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons,
                 Eclipse SWT, JUnit, Selenium (htmlelements).""");
        qualifications.add("Python: Django.");
        qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.add("""
                Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail,
                 JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet,
                 HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.""");
        qualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
        qualifications.add("""
                администрирование Hudson/Jenkins, Ant + custom task, SoapUI,
                JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer""");
        qualifications.add("""
                Отличное знание и опыт применения концепций ООП, SOA,
                шаблонов проектирования, архитектурных шаблонов, UML, функционального программирования""");
        qualifications.add("Родной русский, английский \"upper intermediate\" .");
        return qualifications;
    }

    private static OrganizationsSection initExperience() {
        OrganizationsSection experience = new OrganizationsSection();
        experience.add("Java Online Projects ", " https://javaops.ru/",
                new Position(2013, Month.of(10),
                        "Автор проекта.",
                        "Создание, организация и проведение Java онлайн проектов и стажировок."));
        experience.add("Wrike", "https://www.wrike.com/",
                new Position(2014, Month.of(10), 2016, Month.of(1),
                        "Старший разработчик (backend)",
                        """
                                Проектирование и разработка онлайн платформы управления проектами
                                 Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis)
                                 Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."""));
        experience.add("RIT Center", "",
                new Position(2012, Month.of(4), 2014, Month.of(10),
                        "Java архитектор", """
                        Организация процесса разработки системы ERP для разных окружений:
                        релизная политика, версионирование, ведение CI (Jenkins),
                        миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx),
                         AAA via SSO. Архитектура БД и серверной части системы. Разработка интеграционных
                         сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения
                         (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online
                         редактирование из браузера документов MS Office. Maven + plugin development,
                        Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis,
                         Bonita, Python scripting, Unix shell remote scripting 
                         via ssh tunnels, PL/Python"""));
        experience.add("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/",
                new Position(2010, Month.of(12), 2012, Month.of(4),
                        "Ведущий программист",
                        """
                                Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC,
                                 SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM.
                                 Реализация RIA-приложения для администрирования, мониторинга и анализа результатов
                                 в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT),
                                 Highstock, Commet, HTML5."""));
        experience.add("Yota", "https://www.yota.ru/",
                new Position(2008, Month.of(6), 2010, Month.of(12),
                        "Ведущий специалист",
                        """
                                Дизайн и имплементация Java EE фреймворка для отдела "Платежные Системы"
                                (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2).
                                 Реализация администрирования, статистики и мониторинга фреймворка.
                                Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"""));
        experience.add("Enkata", "https://enkata.com/",
                new Position(2007, Month.of(3), 2008, Month.of(6),
                        "Разработчик ПО",
                        """
                                Реализация клиентской (Eclipse RCP) и серверной 
                                (JBoss 4.2, Hibernate 3.0, Tomcat, JMS)
                                частей кластерного J2EE приложения (OLAP, Data mining)."""));
        experience.add("Siemens AG", "https://www.siemens.com/global/en.html",
                new Position(2005, Month.of(1), 2007, Month.of(2),
                        "Разработчик ПО",
                        """
                                Разработка информационной модели, проектирование 
                                интерфейсов, реализация и отладка ПО
                                на мобильной IN платформе Siemens @vantage (Java, Unix)."""));
        experience.add("Alcatel", "https://alcatel.ru/",
                new Position(1997, Month.of(9), 2005, Month.of(1),
                        "Инженер по аппаратному и программному тестированию",
                        """
                                Тестирование, отладка, внедрение ПО цифровой телефонной станции
                                Alcatel 1000 S12 (CHILL, ASM)."""));
        return experience;
    }

    private static OrganizationsSection initEducation() {
        OrganizationsSection education = new OrganizationsSection();
        education.add("Coursera", "coursera.org/course/progfun",
                new Position(2013, Month.of(3), 2013, Month.of(5),
                        "'Functional Programming Principles in Scala' by Martin Odersky",
                        ""));

        education.add("Luxoft", "https://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                new Position(2011, Month.of(3), 2011, Month.of(4),
                        "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'",
                        ""));
        education.add("Siemens AG", "http://www.siemens.ru/",
                new Position(2005, Month.of(1), 2005, Month.of(4),
                        "3 месяца обучения мобильным IN сетям (Берлин)",
                        ""));
        education.add("Alcatel", "https://alcatel.ru/",
                new Position(1997, Month.of(9), 1998, Month.of(3),
                        "6 месяцев обучения цифровым телефонным сетям (Москва)",
                        ""));
        List<Position> position = new ArrayList<>();
        position.add(new Position(1993, Month.of(9), 1996, Month.of(7),
                "Аспирантура (программист С, С++)",
                ""));
        position.add(new Position(1987, Month.of(9), 1993, Month.of(7),
                "Инженер (программист Fortran, C)",
                ""));
        education.add("Санкт-Петербургский национальный исследовательский университет информационных \n" +
                " ".repeat(35) + "технологий,механики и оптики", "https://itmo.ru/", position);
        education.add("Заочная физико-техническая школа при МФТИ", "https://mipt.ru/",
                new Position(1984, Month.of(9), 1987, Month.of(6),
                        "Закончил с отличием",
                        ""));
        return education;
    }

    private static void printSection(Resume resume) {
        for (SectionType value : SectionType.values()) {
            System.out.println("\n" + value.getTitle() + ": \n " + resume.getSections(value));
        }
    }
}
