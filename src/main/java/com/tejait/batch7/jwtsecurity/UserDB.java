package com.tejait.batch7.jwtsecurity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class UserDB {

	String username;
	String password;
}
