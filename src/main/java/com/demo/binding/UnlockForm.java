package com.demo.binding;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UnlockForm {
private String email;
private String tempPwd;
private String newPwd;
private String confirmPwd;
}
