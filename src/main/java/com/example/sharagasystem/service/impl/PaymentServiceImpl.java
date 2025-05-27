package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.exception.FastException;
import com.example.sharagasystem.exception.NotFoundException;
import com.example.sharagasystem.mapper.PaymentMapper;
import com.example.sharagasystem.model.Payment;
import com.example.sharagasystem.model.ResidentDetails;
import com.example.sharagasystem.model.dto.request.payment.PaymentRequestDto;
import com.example.sharagasystem.model.dto.response.payment.PaymentResponseDto;
import com.example.sharagasystem.model.enums.PaymentStatus;
import com.example.sharagasystem.repository.PaymentRepository;
import com.example.sharagasystem.repository.ResidentRepository;
import com.example.sharagasystem.repository.ServiceRepository;
import com.example.sharagasystem.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.beans.Transient;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ServiceRepository serviceRepository;
    private final ResidentRepository residentRepository;
    private final WayForPayService wayForPayService;

    @Override
    @Transactional
    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {
        Payment payment = PaymentMapper.mapToPayment(paymentRequestDto);
        ResidentDetails resident = residentRepository.findById(paymentRequestDto.getResidentId())
                .orElseThrow(() -> new NotFoundException("Resident not found"));
        payment.setResident(resident);
        com.example.sharagasystem.model.Service service = serviceRepository.findById(paymentRequestDto.getServiceId())
                .orElseThrow(() -> new NotFoundException("Service not found"));
        payment.setService(service);
        PaymentResponseDto paymentResponseDto = PaymentMapper.mapToPaymentResponse(paymentRepository.save(payment));
        String invoice = null;
        try {
            invoice = wayForPayService.createLink(payment);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        paymentResponseDto.setPaymentLink(invoice);
        return paymentResponseDto;
    }

    @Override
    public List<PaymentResponseDto> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(PaymentMapper::mapToPaymentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponseDto getPaymentById(UUID id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no payment with id " + id));

        String invoice = null;
        try {
            invoice = wayForPayService.createLink(payment);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        PaymentResponseDto paymentResponseDto = PaymentMapper.mapToPaymentResponse(payment);
        paymentResponseDto.setPaymentLink(invoice);
        return paymentResponseDto;
    }

    @Override
    public List<PaymentResponseDto> getPaymentsByUserId(UUID userId) {
        return paymentRepository.findByResidentId(userId).stream()
                .map(PaymentMapper::mapToPaymentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponseDto> getAllPaymentsCreationDate(LocalDate date) {
        return paymentRepository.findByCreationDate(date).stream()
                .map(PaymentMapper::mapToPaymentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponseDto> getAllPaymentsByServiceId(UUID serviceId) {
        return paymentRepository.findByServiceId(serviceId).stream()
                .map(PaymentMapper::mapToPaymentResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Scheduled(fixedRate = 3000)
    public void checkPendingPayments() {
        List<Payment> pending = paymentRepository.findByStatus(PaymentStatus.UNPAID);
        for (Payment payment : pending) {
            boolean paid = false;
            try {
                paid = wayForPayService.checkStatus(payment.getId().toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (paid) {
                payment.setStatus(PaymentStatus.PAID);
                payment.setPaymentDate(LocalDate.now());
                paymentRepository.save(payment);
            }
        }
    }

    @Override
    public byte[] generatePaymentPdf(UUID id) {
        PaymentResponseDto payment = paymentRepository.findById(id).map(PaymentMapper::mapToPaymentResponse)
                .orElseThrow(() -> new NotFoundException("There is no payment with id " + id));
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph(payment.getServiceName()));
            document.add(new Paragraph(payment.getAmount().toString()));
            document.add(new Paragraph(payment.getStatus().toString()));
            document.add(new Paragraph(payment.getPaymentDate().toString()));
            document.add(new Paragraph(payment.getPaymentLink()));
            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new FastException("Error while downloading payment status");
        }
    }

    @Override
    public void notifyForOverduePayments() {

    }
}
