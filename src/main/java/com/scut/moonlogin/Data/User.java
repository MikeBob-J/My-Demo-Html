package com.scut.moonlogin.Data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

        private String userName;
        private Integer user_id;
        private String passWord;
}
