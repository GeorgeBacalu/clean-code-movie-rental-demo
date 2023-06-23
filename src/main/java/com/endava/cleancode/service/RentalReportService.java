package com.endava.cleancode.service;

public interface RentalReportService {

    String generateTextReport(String customerName);

    String generateHtmlReport(String customerName);
}
