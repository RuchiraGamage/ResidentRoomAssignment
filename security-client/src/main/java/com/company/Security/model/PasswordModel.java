package com.company.Security.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordModel {
    private String oldPassword;
    private String newPassword;
    private String email;
    private boolean isSuccess;
}
