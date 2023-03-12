package com.naebank.bank.controller.dto;

import org.springframework.lang.Nullable;

public record AuthDto(String response, @Nullable String error){}
