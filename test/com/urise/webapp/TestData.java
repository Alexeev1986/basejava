package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.util.UUID;

public class TestData {

    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();
    public static final String UUID_5 = UUID.randomUUID().toString();

    public static final Resume RESUME_1;
    public static final Resume RESUME_2;
    public static final Resume RESUME_3;
    public static final Resume RESUME_4;
    public static final Resume RESUME_5;

    static {
        RESUME_1 = ResumeTestData.createResume(UUID_1, "Курочкин Евгений Николаевич");
        RESUME_2 = ResumeTestData.createResume(UUID_2, "Иванов Никита Николаевич");
        RESUME_3 = ResumeTestData.createResume(UUID_3, "Полохов Алексей Владимирович");
        RESUME_4 = ResumeTestData.createResume(UUID_4, "Петров Данила Васильевич");
        RESUME_5 = ResumeTestData.createResume(UUID_5, "Самар Павел Вечиславович");
    }
}
